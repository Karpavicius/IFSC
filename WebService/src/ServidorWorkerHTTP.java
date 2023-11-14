
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.DataInputStream;

public class ServidorWorkerHTTP implements Runnable {

	String user;
	String pass;

	private Socket s;

	int content_length = 0;

	private void setcontent_length(String linha) {
		if (linha.toUpperCase().startsWith("CONTENT-LENGTH:")) {
			try {
				content_length = Integer.parseInt(linha.substring(15).trim());
			} catch (Exception e) {
				e.printStackTrace();
				content_length = 0;
			}
		}
	}

	public ServidorWorkerHTTP(Socket s) {
		this.s = s;
	}

	public void run() {
		String post = new String();

		try {
			String linha;
			int leitura, caracter;

			byte[] buffer = new byte[1024];
			try {

				// Faz a leitura do socket como um Stream
				DataInputStream in = new DataInputStream(s.getInputStream());

				// Faz a leitura até encontrar uma quebra de linha
				// Busca o content_length, que diz o tamanho do POST em bytes
				while ((linha = in.readLine()) != null) {
					if (linha.equals(""))
						break;
					// System.out.println(linha);
					setcontent_length(linha);
				}

				// Para a leitura do POST é necessário continuar lendo o Stream.
				// Como será lido em bytes, deve informar quantos bytes ler.
				// Caso não saiba o tamanho do stream, ficará lendo até a conexão fechar
				// porém o HTTP 1.1 não fecha a conexão até receber uma resposta
				// e a resposta neste código só é enviada depois de ler o stream.
				if (content_length > 0) {
					leitura = 0;
					while ((caracter = in.read(buffer, 0, 1024)) != -1) {
						leitura += caracter;
						post = new String(buffer, 0, caracter);
						if (leitura >= content_length)
							break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			String[] dados = post.split("&");
			user = dados[0].split("=")[1];
			pass = dados[1].split("=")[1];

			OutputStream outToClient = s.getOutputStream();

			if (user.compareTo("mateus") == 0 && pass.compareTo("mateus") == 0) {
				String res = "HTTP/1.0 200 OK\n" + "Server: HTTP server/0.1\n"
						+ "Content-type: text/html; charset=UTF-8\n" + "Content-Length: 38\n\n"
						+ "<html><body>OK</body></html>";
				outToClient.write(res.getBytes());
				outToClient.flush();
			} else {
				String res = "HTTP/1.0 200 OK\n" + "Server: HTTP server/0.1\n"
						+ "Content-type: text/html; charset=UTF-8\n" + "Content-Length: 38\n\n"
						+ "<html><body>ERRADO</body></html>";
				outToClient.write(res.getBytes());
				outToClient.flush();
			}

			Thread.currentThread().sleep(1000);

			outToClient.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

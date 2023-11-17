import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerCristian {
	
	private ServerSocket ss;
	
	public ServerCristian(ServerSocket ss) {
		this.ss=ss;
	}
	
	public void startListen() throws IOException, ParseException, InterruptedException {
		
		Socket sckt = null;
		
		System.out.println("rodando");
		
		do {
			if(sckt!=null) {
				sckt.close();
			}
			sckt = ss.accept();				//iniciando o server e aguardando conexão
			System.out.println("deu boa");	//printa quando recebe conexão
			readFromSocket(sckt);			
			
		}while(true);
	}
						//lê o socket
	public String readFromSocket(Socket socket) throws IOException, ParseException, InterruptedException {
		
		//salva o que recebe no socket em um buffer
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//formata a hora
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		String data = formato.format(new Date());
		Date sdf;
		//transforma a string em Date, assim pode usar os métodos gets
		sdf = formato.parse(data);
		//lê o que está vindo em formato string
		String horaRecebida = br.readLine();
		System.out.println(horaRecebida);
		Thread.currentThread().sleep(5000);
		
		//escreve para enviar pelo socket
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		//formata a data
		String data1 = formato.format(new Date());
		Date sdf1;
		//transforma a string em Date
		sdf1 = formato.parse(data1);
		//envia as horas na ordem t1, t2, t0
		pw.println(sdf.getHours()+":"+sdf.getMinutes()+":"+sdf.getSeconds()
		+"/"+sdf1.getHours()+":"+sdf1.getMinutes()+":"+sdf1.getSeconds()
		+"/"+horaRecebida);
		pw.flush();
		
		return null;
		
	}
}

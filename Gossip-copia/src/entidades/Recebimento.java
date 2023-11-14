package entidades;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Recebimento extends Thread {

	private Socket cliente;
	private String informacao;

	public Recebimento(Socket client) {
		super();
		this.cliente = client;
	}

	public Socket getClient() {
		return cliente;
	}

	public void setClient(Socket client) {
		this.cliente = client;
	}

	public String getFofoca() {
		return informacao;
	}

	public void setFofoca(String fofoca) {
		this.informacao = fofoca;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		InputStream inp;
		try {
			inp = cliente.getInputStream();		//"pegando" a parte que recebe dados do socket

			Scanner scan = new Scanner(inp);	//o scanner está pegando o que está chegando pelo inputstream do socket

			this.informacao = scan.nextLine();	//o atributo informação recebendo o dado enviado
			
			String identificacao = "Server1";
			
			String id = informacao.substring(0,7);
			
			//verifica se a assinatura da mensagem é diferente da identificação
			if(informacao.substring(0,7).compareTo(identificacao)!=0) {
				//caso sejam diferentes significa que é mensagem de outro server, então deve ser repassado
				System.out.println("mensagem recebida: " + informacao.substring(7));

				Threads thr = new Threads(cliente, informacao);	//criando uma thread com o socket e a informação
				
				thr.start();				//inicia a thread
			}else {//caso sejam iguais, significa que é mensagem do mesmo server
				System.out.println("repetida1");
			}

			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

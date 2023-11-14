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
			inp = cliente.getInputStream();

			Scanner scan = new Scanner(inp);

			this.informacao = scan.nextLine();

			System.out.println(informacao);

			Threads thr = new Threads(cliente, informacao);
			
			thr.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

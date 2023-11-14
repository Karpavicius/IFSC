package entidades;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Threads extends Thread {
	
	private Socket client;
	private String informacao;
	private Integer[] ips = new Integer[2];
	
	
	
	
	public Threads(String informacao) { //como é um teste local, os dois ips disponíveis serão o localhost
		super();
		this.informacao = informacao; //construtor que recebe apenas a informação/mensagem
		this.ips[0] = 6662;
		
		
	}

	public Threads(Socket client, String informacao) { //construtor que recebe a mensagem e a porta
		super();
		this.client = client;
		this.informacao = informacao;
		this.ips[0] = 6662;
		
	}
	
	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {			//getters e setters
		this.client = client;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	
	public void enviar(String ip, String informacao) throws IOException, InterruptedException { //recebe o ip e a informação
		
		

		OutputStream out;
		PrintWriter print;
		
		Socket envio;
		envio = new Socket(ip, 6662);		//socket usando o ip passado e a porta que vai ser usada
		out = envio.getOutputStream();		//essa é a "parte" do socket que aceita dados
		print = new PrintWriter(out, true);	//escreve no output do socket envio
		print.println(getInformacao());
		Thread.currentThread().sleep(1000); //deixa a thread dormindo por 1s
		envio.close();
		
		
	}
	
	@Override
	public void run() {
		
		int porta = client.getLocalPort();
		
		try {
			enviar("192.168.0.8", this.informacao);
							
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}



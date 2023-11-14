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
	private String[] ips = new String[2];
	
	
	
	
	public Threads(String informacao) {
		super();
		this.informacao = informacao;
		this.ips[0] = "192.168.0.1";
		this.ips[1] = "192.168.0.1";
		
	}

	public Threads(Socket client, String informacao) {
		super();
		this.client = client;
		this.informacao = informacao;
		this.ips[0] = "192.168.0.1";
		this.ips[1] = "192.168.0.1";
		
	}
	
	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	
	public void enviar(String ip, String informacao) throws IOException, InterruptedException {
		OutputStream out;
		PrintWriter print;
		
		Socket envio;
		envio = new Socket(ip, 6668);
		out = envio.getOutputStream();
		print = new PrintWriter(out, true);
		print.println(getInformacao());
		Thread.currentThread().sleep(1000);
		envio.close();	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		InetAddress inet = client.getInetAddress();
		
		Inet4Address in4 = (Inet4Address) inet;
		
		byte[] vet = in4.getAddress();
		
		String ipv4 = vet.toString();
		
		
		for(int i=0;i<2;i++) {
			
			try {
			
				if(ips[i].compareTo(ipv4) == 0) {
					
					enviar(ips[i], this.informacao);
					
				}else {
					
					System.out.println(ips[i]+" foi quem enviou");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}

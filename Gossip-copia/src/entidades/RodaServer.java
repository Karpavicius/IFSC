package entidades;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RodaServer extends Thread{
	private ThreadPoolExecutor lista;
	private ServerSocket server;
	public RodaServer(ThreadPoolExecutor lista, ServerSocket server) {
		this.lista = lista;
		this.server = server;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Ouvindo 1");
		while(true) {
			try {
				lista.execute(new Recebimento(server.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //as threads estão usando a classe Recebimento passando o socket
		}
		
	}
}
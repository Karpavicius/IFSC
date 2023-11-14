package entidades;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket server = new ServerSocket(6668);
		
		var lista = Executors.newFixedThreadPool(6);
		// TODO Auto-generated method stub
		
		Threads thre = new Threads("lul");
		
		thre.enviar("192.168.0.1", "lul");
		
		
		while(true) {
			lista.execute(new Recebimento(server.accept()));
		}

	}

}

package entidades;
import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import entidades.RodaServer;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket serverSocket = new ServerSocket(6661); //socket de conexão na porta informada

		
		ThreadPoolExecutor lista = (ThreadPoolExecutor) Executors.newFixedThreadPool(6); //cria um conjunto de execução de threads, nesse caso 6 é o limite, caso tenha mais entra em uma fila de execução
		
		RodaServer rodaServer = new RodaServer(lista, serverSocket);
		
		rodaServer.start();
		Scanner scan = new Scanner(System.in);
		
		String identificador = "Server1"; //criando uma assinatura pro server
													
		while(true) {
			String info = scan.nextLine();		//esperando o usuário escrever a mensagem
			
			//criando uma thread passando a assinatura + a mensagem que será enviada
			Threads thre = new Threads(identificador+info); 	
			
			thre.enviar("192.168.0.8", info);
		}
		

	}

}

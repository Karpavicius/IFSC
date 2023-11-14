package entidades;
import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import entidades.RodaServer;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket serverSocket = new ServerSocket(6661); //socket de conex�o na porta informada

		
		ThreadPoolExecutor lista = (ThreadPoolExecutor) Executors.newFixedThreadPool(6); //cria um conjunto de execu��o de threads, nesse caso 6 � o limite, caso tenha mais entra em uma fila de execu��o
		
		RodaServer rodaServer = new RodaServer(lista, serverSocket);
		
		rodaServer.start();
		Scanner scan = new Scanner(System.in);
		
		String identificador = "Server1"; //criando uma assinatura pro server
													
		while(true) {
			String info = scan.nextLine();		//esperando o usu�rio escrever a mensagem
			
			//criando uma thread passando a assinatura + a mensagem que ser� enviada
			Threads thre = new Threads(identificador+info); 	
			
			thre.enviar("192.168.0.8", info);
		}
		

	}

}

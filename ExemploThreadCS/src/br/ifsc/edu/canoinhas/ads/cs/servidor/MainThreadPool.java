package br.ifsc.edu.canoinhas.ads.cs.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThreadPool {

	private static Socket s; 
	private static ServerSocket ss;
	private static ExecutorService thPoolServer = Executors.newFixedThreadPool(10);  //Pool de 10 Threads

	public static void main(String[] args) throws IOException {

		ss = new ServerSocket(2800);

		while (true) {

			s = ss.accept();

			Runnable t1 = new ServidorWorker(s);
			thPoolServer.execute(t1);

		}

	}

}

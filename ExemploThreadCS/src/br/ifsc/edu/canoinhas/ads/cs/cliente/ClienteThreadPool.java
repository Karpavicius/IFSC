package br.ifsc.edu.canoinhas.ads.cs.cliente;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteThreadPool {

	private static ExecutorService thPoolClient = Executors.newFixedThreadPool(5);

	public static void main(String args[]) throws UnknownHostException, IOException {

		for (int i = 0; i < 5; i++) {
			thPoolClient.execute(new ClienteWorker());

		}

		thPoolClient.shutdown();

	}

}

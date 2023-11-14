package br.ifsc.edu.canoinhas.ads.cs.cliente;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainCliente {

	public static void main(String args[]) throws UnknownHostException, IOException {

		for (int i = 0; i < 5; i++) {
			ClienteWorker c1 = new ClienteWorker(); 
			c1.run();

		}

	}

}

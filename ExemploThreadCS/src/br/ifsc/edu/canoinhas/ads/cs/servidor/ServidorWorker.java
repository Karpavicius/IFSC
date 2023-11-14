package br.ifsc.edu.canoinhas.ads.cs.servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServidorWorker implements Runnable {

	private Socket s;

	public ServidorWorker(Socket s) {
		this.s = s;
	}

	public void run() {

		try {

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String requestString = inFromClient.readLine();

			System.out.println("Cliente enviou : " + requestString);

			/* Aqui faz algum processamento*/

			
			DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());

			outToClient.writeBytes("Resposta " + Math.random());
			outToClient.writeBytes("\n");
			outToClient.flush();
			outToClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

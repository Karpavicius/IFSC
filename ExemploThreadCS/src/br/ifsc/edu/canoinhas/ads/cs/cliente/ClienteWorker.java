package br.ifsc.edu.canoinhas.ads.cs.cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClienteWorker implements Runnable {

	private Socket socket;

	public void run()  {
		
		try {
			socket = new Socket("localhost", 2800);
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			wr.write("Request " + Math.random());
			wr.write("\n");
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String requestString = rd.readLine();
			System.out.println("Server replied: " + requestString);
			rd.close();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}


}

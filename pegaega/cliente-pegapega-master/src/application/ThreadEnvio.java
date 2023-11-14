package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadEnvio implements Runnable {
	
	private Socket socket;
	
	TelaGameController tela;
	public ThreadEnvio (Socket socket, TelaGameController game) {
		//recebe um socket já conectado num server e o controlador da tela
		this.socket = socket;
		tela = game;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
			
			while(TelaGameController.game) {
				//pega o tempo da tela do game e incrementa 1
				TelaGameController.tempoFloat++;
				//dorme 1 segundo
				Thread.currentThread().sleep(1000);
				//manda pro server o tempo;x;y
				print.println(TelaGameController.tempoFloat+";"+TelaGameController.x+";"+TelaGameController.y);
				//manda atualizar o tempo da tela do game
				tela.atualizaTempo();
				
				
			}
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

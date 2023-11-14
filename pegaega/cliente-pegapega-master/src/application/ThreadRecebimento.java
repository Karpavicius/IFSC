package application;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javafx.application.Platform;

public class ThreadRecebimento  implements Runnable{
	Socket a1;
	TelaGameController tela;
	TelaGame telaGame;
	public ThreadRecebimento (Socket socket, TelaGameController game, TelaGame telaGame) {
		//recebe um socket já ligado no server, um controler e uma classe de tela
		a1 = socket;
		tela = game;
		this.telaGame = telaGame;
	}
	
	public void trocaTempo(String tempo) {
		//função q é chamada quando o tempo deve ser trocado, ela seta o tempo do controler e dps da tela do game
		TelaGameController.tempoFloat = Float.parseFloat(tempo);
	}
	
	
	
	public static String getData() {
		//não é mais usada 
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String formato = "Hora: "+ calendar.get(Calendar.HOUR_OF_DAY) +" Minuto: "+ calendar.get(Calendar.MINUTE) + " Segundo: "+calendar.get(Calendar.SECOND);
		return formato;
	}

	@Override
	public void run() {
		
		try {
			
			Scanner scanner = new Scanner(a1.getInputStream());
			
			while(TelaGameController.game) {
				//fica rodando esse while enquanto o jogo não acabar
				if(scanner.hasNextLine()) {
					//recebe a msg do server e separa os lugares que tiver um ;
				final String[] stringRPS = scanner.nextLine().toString().split(";");
				
				
				switch (stringRPS[0]) {
						//se a msg for tempo; ele disparaa função de atualizar tempo
				case "tempo":
					TelaGameController.tempoFloat = Float.parseFloat(stringRPS[1]);
						//chama a função do controller que atualiza o tempo e atualiza na tela o game
					tela.atualizaTempo();
					break;
				case "Encerrou":
					//se for encerrou ele chama uma nova tela pra cabar o game e passa como ele acabou
					 Platform.runLater(new Runnable() {
		                 @Override public void run() {
		                     telaGame.gameEnd(stringRPS[1]);
		                 }
		             });
					break;
					
				case "Proximo":
						//se for proximo ele chama a função de aviso, que apresenta um textfield na tela dizendoq
						//que tem gente perto
					tela.setAviso(stringRPS[1], TelaGameController.tempoFloat);
					break;

				default:
					break;
				}
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
}

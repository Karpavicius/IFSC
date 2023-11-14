package application;

import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaGame extends Application{
	//tela de game precisa de um socket pra mandar pra thread que envia a posição e o tempo e pra thread que recebe todos os dados
	//do server
	Socket socket;
	static Stage primaryStage;
	public void gameEnd(String como) {
		//como(variavel ali) é como o jogo acabou, aqui ele cria a tela de endgame, e passa pro endgame como o jogo acabou
			try {
			EndGameController controller;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
			Parent root = (Parent) loader.load();
			

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.show();
			    controller = loader.getController();
			    controller.setComo(como);
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
	}
	
	public TelaGame(Socket socket) {
		this.socket = socket;
	}
	
	public TelaGameController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//inicia a tela de jogo
		this.primaryStage = primaryStage;
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaGame.fxml"));
			Parent root = (Parent) loader.load();
			//pega o controlador da tela de jogo
			this.controller = (TelaGameController) loader.getController(); //Essa é a linha importante, é onde você captura o objeto atual que representa seu controller

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.setTitle("Game");
			        primaryStage.show();
			    	
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
		//cria uma thread de envio, manda sempre tempo;x;y
		ThreadEnvio threadEnvio = new ThreadEnvio(socket, controller);
		//dispara a thread de envio
		Thread thread = new Thread(threadEnvio);
		thread.start();
		//cria uma thread de que recebe as infos do server ("novo tempo" ou se o jogo aacabou ou se foi pego)
		ThreadRecebimento recebimento = new ThreadRecebimento(socket, controller, this);
		//dispara a thread de recebimento
		Thread thread2 = new Thread(recebimento);
		thread2.start();
		
	}

}

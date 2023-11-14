package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaAguardo extends Application{
	
	private String tipo;
	
	
	public TelaAguardo(String tipo) {
		this.tipo = tipo;
	}
	
	public TelaAguardoController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			//carrega o fxml da tela de aguardo
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAguardo.fxml"));
			Parent root = (Parent) loader.load();
			this.controller = (TelaAguardoController) loader.getController(); //Essa é a linha importante, é onde você captura o objeto atual que representa seu controller

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.show();
			    	
			    } catch (Exception ex) {
			        System.out.println(ex.getMessage());
			    }
		
		
		
		//cria um socket
		Socket a1;
		try {
			//dispara o socket para o servidor 
			a1 = new Socket("localhost",4261);
		//escritor para mandar dados pro server
		PrintWriter a = new PrintWriter(a1.getOutputStream(), true);
		
		a.println(tipo);
		//scanner pra ler oq vem do server
		Scanner a2 = new Scanner(a1.getInputStream());
		Integer tempo = 0;
		
		String f3;
		
	
		while(true) {
			//fica rodando nesse while até ler "Iniciar;tipo", msg que vem do server
			f3 = a2.nextLine().toString();
			
		System.out.println(f3);
		
		if(f3.toString().equals("Iniciar;"+tipo)) {
			break;
		}
		
		try {//se a msg que vem do server não for "Iniciar;tipo" ele dorme por 1 seg
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		//acabou a espera, jogo comeca
		//quando começa o jogo ele abre a tela do game aqui
		TelaGame game = new TelaGame(a1);
		game.start(primaryStage);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}  
		
	}
	
	



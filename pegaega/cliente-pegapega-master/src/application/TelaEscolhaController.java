package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class TelaEscolhaController implements Initializable {
	
	@FXML
	ComboBox<String> comboBoxTipos;
	Button ok;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		

		comboBoxTipos.setItems(FXCollections.observableArrayList("runner", "catch"));
		
	
	}
	
	public void entrar() {
		//função que está no botão da tela de escolha de tipo, quando clicado o botão ativa esta função, que abre a tela de aguardo
		if(comboBoxTipos.getValue() != null) {
		
			
			try {
				
				TelaAguardo telaAguardo = new TelaAguardo(comboBoxTipos.getValue().toString());
				Stage s = (Stage) comboBoxTipos.getScene().getWindow();
				telaAguardo.start(s);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	


}

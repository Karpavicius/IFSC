package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class EndGameController implements Initializable{
	
	
	
	
	@FXML
	Text texto;
	public void setComo(String como) {
		//função que colocar como o game acabou na tela de endgame
		texto.setText(como);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

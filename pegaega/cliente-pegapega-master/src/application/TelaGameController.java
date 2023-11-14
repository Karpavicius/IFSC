package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TelaGameController implements Initializable {
//a thread de envio pega o tempo;x;y  daqui, esse é o game msm
	public static int x, y;
	public static float tempoFloat;
	public static boolean game = true;
	@FXML
	Text campo;
	@FXML
	Text tempo;
	@FXML
	Text aviso;
	@FXML
	Button cima, baixo, esquerda, direita;
	//função que é disparada pela thread de recebimento quando recebe um "Próximo"
	public void setAviso(String msg, float tempo) {
		
		aviso.setText("No tempo " + tempo +" "+msg);
		
		
	}
	
	private void block() {
		//função que trava os botões e libera eles dps de 1 segundo, feita pra o jogardor não mandar mt requisição
		cima.setDisable(true);
		baixo.setDisable(true);
		esquerda.setDisable(true);
		direita.setDisable(true);
		
		try {
			Thread.currentThread().sleep(1000);
			//habilita os tões
			cima.setDisable(false);
			baixo.setDisable(false);
			esquerda.setDisable(false);
			direita.setDisable(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void setCampo() {
		//funçãoq que é chamada sempre que um botão (cima baixo por ai vai) é apertado, 
		//essa função refaz o campo, 2 for , se o for bater estiver no msm valor que x e y ele coloca 1, representando o jogador
		String campoUnificado = "";
		for(int a = 0; a < 8 ;a ++) {
			for(int b = 0; b < 8 ;b ++) {
				if(a == x && b == y) {
					campoUnificado += " 1 ";
				}else {
					campoUnificado+= " 0 ";
				}
			}
			campoUnificado+="\n";
		}
		
		//coloca o campo pronto no textfield
		campo.setText(campoUnificado);
		
	}
	
	public void atualizaTempo() {
		//função chamada nas threads quando chegar a msg de novo tempo, ele chama nas thread e atualiza o tempo na interface
		tempo.setText(Float.toString(tempoFloat));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//inicia as variáveis 
		x = y = 0;
		tempoFloat = 0;
		
		setCampo();
		//cada um desses é uma ação de botão
		cima.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (x - 1 >= 0) {
					x--;
					setCampo();
					block();
				}
				
			}
		});

		baixo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (x + 1 < 8) {
					x++;
					setCampo();
					block();
				}

			}
		});

		esquerda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (y - 1 >= 0) {
					y--;
					setCampo();
					block();
				}

			}
		});

		direita.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (y + 1 < 8) {
					y++;
					setCampo();
					block();
				}

			}
		});

	}

}

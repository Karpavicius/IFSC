package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	

	
	@Override
	public void start(Stage primaryStage) {
		try {
			//lança o game e a tela inicial de escolha do tipo do jogador, não faz verificação se está disponível ou não o tipo
			Parent root = FXMLLoader.load(getClass().getResource("TelaEsolhaTipo.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Escolha seu tipo");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

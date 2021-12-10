package controllers;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class RegistrazioneUtente1Controller  extends DefaultSceneController{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void confermaDatiRegistrazione(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Conferma i dati inseriti per procedere alla generazione della password");
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("registrazioneUtenteScene2.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Registrazione utente");
			stage.show();
		}
	}
}

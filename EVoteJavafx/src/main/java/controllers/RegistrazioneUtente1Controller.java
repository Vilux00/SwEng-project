package controllers;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class RegistrazioneUtente1Controller  extends DefaultSceneController{
	
	public void confermaDatiRegistrazione(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Conferma i dati inseriti per procedere");
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			setScenaPrecedente("registrazioneUtenteScene1.fxml", "Registrazione utente");
			changeScene(event, "registrazioneUtenteScene2.fxml", "Registrazione utente");
		}
	}
}

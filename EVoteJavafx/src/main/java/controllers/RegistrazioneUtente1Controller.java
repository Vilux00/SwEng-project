package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

public class RegistrazioneUtente1Controller  extends DefaultSceneController implements Initializable{
		
 	@FXML
    private ChoiceBox<Integer> choiceBoxAnno;
    @FXML
    private ChoiceBox<Integer> choiceBoxGiorno;

    @FXML
    private ChoiceBox<Integer> choiceBoxMese;

    @FXML
    private ChoiceBox<String> choiceBoxPrivilegio;

    @FXML
    private ChoiceBox<String> choiceBoxSesso;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}

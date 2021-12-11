package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloGestoreController extends ProfiloScrutatoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreScene.fxml", "Home profilo");
		changeScene(event, "registrazioneUtenteScene1.fxml", "Registrazione utente");
	}
	
	public void goToRegistrazioneUtente(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreScene.fxml", "Home profilo");
		changeScene(event, "registrazioneUtenteScene1.fxml", "Registrazione utente");
	}
}

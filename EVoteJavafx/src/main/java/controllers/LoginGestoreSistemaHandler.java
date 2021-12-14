package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginGestoreSistemaHandler extends DefaultSceneHandler{

	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginGestoreSistemaView.fxml", "Login gestore"); 
		changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema");
		DefaultSceneHandler.isLogged = true;
	}
}

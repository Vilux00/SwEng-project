package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginGestoreSistemaController extends DefaultSceneController{

	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginGestoreSistemaScene.fxml", "Login gestore"); 
		changeScene(event, "profiloGestoreScene.fxml", "Profilo gestore di sistema");
		DefaultSceneController.isLogged = true;
	}
}

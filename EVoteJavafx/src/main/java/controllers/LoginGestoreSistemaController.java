package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginGestoreSistemaController extends DefaultSceneController{

	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginGestoreSistemaScene.fxml", "Home profilo"); 
		changeScene(event, "profiloGestoreScene.fxml", "Home profilo");
		DefaultSceneController.isLogged = true;
	}
}

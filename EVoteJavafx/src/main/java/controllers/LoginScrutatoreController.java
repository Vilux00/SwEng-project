package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;


public class LoginScrutatoreController extends DefaultSceneController{
	
	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginScrutatoreScene.fxml", "Login scrutatore"); 
		changeScene(event, "profiloScrutatoreScene.fxml", "Home profilo");
		DefaultSceneController.isLogged = true;
	}
}

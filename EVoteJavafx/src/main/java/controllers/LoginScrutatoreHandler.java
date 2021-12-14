package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;


public class LoginScrutatoreHandler extends DefaultSceneHandler{
	
	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginScrutatoreView.fxml", "Login scrutatore"); 
		changeScene(event, "profiloScrutatoreView.fxml", "Home profilo");
		DefaultSceneHandler.isLogged = true;
	}
}

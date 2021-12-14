package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginElettoreHandler extends DefaultSceneHandler{	
	
	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginElettoreView.fxml", "Login elettore"); 
		changeScene(event, "profiloElettoreView.fxml", "Home profilo");
		DefaultSceneHandler.isLogged = true;
	}
	
	
}

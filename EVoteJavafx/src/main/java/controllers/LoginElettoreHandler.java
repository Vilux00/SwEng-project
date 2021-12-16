package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import main.Password;

public class LoginElettoreHandler extends DefaultSceneHandler{	

	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginElettoreView.fxml", "Login elettore"); 
		changeScene(event, "profiloElettoreView.fxml", "Home profilo");
		DefaultSceneHandler.isLogged = true;
	}
	
	
}

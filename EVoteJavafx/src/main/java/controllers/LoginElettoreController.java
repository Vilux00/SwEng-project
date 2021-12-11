package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginElettoreController extends DefaultSceneController{	
	
	public void login(ActionEvent event) throws IOException {
		setScenaPrecedente("loginElettoreScene.fxml", "Login elettore"); // NO
		changeScene(event, "profiloElettoreScene.fxml", "Home profilo");
	}
	
	
}

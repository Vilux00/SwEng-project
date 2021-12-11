package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;


public class HomeController extends DefaultSceneController{
	
	public void switchToLoginElettoreScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeScene.fxml", "Home");
		changeScene(event, "loginElettoreScene.fxml", "Login elettore");
	}
	
	public void switchToLoginScrutatoreScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeScene.fxml", "Home");
		changeScene(event, "loginScrutatoreScene.fxml", "Login scrutatore");
	}

	public void switchToLoginAdminScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeScene.fxml", "Home");
		changeScene(event, "loginGestoreSistemaScene.fxml", "Login gestore");
	}
	
}

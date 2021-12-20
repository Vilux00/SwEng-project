package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;


public class HomeHandler extends DefaultSceneHandler{
	
	public void switchToLoginElettoreScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeView.fxml", "Home");
		changeScene(event, "loginElettoreView.fxml", "Login elettore");
	}
	
	public void switchToLoginScrutatoreScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeView.fxml", "Home");
		changeScene(event, "loginScrutatoreView.fxml", "Login scrutatore");
	}

	public void switchToLoginAdminScene(ActionEvent event) throws IOException {
		setScenaPrecedente("homeView.fxml", "Home");
		changeScene(event, "loginGestoreSistemaView.fxml", "Login gestore");
	}
	
}

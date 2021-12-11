package controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController extends DefaultSceneController{
	/*
	private Stage stage;
	private Scene scene;
	private Parent root;
	*/
	public void exit(ActionEvent event) {
		 Platform.exit();
	}
	
	public void switchToLoginElettoreScene(ActionEvent event) throws IOException {
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		changeScene(event, "loginElettoreScene.fxml", "Login elettore");
	}
	
	public void switchToLoginScrutatoreScene(ActionEvent event) throws IOException {
		
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		
		changeScene(event, "loginScrutatoreScene.fxml", "Login scrutatore");
	}

	public void switchToLoginAdminScene(ActionEvent event) throws IOException {
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		changeScene(event, "loginGestoreSistemaScene.fxml", "Login gestore");
	}
}

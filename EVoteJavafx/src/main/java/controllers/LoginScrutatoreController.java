package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScrutatoreController extends DefaultSceneController{
	private Parent root;
	private Stage stage;
	private Scene scene;

	public void login(ActionEvent event) throws IOException {
		DefaultSceneController.scenaPrecedente.add("loginScrutatoreScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Login scrutatore");	
		changeScene(event, "profiloScrutatoreScene.fxml", "Home profilo");
	}
}

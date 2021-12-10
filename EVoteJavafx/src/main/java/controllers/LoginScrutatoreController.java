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
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("profiloScrutatoreScene.fxml"));
		root = loader.load();
		DefaultSceneController.scenaPrecedente.add("loginScrutatoreScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Login scrutatore");	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Profilo scrutatore");
		stage.show();
	}
}

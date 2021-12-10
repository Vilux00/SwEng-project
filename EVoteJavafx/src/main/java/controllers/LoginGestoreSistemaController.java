package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginGestoreSistemaController extends DefaultSceneController{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void login(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("profiloGestoreScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home");
		stage.show();
	}
}
package controllers;

import java.io.IOException;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DefaultSceneController {
	private Parent root;
	private Stage stage;
	protected Scene scene;
	
	protected static Stack<String> scenaPrecedente = new Stack<>();
	protected static Stack<String> scenaPrecedenteTitolo = new Stack<>();
		
	public void changeScene(ActionEvent event, String sceneName, String title) throws IOException{
		root = FXMLLoader.load(getClass().getClassLoader().getResource(sceneName));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	public void backToHomeScene(ActionEvent event) throws IOException {
		changeScene(event, "homeScene.fxml", "Home");
		DefaultSceneController.scenaPrecedente.clear();
		DefaultSceneController.scenaPrecedenteTitolo.clear();
	}
	
	public void setScenaPrecedente(String fxml, String titolo) {
		DefaultSceneController.scenaPrecedente.add(fxml);
		DefaultSceneController.scenaPrecedenteTitolo.add(titolo);
	}
	
	public void goToScenaPrecedente(ActionEvent event) throws IOException{
		changeScene(event, DefaultSceneController.scenaPrecedente.pop(), DefaultSceneController.scenaPrecedenteTitolo.pop());
	}
}

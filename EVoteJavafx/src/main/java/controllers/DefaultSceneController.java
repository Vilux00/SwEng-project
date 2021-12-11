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
	private Scene scene;
	
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
		/*root = FXMLLoader.load(getClass().getClassLoader().getResource("homeScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home");
		stage.show();*/
		changeScene(event, "homeScene.fxml", "Home");
	}
	
	public void setScenaPrecedente(String fxml, String titolo) {
		scenaPrecedente.add(fxml);
		scenaPrecedenteTitolo.add(titolo);
	}
	
	public void goToScenaPrecedente(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getClassLoader().getResource(scenaPrecedente.pop()));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(scenaPrecedenteTitolo.pop());
		stage.show();
	}
}

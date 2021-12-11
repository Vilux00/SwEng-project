package controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DefaultSceneController {
	private Parent root;
	private Stage stage;
	protected Scene scene;
	
	protected static Stack<String> scenaPrecedente = new Stack<>();
	protected static Stack<String> scenaPrecedenteTitolo = new Stack<>();
	
	protected static boolean isLogged = false;
		
	public void changeScene(ActionEvent event, String sceneName, String title) throws IOException{
		root = FXMLLoader.load(getClass().getClassLoader().getResource(sceneName));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	public void backToHomeScene(ActionEvent event) throws IOException {
		
		if (isLogged == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Sicuro di fare logout?");
			alert.setHeaderText("Conferma logout");
			alert.setTitle("Logout");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				changeScene(event, "homeScene.fxml", "Home");
				DefaultSceneController.scenaPrecedente.clear();
				DefaultSceneController.scenaPrecedenteTitolo.clear();
			}
		}else {
			changeScene(event, "homeScene.fxml", "Home");
			DefaultSceneController.scenaPrecedente.clear();
			DefaultSceneController.scenaPrecedenteTitolo.clear();
		}
	}
	
	public void setScenaPrecedente(String fxml, String titolo) {
		DefaultSceneController.scenaPrecedente.add(fxml);
		DefaultSceneController.scenaPrecedenteTitolo.add(titolo);
	}
	
	public void goToScenaPrecedente(ActionEvent event) throws IOException{
		changeScene(event, DefaultSceneController.scenaPrecedente.pop(), DefaultSceneController.scenaPrecedenteTitolo.pop());
	}
}

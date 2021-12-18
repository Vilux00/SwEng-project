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

public class DefaultSceneHandler {
	private Parent root;
	private Stage stage;
	protected Scene scene;
	
	protected Object data;
	
	protected static Stack<String> scenaPrecedente = new Stack<>();
	protected static Stack<String> scenaPrecedenteTitolo = new Stack<>();
	
	protected static boolean isLogged = false;
		
	public void changeScene(ActionEvent event, String sceneName, String title, Object o) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+sceneName));
		root = loader.load();
		DefaultSceneHandler c = loader.getController();
		c.passParameter(o);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public void changeScene(ActionEvent event, String sceneName, String title) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+sceneName));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	public void passParameter(Object o) {
		this.data = o;
	}
	
	public void backToHomeScene(ActionEvent event) throws IOException {	
		if (isLogged == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Sicuro di fare logout?");
			alert.setHeaderText("Conferma logout");
			alert.setTitle("Logout");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				changeScene(event, "homeView.fxml", "Home");
				scenaPrecedente.clear();
				scenaPrecedenteTitolo.clear();
				isLogged = false;
			}
		}else {
			changeScene(event, "homeView.fxml", "Home");
			scenaPrecedente.clear();
			scenaPrecedenteTitolo.clear();
		}
	}
	
	public void setScenaPrecedente(String fxml, String titolo) {
		scenaPrecedente.add(fxml);
		scenaPrecedenteTitolo.add(titolo);
	}
	
	public void goToScenaPrecedente(ActionEvent event, Object data) throws IOException{
		String str = scenaPrecedente.lastElement();
		if (str.contains("login") == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Sicuro di fare logout?");
			alert.setHeaderText("Conferma logout");
			alert.setTitle("Logout");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
				isLogged = false;
			}
			return;
		}
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}

	public void goToScenaPrecedente(ActionEvent event) throws IOException{
		String str = scenaPrecedente.lastElement();
		if (str.contains("login") == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Sicuro di fare logout?");
			alert.setHeaderText("Conferma logout");
			alert.setTitle("Logout");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
				isLogged = false;
			}
			return;
		}
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
	
	public void rimuoviScenaPrecedente() {
		scenaPrecedente.pop();
		scenaPrecedenteTitolo.pop();
	}
	
	public void rimuoviScenaPrecedente(int n) {
		for (int i = 0; i < n; i++) {
			rimuoviScenaPrecedente();
		}
	}
}

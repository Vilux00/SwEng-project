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
import model.ElettoreHolder;

public abstract class DefaultSceneController {
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
		DefaultSceneController c = loader.getController();
		c.receiveData(o); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public void changeScene(ActionEvent event, String sceneName, String title) throws IOException{
		changeScene(event, sceneName, title, null);
	}
	
	public void backToHomeScene(ActionEvent event) throws IOException {	
		if (isLogged == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Sei sicuro di voler effettuare il logout?");
			alert.setTitle("Logout");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				changeScene(event, "homeView.fxml", "Home");
				scenaPrecedente.clear();
				scenaPrecedenteTitolo.clear();
				isLogged = false;
				ElettoreHolder.getInstance().setElettore(null);
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

	public void rimuoviScenaPrecedente() {
		scenaPrecedente.pop();
		scenaPrecedenteTitolo.pop();
	}
	
	public void rimuoviScenaPrecedente(int n) {
		for (int i = 0; i < n; i++) rimuoviScenaPrecedente();
	}
	
	public void receiveData(Object o) {
		this.data = o;
	}
	
	public abstract void goToScenaPrecedente(ActionEvent event) throws IOException;
}

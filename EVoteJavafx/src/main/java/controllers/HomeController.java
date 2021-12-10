package controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void exit(ActionEvent event) {
		 Platform.exit();
	}
	
	public void switchToLoginElettoreScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginElettoreScene.fxml"));
		root = loader.load();
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		/*
		LoginElettoreController controller = loader.getController();
		controller.setScenaPrecedente("homeScene.fxml", "Home");
		*/
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login Elettore");
		stage.show();
	}
	
	public void switchToLoginScrutatoreScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginScrutatoreScene.fxml"));
		root = loader.load();
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		/*
		LoginScrutatoreController controller = loader.getController();
		controller.setScenaPrecedente("homeScene.fxml", "Home");
		*/
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login Scrutatore");
		stage.show();
	}

	public void switchToLoginAdminScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginGestoreSistemaScene.fxml"));
		root = loader.load();
		DefaultSceneController.scenaPrecedente.add("homeScene.fxml");
		DefaultSceneController.scenaPrecedenteTitolo.add("Home");
		/*
		LoginGestoreSistemaController controller = loader.getController();
		controller.setScenaPrecedente("homeScene.fxml", "Home");
		*/
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login gestore di sistema");
		stage.show();
	}
}

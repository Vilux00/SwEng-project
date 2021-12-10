package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfiloElettoreController  extends DefaultSceneController{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	//da cambiare in quanto non funzionante
	//cercare come ottenere il nome del file FXML e il titolo dell
	private String FXML = "profiloElettoreScene.fxml";
	
	
	public void cambiaPassword(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("modificaPasswordScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		DefaultSceneController.scenaPrecedente.add(FXML);
		DefaultSceneController.scenaPrecedenteTitolo.add(stage.getTitle());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Modifica password");
		stage.show();	
	}
}

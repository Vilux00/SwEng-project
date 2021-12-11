package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloElettoreController extends DefaultSceneController{	
	
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreScene.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordScene.fxml", "Modifica password");
	}
	
}

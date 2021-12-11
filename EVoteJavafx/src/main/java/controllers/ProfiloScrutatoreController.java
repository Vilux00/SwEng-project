package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloScrutatoreController extends ProfiloElettoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreScene.fxml", "Profilo scrutatore");
		changeScene(event, "modificaPasswordScene.fxml", "Modifica password");
	}
}

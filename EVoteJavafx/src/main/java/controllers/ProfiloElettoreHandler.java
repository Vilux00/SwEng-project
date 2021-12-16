package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import model.SessioneDiVoto;

public class ProfiloElettoreHandler extends DefaultSceneHandler{
	
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password");
	}
	
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
	
}

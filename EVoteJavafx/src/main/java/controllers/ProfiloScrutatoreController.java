package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloScrutatoreController extends ProfiloElettoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreScene.fxml", "Profilo scrutatore");
		changeScene(event, "modificaPasswordScene.fxml", "Modifica password");
	}
	
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloScrutatoreScene.fxml", "Profilo scrutatore");
		changeScene(event, "registraElettoreASessioneScene.fxml", "Registra utente a sessione");
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreScene.fxml", "Profilo scrutatore");
		changeScene(event, "selezioneSessioneScene.fxml", "Selezione sessione");
	}
}

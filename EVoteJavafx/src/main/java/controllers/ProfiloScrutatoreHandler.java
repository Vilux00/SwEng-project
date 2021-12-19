package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloScrutatoreHandler extends ProfiloElettoreHandler{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password", data);
	}
	
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "registraElettoreASessioneView.fxml", "Registra utente a sessione");
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
}

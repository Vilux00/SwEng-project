package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloScrutatoreController extends ProfiloElettoreController{
	
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password");
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
	
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "registraElettoreASessioneView.fxml", "Registra utente a sessione");
	}
	
	public void inserisciVoto(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "selSessioneInserimVoto.fxml", "Selezione sessione", data);
	}
}

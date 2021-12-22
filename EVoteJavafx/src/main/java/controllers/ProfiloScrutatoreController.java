package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import model.Elettore;
import model.ElettoreHolder;

public class ProfiloScrutatoreController extends ProfiloElettoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password", data);
	}
	
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		changeScene(event, "registraElettoreASessioneView.fxml", "Registra utente a sessione", data);
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		ElettoreHolder.getInstance().setElettore((Elettore) data);
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione", data);
	}
	
	public void inserisciVoto(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloScrutatoreView.fxml", "Profilo scrutatore");
		ElettoreHolder.getInstance().setElettore((Elettore) data);
		changeScene(event, "selSessioneInserimVoto.fxml", "Selezione sessione", data);
	}
}

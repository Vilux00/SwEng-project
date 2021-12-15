package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloGestoreHandler extends ProfiloScrutatoreHandler{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password");
	}
	
	@Override
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "registraElettoreASessioneView.fxml", "Registra utente a sessione");
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
	
	public void goToRegistrazioneUtente(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "registrazioneUtenteView1.fxml", "Registrazione utente");
	}
	
	public void creaSessioneVoto(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "creazioneSessioneView.fxml", "Creazione sessione di voto");
	}
	
	public void visualizzaSessioni(ActionEvent event) throws IOException{
	}
	
	public void aggiungiCandidato(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "aggiungiCandidatoView.fxml", "Aggiungi candidato");
	}
}
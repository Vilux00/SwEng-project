package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import model.Elettore;
import model.ElettoreHolder;

public class ProfiloGestoreController extends ProfiloScrutatoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password", data);
	}
	
	@Override
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "registraElettoreASessioneView.fxml", "Registra utente a sessione", data);
	}
	
	@Override
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		ElettoreHolder.getInstance().setElettore((Elettore) data);
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione", data);
	}
	
	@Override
	public void inserisciVoto(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		ElettoreHolder.getInstance().setElettore((Elettore) data);
		changeScene(event, "selSessioneInserimVoto.fxml", "Selezione sessione", data);
	}
	
	public void registrazioneUtente(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "registrazioneUtenteView1.fxml", "Registrazione utente", data);
	}
	
	public void creaSessioneVoto(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "creazioneSessioneView.fxml", "Creazione sessione di voto", data);
	}
	
	public void visualizzaSessioni(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "visualizzaSessioniView.fxml", "Visualizza sessioni", data);
	}
	
	public void aggiungiCandidato(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreView.fxml", "Profilo gestore di sistema");
		changeScene(event, "aggiungiCandidatoView.fxml", "Aggiungi candidato", data);
	}
}

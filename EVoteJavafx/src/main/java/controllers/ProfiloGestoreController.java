package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfiloGestoreController extends ProfiloScrutatoreController{
	@Override
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreScene.fxml", "Profilo gestore di sistema");
		changeScene(event, "modificaPasswordScene.fxml", "Modifica password");
	}
	
	@Override
	public void registraElettoreASessione(ActionEvent event)  throws IOException {
		setScenaPrecedente("profiloGestoreScene.fxml", "Profilo gestore di sistema");
		changeScene(event, "registraElettoreASessioneScene.fxml", "Registra utente a sessione");
	}
	
	public void goToRegistrazioneUtente(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloGestoreScene.fxml", "Profilo gestore di sistema");
		changeScene(event, "registrazioneUtenteScene1.fxml", "Registrazione utente");
	}
	
	public void creaSessioneVoto(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreScene.fxml", "Profilo gestore di sistema");
		changeScene(event, "creazioneSessioneScene.fxml", "Creazione sessione di voto");
	}
	
	public void visualizzaSessioni(ActionEvent event) throws IOException{
	}
	
	public void aggiungiCandidato(ActionEvent event) throws IOException{
		setScenaPrecedente("profiloGestoreScene.fxml", "Profilo gestore di sistema");
		changeScene(event, "aggiungiCandidatoScene.fxml", "Aggiungi candidato");
	}
}

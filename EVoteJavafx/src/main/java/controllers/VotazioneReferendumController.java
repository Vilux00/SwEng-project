package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;

public class VotazioneReferendumController extends DefaultSceneController{
	public void setFavorevole(ActionEvent event) throws IOException{
		//aggiorna db
		//output visivo
		rimuoviScenaPrecedente();
		goToScenaPrecedente(event);
	}
	
	public void setContrario(ActionEvent event) throws IOException{
		//aggiorna db
		//output visivo
		rimuoviScenaPrecedente();
		goToScenaPrecedente(event);
	}
	
	
}

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;

public class VotazioneReferendumController extends DefaultSceneController{
	public void setFavorevole(ActionEvent event) throws IOException{
		rimuoviScenaPrecedente();
		goToScenaPrecedente(event);
		//output visivo
	}
	
	public void setContrario(ActionEvent event) throws IOException{
		rimuoviScenaPrecedente();
		goToScenaPrecedente(event);
		//output visivo
	}
	
	
}

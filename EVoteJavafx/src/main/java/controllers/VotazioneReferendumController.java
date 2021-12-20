package controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class VotazioneReferendumController extends DefaultSceneController{
	public void setFavorevole(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma votazione");
		alert.setTitle("Conferma votazione");
		alert.setContentText("Sicuro di voler votare 'favorevole' al referendum?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			//aggiorna db
			//output visivo
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}
	}
	
	public void setContrario(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma votazione");
		alert.setTitle("Conferma votazione");
		alert.setContentText("Sicuro di voler votare 'contrario' al referendum?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			//aggiorna db
			//output visivo
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}
	}
	
	public void setSchedaBianca(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		alert.setContentText("Sicuro di voler lasciare la scheda bianca?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			//aggiorna db
			//output visivo
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}
	}
	
	
}

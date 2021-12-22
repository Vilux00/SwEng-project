package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Elettore;
import model.LogVoto;
import model.LogVotoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;
import model.SessioneDiVotoHolder;
import model.Voto;
import model.VotoDao;

public class VotazioneReferendumController extends DefaultSceneController implements Initializable{
	
	@FXML private Label quesito;
	
	public void setFavorevole(ActionEvent event) throws IOException{
		Elettore e = (Elettore) data;
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Sicuro di voler votare 'favorevole' al referendum?");
		alert.setTitle("Conferma votazione");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if(!insertLog(s, e.getCodF())) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}else {
				Voto v = new Voto(s);
				v.setR_quesito(true);
				VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
				if(vd.inserisciVotoReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Votazione inserita con successo");
					alert.setTitle("Votazione inserita");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
				}else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Errore inserimento votazione");
					alert.setTitle("Errore");
					alert.show();
				}
			}
		}
	}
	
	public void setContrario(ActionEvent event) throws IOException{
		Elettore e = (Elettore) data;
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Sicuro di voler votare 'contrario' al referendum?");
		alert.setTitle("Conferma votazione");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if(!insertLog(s, e.getCodF())) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}else {
				Voto v = new Voto(s);
				v.setR_quesito(false);
				VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
				if(vd.inserisciVotoReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Votazione inserita con successo");
					alert.setTitle("Votazione inserita");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
				}else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Errore inserimento votazione");
					alert.setTitle("Errore");
					alert.show();
				}
			}
		}
	}
	
	public void setSchedaBianca(ActionEvent event) throws IOException{
		Elettore e = (Elettore) data;
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Sicuro di voler lasciare la scheda bianca?");
		alert.setTitle("Conferma votazione");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if(!insertLog(s, e.getCodF())) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}else {
				Voto v = new Voto(s);
				v.setR_quesito(null);
				VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
				if(vd.inserisciVotoReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Votazione inserita con successo");
					alert.setTitle("Votazione inserita");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
				}else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Errore inserimento votazione");
					alert.setTitle("Errore");
					alert.show();
				}
			}
		}
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}
	
	public boolean insertLog(SessioneDiVoto s, String codF) {
		LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
		return ld.inserisciLog(new LogVoto(s.getId(), codF));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	public void loadData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		String quesito = sd.getQuesito(SessioneDiVotoHolder.getInstance().getSessione());
		this.quesito.setText(quesito);
	}
	
}

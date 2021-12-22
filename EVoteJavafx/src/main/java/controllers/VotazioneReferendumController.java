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
import model.ElettoreHolder;
import model.LogVoto;
import model.LogVotoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;
import model.SessioneDiVotoHolder;
import model.Voto;
import model.VotoDao;

public class VotazioneReferendumController extends DefaultSceneController implements Initializable{
	
	@FXML private Label visualizzaQuesito;
	
	public void setFavorevole(ActionEvent event) throws IOException{
		setVoto("Favorevole", event);
	}
	
	public void setContrario(ActionEvent event) throws IOException{
		setVoto("Contrario", event);
	}
	
	public void setSchedaBianca(ActionEvent event) throws IOException{
		setVoto("Scheda bianca", event);
	}
	
	private void setVoto(String tipoVoto, ActionEvent event) throws IOException {
		Elettore e = ElettoreHolder.getInstance().getElettore();
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		switch (tipoVoto) {
			case "Favorevole":
				alert.setHeaderText("Sicuro di voler votare 'favorevole' al referendum?");
			case "Contrario":
				alert.setHeaderText("Sicuro di voler votare 'contrario' al referendum?");
			case "Scheda bianca":
				alert.setHeaderText("Sicuro di voler lasciare la scheda bianca?");
		}
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if(insertLog(s, e.getCodF())) {
				Voto v = new Voto(s);
				switch (tipoVoto) {
					case "Favorevole":
						v.setR_quesito(true);
					case "Contrario":
						v.setR_quesito(false);
					case "Scheda bianca":
						v.setR_quesito(null);
				}
				VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
				if(vd.inserisciVotoReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Votazione inserita con successo");
					alert.setTitle("Votazione inserita");
					alert.show();
					SessioneDiVotoHolder.getInstance().setSessione(null);
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
				}else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Errore inserimento votazione");
					alert.setTitle("Errore");
					alert.show();
				}
			}else {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();	
			}
		}
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
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
		this.visualizzaQuesito.setText(quesito);
	}
	
}

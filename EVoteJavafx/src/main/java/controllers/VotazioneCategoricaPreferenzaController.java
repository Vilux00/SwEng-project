package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Candidato;
import model.DaoFactory;
import model.ElettoreHolder;
import model.LogVoto;
import model.LogVotoDao;
import model.Partito;
import model.PartitoDao;
import model.SessioneDiVotoHolder;
import model.Voto;
import model.VotoDao;

public class VotazioneCategoricaPreferenzaController extends DefaultSceneController implements Initializable{
	
	@FXML private ComboBox<String> comboBoxPartiti;
	@FXML private ComboBox<String> comboBoxCandidati;
	@FXML private ComboBox<String> comboBoxCandidatiScelti;
	@FXML private Button conferma;
	private List<Partito> partiti;
	private List<Candidato> candidati;
	private List<Candidato> candidatiScelti;
	
	public void confermaVotazione(ActionEvent event) throws IOException{
		if(comboBoxCandidatiScelti.getItems().size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Non hai inserito alcun candidato di tua preferenza");
			alert.setTitle("Errore");
			alert.show();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String msg = "";
		for(Candidato c : candidatiScelti) msg += c.toString() + "\n";
		alert.setHeaderText("Conferma i dati inseriti");
		alert.setTitle("Conferma dati");
		alert.setContentText("Preferenze inserite: \n" + msg);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Voto v = new Voto(SessioneDiVotoHolder.getInstance().getSessione());
			VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
			LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
			LogVoto lv = new LogVoto(SessioneDiVotoHolder.getInstance().getSessione().getId(), ElettoreHolder.getInstance().getElettore().getCodF());
			// Hopefully short circuit evaluation does his job
			if((data != null && ((String)data).equals("SuperUser")) || ld.inserisciLog(lv)) {
				for(Partito p : partiti) if(p.toString().equals(comboBoxPartiti.getValue())) v.setPreferenze_partito(new int[] {p.getId()});
				int []l = new int[candidatiScelti.size()];
				for(int i = 0; i < candidatiScelti.size(); i++) l[i] = candidatiScelti.get(i).getId(); 
				v.setPreferenze_candidato(l);
				if(vd.inserisciVotoNonReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Preferenza inserita correttamente");
					alert.setTitle("Votazione completata");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
					SessioneDiVotoHolder.getInstance().setSessione(null);
					return;
				}
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Errore inserimento voto");
				alert.setTitle("Errore");
				alert.show();
			}
			else {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}
		}
	}

	public void setSchedaBianca(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Confermi di voler lasciare la scheda bianca?");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Voto v = new Voto(SessioneDiVotoHolder.getInstance().getSessione());
			VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
			LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
			if((data != null && ((String)data).equals("SuperUser")) || ld.inserisciLog(new LogVoto(SessioneDiVotoHolder.getInstance().getSessione().getId(), ElettoreHolder.getInstance().getElettore().getCodF()))){
				if(vd.inserisciVotoNonReferendum(v)) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Preferenza inserita correttamente");
					alert.setTitle("Votazione completata");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
					return;
				}
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Errore inserimento voto");
				alert.setTitle("Errore");
				alert.show();
			}
			else {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}
		}
	}
	
	public void aggiungiCandidato(ActionEvent event) {
		String candidato = comboBoxCandidati.getValue();
		if(candidato == null) return;
		Candidato candidatoAgg = null;
		for(Candidato c : candidati) 
			if(c.toString().equals(candidato)) candidatoAgg = c;
		switchCandidato(candidatoAgg, true);
		clearComboBox();
		updateComboBox();
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		String candidato = comboBoxCandidatiScelti.getValue();
		if(candidato == null) return;
		Candidato candidatoRem = null;
		for(Candidato c : candidatiScelti)
			if(c.toString().equals(candidato)) candidatoRem = c;
		switchCandidato(candidatoRem, false);
		clearComboBox();
		updateComboBox();
	}
	
	public void updateCandidati(ActionEvent event) {
		comboBoxCandidati.getItems().clear();
		comboBoxCandidatiScelti.getItems().clear();
		comboBoxCandidati.setDisable(false);
		comboBoxCandidatiScelti.setDisable(false);
		candidatiScelti.clear();
		conferma.setDisable(false);
		for(Partito p : partiti) {
			if(p.toString().equals(comboBoxPartiti.getValue())) {
				candidati.addAll(p.getCandidati());
				for(Candidato c : p) comboBoxCandidati.getItems().addAll(c.toString());
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		comboBoxCandidati.setDisable(true);
		comboBoxCandidatiScelti.setDisable(true);
		candidati = new ArrayList<>();
		candidatiScelti = new ArrayList<>();
		conferma.setDisable(true);
		PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
		partiti = pd.getPartiti(SessioneDiVotoHolder.getInstance().getSessione());
		for(Partito p : partiti) comboBoxPartiti.getItems().add(p.toString());
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
	
	private void switchCandidato(Candidato c, boolean b) {
		if(b) {
			candidati.remove(c);
			candidatiScelti.add(c);
			return;
		}
		candidatiScelti.remove(c);
		candidati.add(c);
	}
	
	private void clearComboBox() {
		comboBoxCandidati.getItems().clear();
		comboBoxCandidatiScelti.getItems().clear();
	}
	
	private void updateComboBox() {
		for(Candidato c : candidati) comboBoxCandidati.getItems().add(c.toString());
		for(Candidato c : candidatiScelti) comboBoxCandidatiScelti.getItems().add(c.toString());
	}
}

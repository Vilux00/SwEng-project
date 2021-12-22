package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import model.Candidato;
import model.DaoFactory;
import model.Partito;
import model.PartitoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;
import model.SessioneDiVotoHolder;

public class CreaVotazioneController extends DefaultSceneController implements Initializable{
	
	private ObservableList<Candidato> candidatiSceglibili = FXCollections.observableArrayList();
	private ObservableList<Candidato> candidatiScelti = FXCollections.observableArrayList();
	private ObservableList<String> listCandSceglibili = FXCollections.observableArrayList();
	private ObservableList<String> listCandScelti = FXCollections.observableArrayList();
	@FXML private ComboBox<String> comboBoxVincitore;
	@FXML private ComboBox<String> comboBoxCandidati;	
	@FXML private ComboBox<String> comboBoxCandidatiScelti;
	
	public void conferma(ActionEvent event) throws IOException{
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		s.setModVincitore(StringUtils.substringBetween(comboBoxVincitore.getValue(), "(", ")"));
		s.addCandidati(candidatiScelti);
		SessioneDiVotoDao se = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		if(!se.inserisciSessioneNonReferendum(s)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Errore creazione sessione");
			alert.setTitle("Qualcosa è andato storto");
			changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema");
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma dati");
			alert.setTitle("Conferma dati");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				rimuoviScenaPrecedente(2);
				changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema");
			}
		}
	}

	public void aggiungiCandidato(ActionEvent event) {
		String candidato = comboBoxCandidati.getValue();
		Candidato candidatoAgg = null;
		for(Candidato c : candidatiSceglibili) 
			if(c.toString().equals(candidato)) candidatoAgg = c;
		switchCandidato(candidatoAgg, true);
		clearComboBox();
		updateComboBox();
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		String candidato = comboBoxCandidatiScelti.getValue();
		Candidato candidatoRem = null;
		for(Candidato c : candidatiScelti)
			if(c.toString().equals(candidato)) candidatoRem = c;
		switchCandidato(candidatoRem, false);
		clearComboBox();
		updateComboBox();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		comboBoxVincitore.getItems().addAll("Maggioranza(MAG)", "Maggioranza assoluta(MAGA)");
		PartitoDao pa = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
		List<Partito> l = pa.getPartiti();
		for(Partito p : l) {
			for(Candidato c : p.getCandidati()) {
				candidatiSceglibili.add(c);
				listCandSceglibili.add(c.toString());
			}
		}
		comboBoxCandidati.getItems().addAll(listCandSceglibili);
	}
	
	private void clearComboBox() {
		comboBoxCandidati.getItems().clear();
		comboBoxCandidatiScelti.getItems().clear();
	}
	
	private void switchCandidato(Candidato c, boolean b) {
		if(b) {
			candidatiSceglibili.remove(c);
			listCandSceglibili.remove(c.toString());
			candidatiScelti.add(c);
			listCandScelti.add(c.toString());
			return;
		}
		candidatiScelti.remove(c);
		listCandScelti.remove(c.toString());
		candidatiSceglibili.add(c);
		listCandSceglibili.add(c.toString());
	}
	
	private void updateComboBox() {
		comboBoxCandidati.getItems().addAll(listCandSceglibili);
		comboBoxCandidatiScelti.getItems().addAll(listCandScelti);
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

}

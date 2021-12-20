package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import model.CandidatoDao;
import model.DaoFactory;
import model.Partito;
import model.PartitoDao;

public class CreaVotazioneController extends DefaultSceneController implements Initializable{
	
	private ObservableList<Candidato> candidatiSceglibili = FXCollections.observableArrayList();
	private ObservableList<Candidato> candidatiScelti = FXCollections.observableArrayList();
	private ObservableList<String> listCandSceglibili = FXCollections.observableArrayList();
	private ObservableList<String> listCandScelti = FXCollections.observableArrayList();
	@FXML private ComboBox<String> comboBoxVincitore;
	@FXML private ComboBox<String> comboBoxCandidati;	
	@FXML private ComboBox<String> comboBoxCandidatiScelti;
	
	public void conferma(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma i dati inseriti");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			rimuoviScenaPrecedente(2);
			changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema"); //da rivedere (sarebbe meglio avere un output visivo dell'avvenuta creazione
		}
	}

	public void aggiungiCandidato(ActionEvent event) {
		String candidato = comboBoxCandidati.getValue();
		List<Candidato> candidatiAdded = new ArrayList<>();
		for(Candidato c : candidatiSceglibili) 
			if(c.toString().equals(candidato))
				candidatiAdded.add(c);
		for(Candidato c : candidatiAdded) {
			candidatiSceglibili.remove(c);
			listCandSceglibili.remove(c.toString());
			candidatiScelti.add(c);
			listCandScelti.add(c.toString());
		}
		comboBoxCandidati.getItems().addAll(listCandSceglibili);
		comboBoxCandidatiScelti.getItems().addAll(listCandScelti);
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		String candidato = comboBoxCandidati.getValue();
		List<Candidato> candidatiRemoved = new ArrayList<>();
		for(Candidato c : candidatiScelti)
			if(c.toString().equals(candidato))
				candidatiRemoved.add(c);
		for(Candidato c : candidatiRemoved) {
			candidatiScelti.remove(c);
			listCandScelti.remove(c.toString());
			candidatiSceglibili.add(c);
			listCandSceglibili.add(c.toString());
		}
		comboBoxCandidati.getItems().addAll(listCandSceglibili);
		comboBoxCandidatiScelti.getItems().addAll(listCandScelti);
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		comboBoxVincitore.getItems().addAll("Maggioranza", "Maggioranza assoluta");
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
}

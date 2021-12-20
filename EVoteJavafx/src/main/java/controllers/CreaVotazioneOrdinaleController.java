package controllers;

import java.io.IOException;
import java.net.URL;
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

public class CreaVotazioneOrdinaleHandler extends DefaultSceneHandler implements Initializable{
	
	private ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML
    private ComboBox<String> comboBoxVincitore;

	@FXML
    private ComboBox<String> comboBoxCandidati;
	
	@FXML
    private ComboBox<String> comboBoxCandidatiScelti;
	
	public void conferma(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			rimuoviScenaPrecedente(2);
			changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema"); //da rivedere (sarebbe meglio avere un output visivo dell'avvenuta creazione
		}
	}

	public void aggiungiCandidato(ActionEvent event) {
	}
	
	public void rimuoviCandidato(ActionEvent event) {
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		//aggiunta candidati da db
	}
}

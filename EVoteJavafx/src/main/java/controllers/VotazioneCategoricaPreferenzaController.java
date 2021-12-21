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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class VotazioneCategoricaPreferenzaController extends DefaultSceneController implements Initializable{
	
	@FXML
    private ComboBox<String> comboBoxPartiti;

	@FXML
    private ComboBox<String> comboBoxCandidati;
	
	@FXML
    private ComboBox<String> comboBoxCandidatiScelti;
	
	public void confermaVotazione(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			//aggiorna db
			//output visivo
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event, data);
		}
	}

	public void aggiungiCandidato(ActionEvent event) {
		
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		
	}
	
	public void updateCandidati(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		//aggiunta partiti
	}
}

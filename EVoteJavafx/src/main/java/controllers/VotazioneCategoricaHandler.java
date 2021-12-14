package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class VotazioneCategoricaHandler extends DefaultSceneHandler implements Initializable{
	
	ObservableList<String> candidati = FXCollections.observableArrayList();
	ObservableList<String> partiti = FXCollections.observableArrayList();
	@FXML
    private ComboBox<String> comboBoxCandidatiPartiti;

    @FXML
    private Button buttonCandidati;

    @FXML
    private Button buttonPartiti;

	public void votaPerCandidati(ActionEvent event) throws IOException{
		comboBoxCandidatiPartiti.getItems().clear();
		comboBoxCandidatiPartiti.getItems().addAll(candidati);
		comboBoxCandidatiPartiti.setPromptText("Candidati");
	}
	
	public void votaPerPartiti(ActionEvent event) throws IOException{
		comboBoxCandidatiPartiti.getItems().clear();
		comboBoxCandidatiPartiti.getItems().addAll(partiti);
		comboBoxCandidatiPartiti.setPromptText("Partiti");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		//aggiunta partiti da db
		//aggiunta candidati da db
	}
	
	public void confermaVotazione(ActionEvent event) throws IOException{
		//aggiorna db
		//output visivo
		rimuoviScenaPrecedente();
		goToScenaPrecedente(event);
	}
}

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class CreazioneSessioneController extends DefaultSceneController implements Initializable{
	
	private ObservableList<Integer> ol = FXCollections.observableArrayList();
	private ObservableList<String> ol2 = FXCollections.observableArrayList();
	
 	@FXML
    private ComboBox<Integer> comboBoxAnno;

    @FXML
    private ComboBox<Integer> comboBoxGiorno;

    @FXML
    private ComboBox<String> comboBoxMese;
    
    @FXML
    private ComboBox<String> comboBoxVoto;
    
    @FXML
    private ComboBox<String> comboBoxVincitore;
    
	public void confermaVotazione(ActionEvent event) throws IOException{
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		for (int i = 1; i <= 31; i++) {
			ol.add(i);
		}
		comboBoxGiorno.getItems().addAll(ol);
		ol.clear();
		for (int i = 2021; i <= 2050; i++) {
			ol.add(i);
		}
		comboBoxAnno.getItems().addAll(ol);
		ol2.addAll("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre");
		comboBoxMese.getItems().addAll(ol2);
		ol2.clear();
		ol2.addAll("Voto ordinale", "Voto categorico", "Voto categorico con preferenze", "Referendum");
		comboBoxVoto.getItems().addAll(ol2);
		ol2.clear();
		ol2.addAll("Maggioranza", "Maggioranza assoluta", "Referendum senza quorum", "Referendum con quorum");
		comboBoxVoto.getItems().addAll(ol2);
		
		//aggiungi candidati
		
		
	}
	
	public void aggiungiCandidato(ActionEvent event) {
		if (comboBoxVincitore.)
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		
	}
}

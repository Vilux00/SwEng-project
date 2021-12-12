package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
	}
	
	public void conferma(ActionEvent event) throws IOException{
		setScenaPrecedente("creazioneSessioneScene.fxml", "Creazione sessione di voto");
		if(Objects.isNull(comboBoxVoto.getValue()) == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Selezionare il tipo di voto");
			alert.show();
			//pop
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma dati");
			alert.setTitle("Conferma dati");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if(comboBoxVoto.getValue().contains("Voto") == true) {
					changeScene(event, "votazioneClassicaScene.fxml", "Creazione sessione di voto");
				}else {
					changeScene(event, "referendumScene.fxml", "Creazione sessione di voto");
				}
			}
		}		
	}
}

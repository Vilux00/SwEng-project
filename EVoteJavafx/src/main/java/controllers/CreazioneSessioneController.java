package controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import model.SessioneDiVoto;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class CreazioneSessioneController extends DefaultSceneController implements Initializable{
	
	@FXML private TextField nome;
 	@FXML private ComboBox<Integer> comboBoxAnno;
    @FXML private ComboBox<Integer> comboBoxGiorno;
    @FXML private ComboBox<Integer> comboBoxMese;
    @FXML private ComboBox<String> comboBoxVoto;
    @FXML private ComboBox<Integer> comboBoxOrario;   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {	
		ObservableList<Integer> ol = FXCollections.observableArrayList();
		ObservableList<String> ol2 = FXCollections.observableArrayList();
		for (int i = 1; i <= 31; i++) ol.add(i);
		comboBoxGiorno.getItems().addAll(ol);
		ol.clear();
		for (int i = 2021; i <= 2050; i++) ol.add(i);
		comboBoxAnno.getItems().addAll(ol);
		ol.clear();
		for(int i = 1; i < 13; i++) ol.add(i);
		comboBoxMese.getItems().addAll(ol);
		ol.clear();
		for(int i = 0; i < 23; i+=2) ol.add(i);
		comboBoxOrario.getItems().addAll(ol);
		ol2.clear();
		ol2.addAll("Voto ordinale(ORD)", "Voto categorico(CAT)", "Voto categorico con preferenze(CATP)", "Referendum(REF)");
		comboBoxVoto.getItems().addAll(ol2);
	}
	
	public void conferma(ActionEvent event) throws IOException{
		String n = nome.getText();
		String v = comboBoxVoto.getValue();
		Integer gg = comboBoxGiorno.getValue();
		Integer mm = comboBoxMese.getValue();
		Integer yy = comboBoxAnno.getValue();
		Integer hh = comboBoxOrario.getValue();
		setScenaPrecedente("creazioneSessioneView.fxml", "Creazione sessione di voto");
		if(v == null || n == null || gg == null || mm == null || yy == null || hh == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campi mancanti");
			alert.setHeaderText("Alcuni campi non sono stati compilati");
			alert.show();
			rimuoviScenaPrecedente();
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma i dati inseriti");
			alert.setTitle("Conferma dati");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setContentText("Nome sessione: " + n +"\nTipologia sessione: " + v + "\nScadenza: "+ gg + "-" + mm + "-" + yy +  " Orario:" + hh);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				SessioneDiVoto s = new SessioneDiVoto(nome.getText(), StringUtils.substringBetween(v, "(", ")"));
				s.setScadenza(gg, mm, yy, hh, 0);
				if(comboBoxVoto.getValue().contains("categorico") == true) {
					changeScene(event, "creaVotazioneCategorica.fxml", "Creazione sessione di voto", s);
				}else if (comboBoxVoto.getValue().contains("Referendum") == true){
					changeScene(event, "creaReferendumView.fxml", "Creazione sessione di voto", s);
				}else {
					changeScene(event, "creaVotazioneOrdinaleView.fxml", "Creazione sessione di voto", s);
				}
			}
		}		
	}
}

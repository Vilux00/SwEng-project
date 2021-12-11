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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;


public class RegistrazioneUtente1Controller  extends DefaultSceneController implements Initializable{
		
	ObservableList list = FXCollections.observableArrayList();
	
 	@FXML
    private ComboBox<Integer> comboBoxAnno;
    @FXML
    private ComboBox<Integer> comboBoxGiorno;

    @FXML
    private ComboBox<String> comboBoxMese;

    @FXML
    private ChoiceBox<String> choiceBoxPrivilegio;

    @FXML
    private ChoiceBox<String> choiceBoxSesso;

	public void confermaDatiRegistrazione(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Conferma i dati inseriti per procedere");
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			setScenaPrecedente("registrazioneUtenteScene1.fxml", "Registrazione utente");
			changeScene(event, "registrazioneUtenteScene2.fxml", "Registrazione utente");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		for (int i = 1; i <= 31; i++) {
			list.add(i);
		}
		comboBoxGiorno.getItems().addAll(list);
		list.clear();
		for (int i = 1900; i <= 2021; i++) {
			list.add(i);
		}
		comboBoxAnno.getItems().addAll(list);
		list.clear();
		list.addAll("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre");
		comboBoxMese.getItems().addAll(list);
		list.clear();
		list.addAll("Maschio", "Femmina");
		choiceBoxSesso.getItems().addAll(list);
		list.clear();
		list.addAll("Elettore", "Scrutatore", "Gestore di sistema");
		choiceBoxPrivilegio.getItems().addAll(list);
	}
}

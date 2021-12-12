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

public class SelezioneSessioneController extends DefaultSceneController implements Initializable{
	
private ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML
    private ComboBox<String> comboBoxSessione;
	
	public void goToSessione(ActionEvent event) throws IOException{
		setScenaPrecedente("selezioneSessioneScene.fxml", "Selezione sessione");
		if(Objects.isNull(comboBoxSessione.getValue()) == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Selezionare la sessione");
			alert.show();
			//pop
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma sessione");
			alert.setTitle("Conferma sessione");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				
				//questo e' sbagliato ovviamente 
				//scopo e' il testing di interfaccie
				
				if(comboBoxSessione.getValue().equals("Voto ordinale") == true) {
					changeScene(event, "votazioneOrdinaleScene.fxml", "Votazione");
				}else if(comboBoxSessione.getValue().equals("Voto categorico") == true) {
					changeScene(event, "votazioneCategoricaScene.fxml", "Votazione");
				}else if(comboBoxSessione.getValue().equals("Voto categorico con preferenza") == true) {
					changeScene(event, "votazioneCategoricaPreferenzaScene.fxml", "Votazione");
				}else {
					changeScene(event, "votazioneReferendumScene.fxml", "Votazione");
				}
			}
		}	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}

	private void loadData() {
		list.addAll("Voto ordinale", "Voto categorico", "Voto categorico con preferenza", "Referendum");
		comboBoxSessione.getItems().addAll(list);
	}
}

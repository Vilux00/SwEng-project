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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.DaoFactory;
import model.SessioneDiVoto;
import model.SessioneDiVotoDaoImpl;
import javafx.scene.control.Alert.AlertType;

public class CreaReferendumHandler extends DefaultSceneHandler implements Initializable{

	private ObservableList<String> list = FXCollections.observableArrayList();
	@FXML private TextArea quesito;
	@FXML private ComboBox<String> comboBoxVincitore;
	
	public void conferma(ActionEvent event) throws IOException{
		if(quesito.getText() == null || comboBoxVincitore.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Alcuni campi non sono stati compilati");
			alert.setTitle("Campi non completati");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma i dati inseriti");
			alert.setTitle("Conferma dati");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setContentText("Quesito: " + quesito.getText() + "\nModalita' di voto: " + comboBoxVincitore.getValue());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				SessioneDiVoto s = (SessioneDiVoto) data;
				s.setModVincitore(StringUtils.substringBetween(comboBoxVincitore.getValue(), "(", ")"));
				s.setQuesito(quesito.getText());
				SessioneDiVotoDaoImpl se = (SessioneDiVotoDaoImpl) DaoFactory.getInstance().getDao("SessioneDiVoto");
				if(se.inserisciSessioneReferendum(s)) {
					alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Sessione di voto referendum creata con successo");
					alert.setTitle("Sessione creata");
					alert.show();
					rimuoviScenaPrecedente(2);
					changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema"); //da rivedere (sarebbe meglio avere un output visivo dell'avvenuta creazione
				}
				else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Qualcosa è andato storto, riavvia la applicazione");
					alert.setTitle("Errore");
					alert.show();
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		list.addAll("Referendum senza quorum(REFQ)", "Referendum con quorum(REF)");
		comboBoxVincitore.getItems().addAll(list);
	}
	
}

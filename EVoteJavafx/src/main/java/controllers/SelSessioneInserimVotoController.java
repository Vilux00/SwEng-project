package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;
import model.SessioneDiVotoHolder;

public class SelSessioneInserimVotoController extends DefaultSceneController implements Initializable{
	private List<SessioneDiVoto> list;
	@FXML private ComboBox<String> comboBoxSessione;
	
	public void goToSessione(ActionEvent event) throws IOException{
		setScenaPrecedente("selezioneSessioneView.fxml", "Selezione sessione");
		if(Objects.isNull(comboBoxSessione.getValue()) == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Selezionare la sessione");
			alert.show();
		}else {
			for (SessioneDiVoto s : list) {
				if (s.toString().equals(comboBoxSessione.getValue())) {
					SessioneDiVotoHolder.getInstance().setSessione(s);
					if (s.getModalitaVoto().equals("REF")) {
						changeScene(event, "votazioneReferendumView.fxml", "Votazione", "SuperUser");
					}else if (s.getModalitaVoto().equals("ORD")) {
						changeScene(event, "votazioneOrdinaleView.fxml", "Votazione", "SuperUser");
					}else if (s.getModalitaVoto().equals("CAT")) {
						changeScene(event, "votazioneCategoricaView.fxml", "Votazione", "SuperUser");
					}else {
						changeScene(event, "votazioneCategoricaPreferenzaView.fxml", "Votazione", "SuperUser");
					}
				}
			}
		}	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}

	public void loadData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
	    list = sd.getSessioni();
	    for (SessioneDiVoto s : sd.getSessioni()) comboBoxSessione.getItems().addAll(s.toString());
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

}

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.DaoFactory;
import model.LogVoto;
import model.LogVotoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class RegistraElettoreASessioneController extends DefaultSceneController implements Initializable{

    @FXML private ComboBox<String> ComboBoxSessioni;
    @FXML private TextField TextFieldCodFisc;
    List<SessioneDiVoto> l = new ArrayList<>();

    @FXML
    void aggiungiElettoreASessione(ActionEvent event) {
    	String codF = TextFieldCodFisc.getText();
    	String sessione = ComboBoxSessioni.getValue();
    	LogVoto l = new LogVoto(Integer.parseInt(StringUtils.substringBetween(sessione, "(", ")")), codF);
    	LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
    	ld.inserisciLog(l);
    }

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		SessioneDiVotoDao se = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		List<SessioneDiVoto> l = se.getSessioni();
		for(SessioneDiVoto s : l) ComboBoxSessioni.getItems().add(s.toString());
	}

}

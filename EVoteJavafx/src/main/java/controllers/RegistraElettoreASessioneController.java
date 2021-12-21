package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.DaoFactory;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class RegistraElettoreASessioneController extends DefaultSceneController implements Initializable{

    @FXML private ComboBox<String> ComboBoxSessioni;
    @FXML private TextField TextFieldCodFisc;

    @FXML
    void aggiungiElettoreASessione(ActionEvent event) {
    	String codF = TextFieldCodFisc.getText();
    	
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
		
	}

}

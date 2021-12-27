package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.DaoFactory;
import model.ElettoreDao;
import model.LogVoto;
import model.LogVotoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class RegistraElettoreASessioneController extends DefaultSceneController implements Initializable{

    @FXML private ComboBox<String> ComboBoxSessioni;
    @FXML private TextField TextFieldCodFisc;

    @FXML
    public void aggiungiElettoreASessione(ActionEvent event) {
    	String codF = TextFieldCodFisc.getText().replace(" ", "");
    	String sessione = ComboBoxSessioni.getValue();
    	
    	ElettoreDao el = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
    	if(!el.isIn(codF)) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Errore codice fiscale");
    		alert.setHeaderText("Il codice fiscale inserito non è presente tra quelli registrati");
    		alert.show();
    		clearFields();
    	}
    	else {
    		LogVoto l = new LogVoto(Integer.parseInt(StringUtils.substringBetween(sessione, "(", ")")), codF);
    		LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Conferma dati");
			alert.setTitle("Conferma dati");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setContentText("Registrazione di: " + codF + "\nalla votazione: " + sessione);
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if(ld.inserisciLog(l)) {
	    			alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Inserimento avvenuto con successo");
	        		alert.setHeaderText("Hai inserito correttamente la presenza alla votazione");
	        		alert.show();
	        		clearFields();
	    		}
				else {
					alert = new Alert(AlertType.ERROR);
	        		alert.setTitle("Qualcosa è andato storto");
	        		alert.setHeaderText("Errore inserimento presenza a votazione");
	        		alert.show();
	        		clearFields();
				}
			}
    	}
    }

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		SessioneDiVotoDao se = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		List<SessioneDiVoto> sessioni = se.getSessioni();
		for(SessioneDiVoto s : sessioni) ComboBoxSessioni.getItems().add(s.toString());
	}
	
	private void clearFields() {
		TextFieldCodFisc.setText("");
		ComboBoxSessioni.setValue("");
	}

}

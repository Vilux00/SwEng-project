package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistraElettoreASessioneController extends DefaultSceneController{

    @FXML
    private ComboBox<String> ComboBoxSessioni;

    @FXML
    private TextField TextFieldCodFisc;

    @FXML
    void aggiungiElettoreASessione(ActionEvent event) {
    	
    }

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}

}

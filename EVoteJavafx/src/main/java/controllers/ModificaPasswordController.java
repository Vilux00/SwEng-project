package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import main.Password;

public class ModificaPasswordController extends DefaultSceneController{
	@FXML
	PasswordField vecchiaPassword;
	@FXML
	PasswordField nuovaPassword1;
	@FXML
	PasswordField nuovaPassword2;
	
	public void modificaPassword(ActionEvent event) throws IOException {
		if(!vecchiaPassword.getText().isEmpty() && checkOldPassword() == true){
			if(nuovaPassword1.getText().equals(nuovaPassword2.getText())) {
				if(Password.checkPassword((nuovaPassword1.getText()))) {
					changeToNewPassword();
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Le nuove password che hai inserito non sono uguali");
				alert.setTitle("Password non uguali");
				alert.show();
			}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La vecchia password inserita e' incorretta");
			alert.setTitle("Vecchia password incorretta");
			alert.show();
		}
	}
	
	private boolean checkOldPassword() {
		//controllo nel db
		return false;
	}
	
	private void changeToNewPassword() {
		//chiamata al db
		if(true/*password gia presente*/) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La nuova password e' identica alla precedente");
			alert.setTitle("Password identiche");
			alert.show();
		}else {
			//aggiungi 
		}
	}

}

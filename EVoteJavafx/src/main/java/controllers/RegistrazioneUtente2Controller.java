package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Password;

public class RegistrazioneUtente2Controller  extends DefaultSceneController{
	
	private String password;
	
	@FXML
	private TextField visualizzaPassword;
	
	public void generaPassword(ActionEvent event) {
		password = Password.generateRandomPassword();
		visualizzaPassword.setText(password);
	}
	
	public void registraUtenteDB() {
		//chiamata al db
	}
}
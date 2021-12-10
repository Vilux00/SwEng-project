package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.Password;

public class RegistrazioneUtente2Controller  extends DefaultSceneController{
	
	private String password;
	
	@FXML
	private TextField visualizzaPassword;
	
	@FXML
	private HBox boxCreaAccount;
	
	@FXML
	private HBox stampaPdfAccount;
	
	@FXML
	private Button btnGeneraPassword;
	
	public void generaPassword(ActionEvent event) {
		password = Password.generateRandomPassword();
		visualizzaPassword.setText(password);
		boxCreaAccount.setVisible(true);
	}
	
	public void registraUtenteDB(ActionEvent event) {
		btnGeneraPassword.setDisable(false);   //da sistemare
		//chiamata al database
		stampaPdfAccount.setVisible(true);
	}
	
	public void stampaCredenzialiUtente(ActionEvent event) {
		System.out.println("Stampa credenziali utente");
	}
}
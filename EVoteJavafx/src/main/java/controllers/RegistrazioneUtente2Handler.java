package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.DaoFactory;
import model.Elettore;
import model.NuovoUtente;
import model.Password;
import model.NuovoUtenteDaoImpl;

public class RegistrazioneUtente2Handler extends DefaultSceneHandler{
	private String password;
	@FXML
	private TextField visualizzaPassword;
	@FXML
	private HBox boxCreaAccount;
	@FXML
	private HBox stampaPdfAccount;
	@FXML
	private Button btnGeneraPassword;
	@FXML
	private Button btnCreaAccount;
	
	public void generaPassword(ActionEvent event) {
		password = Password.generateRandomPassword();
		visualizzaPassword.setText(password);
		boxCreaAccount.setVisible(true);
	}
	
	public void registraUtenteDB(ActionEvent event) {
		NuovoUtente n = (NuovoUtente)data;
		n.setPassword(password);
		btnGeneraPassword.setDisable(true);
		NuovoUtenteDaoImpl nu = (NuovoUtenteDaoImpl)DaoFactory.getInstance().getDao("NuovoUtente");
		if(nu.register(n));
		btnCreaAccount.setDisable(true);
		stampaPdfAccount.setVisible(true);
	}
	
	public void stampaCredenzialiUtente(ActionEvent event) {
		Elettore e = (Elettore)data;
		System.out.println("Codice fiscale: " + e.getCodF() + ", password: " + e.getPassword());
	}
}
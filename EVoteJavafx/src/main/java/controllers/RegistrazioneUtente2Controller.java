package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import model.DaoFactory;
import model.NuovoUtente;
import model.Password;
import model.NuovoUtenteDaoImpl;

public class RegistrazioneUtente2Controller extends DefaultSceneController{
	private String password;
	@FXML private TextField visualizzaPassword;
	@FXML private HBox boxCreaAccount;
	@FXML private HBox stampaPdfAccount;
	@FXML private Button btnGeneraPassword;
	@FXML private Button btnCreaAccount;
	
	public void generaPassword(ActionEvent event) {
		password = Password.generateRandomPassword();
		visualizzaPassword.setText(password);
		boxCreaAccount.setVisible(true);
	}
	
	public void registraUtenteDB(ActionEvent event) throws IOException {
		Object []objArr = (Object[])data;
		NuovoUtente n = (NuovoUtente)objArr[1];
		n.setPassword(password);
		btnGeneraPassword.setDisable(true);
		NuovoUtenteDaoImpl nu = (NuovoUtenteDaoImpl)DaoFactory.getInstance().getDao("NuovoUtente");
		if(!nu.register(n)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Utente gia' registrato");
			alert.setTitle("Errore");
			alert.show();
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}else {
			btnCreaAccount.setDisable(true);
			stampaPdfAccount.setVisible(true);
		}
	}
	
	public void stampaCredenzialiUtente(ActionEvent event) throws IOException{
		Object []objArr = (Object[])data;
		NuovoUtente n = (NuovoUtente)objArr[1];
		System.out.println("Codice fiscale: " + n.getCodF() + ", password: " + n.getPassword());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Registrazione avvenuta con successo");
		alert.setTitle("");
		alert.show();
		rimuoviScenaPrecedente(2);
		changeScene(event, "profiloGestoreView.fxml", "Home profilo", objArr[0]);
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		Object []objArr = (Object[])data;
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), objArr[0]);
	}
}
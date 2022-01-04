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
import model.NuovoUtenteDao;
import model.NuovoUtenteHolder;

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
		NuovoUtente n = NuovoUtenteHolder.getInstance().getUtente();
		n.setPassword(password);
		btnGeneraPassword.setDisable(true);
		NuovoUtenteDao nu = (NuovoUtenteDao)DaoFactory.getInstance().getDao("NuovoUtente");
		if(!nu.register(n)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Utente gia' registrato");
			alert.setTitle("Errore");
			alert.show();
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
			NuovoUtenteHolder.getInstance().setUtente(null);
		}else {
			btnCreaAccount.setDisable(true);
			stampaPdfAccount.setVisible(true);
		}
	}
	
	public void stampaCredenzialiUtente(ActionEvent event) throws IOException{
		NuovoUtente n = NuovoUtenteHolder.getInstance().getUtente();
		System.out.println("Codice fiscale: " + n.getCodF() + ", password: " + n.getPassword());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Registrazione avvenuta con successo");
		alert.setTitle("");
		alert.show();
		rimuoviScenaPrecedente(2);
		changeScene(event, "profiloGestoreView.fxml", "Home profilo");
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
}
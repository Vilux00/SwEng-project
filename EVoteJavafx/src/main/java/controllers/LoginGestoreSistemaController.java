package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreHolder;
import model.Gestore;
import model.GestoreDao;

public class LoginGestoreSistemaController extends DefaultSceneController{
	@FXML private TextField codF;
	@FXML private TextField password;
	
	public void login(ActionEvent event) throws IOException {
		String codFisc = codF.getText().replaceAll(" ", "");;
		String pwd = password.getText();

		if(codFisc.equals("") || pwd.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Alcuni campi non sono compilati");
			alert.setTitle("Campi mancanti");
			alert.show();
			return;
		}
		Elettore e = new Gestore(codFisc, pwd);
		GestoreDao ge = (GestoreDao)DaoFactory.getInstance().getDao("Gestore");
		if(ge.login(e)){
			ElettoreHolder.getInstance().setElettore(e);
			setScenaPrecedente("loginGestoreSistemaView.fxml", "Login gestore"); 
			changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema");
			DefaultSceneController.isLogged = true;
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Credenziali non corrette");
			alert.setTitle("Autenticazione fallita");
			alert.show();
		}		
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, "homeView.fxml", "Home");
	}
}

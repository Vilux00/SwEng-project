package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Elettore;
import model.Gestore;
import model.GestoreDaoImpl;

public class LoginGestoreSistemaHandler extends DefaultSceneHandler{
	@FXML
	private TextField codF;
	@FXML
	private TextField password;
	
	public void login(ActionEvent event) throws IOException {
		String codFisc = codF.getText();
		String pwd = password.getText();

		if(codFisc.equals("") || pwd.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Alcuni campi non sono compilati");
			alert.setTitle("Campi mancanti");
			alert.show();
			return;
		}
		Elettore e = new Gestore(codFisc, pwd);
		GestoreDaoImpl ge = (GestoreDaoImpl)DaoFactory.getInstance().getDao("Gestore");
		if(ge.login(e)){
			setScenaPrecedente("loginGestoreSistemaView.fxml", "Login gestore"); 
			changeScene(event, "profiloGestoreView.fxml", "Profilo gestore di sistema", e);
			DefaultSceneHandler.isLogged = true;
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Credenziali non corrette");
			alert.setTitle("Autenticazione fallita");
			alert.show();
		}		
	}
}

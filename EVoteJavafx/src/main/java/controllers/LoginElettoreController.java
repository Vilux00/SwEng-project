package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreDaoImpl;

public class LoginElettoreController extends DefaultSceneController{	
	@FXML
	private TextField codF;
	@FXML
	private TextField password;

	public void login(ActionEvent event) throws IOException {
		String codFisc = codF.getText().replaceAll(" ", "");
		String pwd = password.getText();

		if(codFisc.equals("") || pwd.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Alcuni campi non sono compilati");
			alert.setTitle("Campi mancanti");
			alert.show();
			return;
		}
		Elettore e = new Elettore(codFisc, pwd);
		ElettoreDaoImpl ed = (ElettoreDaoImpl) DaoFactory.getInstance().getDao("Elettore");
		if(ed.login(e)){
			setScenaPrecedente("loginElettoreView.fxml", "Login elettore"); 
			changeScene(event, "profiloElettoreView.fxml", "Home profilo", e);
			DefaultSceneController.isLogged = true;
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Credenziali non corrette");
			alert.setTitle("Autenticazione fallita");
			alert.show();
		}			
	}
	
	
}
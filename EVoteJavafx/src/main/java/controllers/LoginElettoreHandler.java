package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Elettore;
import model.ElettoreDaoImpl;

public class LoginElettoreHandler extends DefaultSceneHandler{	
	
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
		Elettore e = new Elettore(codFisc, pwd);
		System.out.println(e.getCodF());
		if(new ElettoreDaoImpl().login(e)){
			setScenaPrecedente("loginElettoreView.fxml", "Login elettore"); 
			changeScene(event, "profiloElettoreView.fxml", "Home profilo", e);
			DefaultSceneHandler.isLogged = true;
		}else {
			new Alert(AlertType.INFORMATION).show();
		}		
	}
	
	
}

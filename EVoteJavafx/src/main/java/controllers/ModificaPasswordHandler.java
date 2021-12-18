package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreDaoImpl;
import model.Password;

public class ModificaPasswordHandler extends DefaultSceneHandler{
	@FXML
	PasswordField vecchiaPassword;
	@FXML
	PasswordField nuovaPassword1;
	@FXML
	PasswordField nuovaPassword2;
	
	public void modificaPassword(ActionEvent event) throws IOException {
		String oldPw = vecchiaPassword.getText();
		String newPw1 = nuovaPassword1.getText();
		String newPw2 = nuovaPassword2.getText();
		Elettore e = (Elettore)data;

		if(oldPw.equals("") || newPw1.equals("") || newPw2.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campi vuoti");
			alert.setHeaderText("Riempire tutti i campi");
			alert.show();
		}
		else if(!checkOldPassword()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Password incorretta");
			alert.setHeaderText("La vecchia password è incorretta");
			alert.show();
		}
		else if(!newPw1.equals(newPw2)){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Password non conincidenti");
			alert.setHeaderText("I campi nuova password non coincidono");
			alert.show();
		}
		else if(!Password.checkPassword(newPw1)){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Password non conforme");
			alert.setHeaderText("La nuova password non rispetta i requisiti");
			alert.show();
		}
		else if(newPw1.equals(e.getPassword())){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La nuova password e' identica alla precedente");
			alert.setTitle("Password identiche");
			alert.show();
		}
		else{
			if(changePasswordDB()){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Password modificata con successo");
				alert.setTitle("Password modificata");
				alert.show();
				goToScenaPrecedente(event, e);
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Qualcosa è andato storto, riavviare la applicazione");
				alert.setTitle("Ops...");
				alert.show();
			}
		}
	}
	
	private boolean checkOldPassword() {
		Elettore e = (Elettore)data;
		return e.getPassword().equals(vecchiaPassword.getText());
	}
	
	private boolean changePasswordDB() {
		Elettore e = (Elettore)data;
		e.setPassword(nuovaPassword1.getText());
		ElettoreDaoImpl el = (ElettoreDaoImpl)DaoFactory.getInstance().getDao("Elettore");
		return(el.updatePassword(e));
	}
}

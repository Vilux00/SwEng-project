package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.Password;

public class ModificaPasswordController extends DefaultSceneController{
	@FXML
	PasswordField vecchiaPassword;
	@FXML
	PasswordField nuovaPassword1;
	@FXML
	PasswordField nuovaPassword2;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void modificaPassword(ActionEvent event) throws IOException {
		if(!vecchiaPassword.getText().isEmpty() && checkOldPassword() == true){
			if(nuovaPassword1.getText().equals(nuovaPassword2.getText())) {
				if(Password.checkPassword((nuovaPassword1.getText()))) {
					changeToNewPassword();
				}
				/*
				 * In base al privilegio si torna indietro
				 * changeScene(event);
				 */
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Le nuove password che hai inserito non sono uguali");
				alert.setTitle("Password non uguali");
				alert.show();
			}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La vecchia password inserita e' incorretta");
			alert.setTitle("Vecchia password incorretta");
			alert.show();
		}
	}
	
	private boolean checkOldPassword() {
		//controllo nel db
		return false;
	}
	
	private void changeToNewPassword() {
		//chiamata al db
		if(true/*password gia presente*/) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La nuova password e' identica alla precedente");
			alert.setTitle("Password identiche");
			alert.show();
		}else {
			//aggiungi 
		}
	}
	
	/*
	private void changeScene(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getClassLoader().getResource("modificaPasswordScene.fxml"));  //devo andare indietro in base al chiamante
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Modifica password");
		stage.show();	
	}
	*/
}

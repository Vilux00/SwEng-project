package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Candidato;
import model.CandidatoDao;
import model.CandidatoDaoImpl;
import model.DaoFactory;
import model.Partito;

public class InserimentoCandidatoController  extends DefaultSceneController{
	@FXML private TextField nome;
	@FXML private TextField cognome;
	@FXML private TextField partito;
	
	public void inserimentoCandidato(ActionEvent event) throws IOException{
		String n = nome.getText();
		String c = cognome.getText();
		String p = partito.getText();
		if(n.equals("") || c.equals("") || p.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Alcuni campi sono vuoti");
			alert.setTitle("Errore");
			alert.show();
			return;
		}
		Candidato ca = new Candidato(n, c);
		CandidatoDao cd = (CandidatoDaoImpl) DaoFactory.getInstance().getDao("Candidato");
		if(cd.inserisciCandidato(ca, new Partito(p))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Inserimento candidato");
			alert.setHeaderText("Candidato inserito correttamente");
			alert.show();
			resetCampi();
		}
	}

	private void resetCampi() {
		nome.setText("");
		cognome.setText("");
		partito.setText("");
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
}

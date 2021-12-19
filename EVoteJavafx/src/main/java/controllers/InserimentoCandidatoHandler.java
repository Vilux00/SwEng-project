package controllers;

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

public class InserimentoCandidatoHandler  extends DefaultSceneHandler{
	@FXML TextField nome;
	@FXML TextField cognome;
	@FXML TextField partito;
	
	public void inserimentoCandidato(ActionEvent event) {
		String n = nome.getText();
		String c = cognome.getText();
		String p = partito.getText();
		Candidato ca = new Candidato(n, c);
		CandidatoDao cd = (CandidatoDaoImpl) DaoFactory.getInstance().getDao("Candidato");
		if(cd.inserisciCandidato(ca, new Partito(nome.getText()))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Inserimento candidato");
			alert.setHeaderText("Candidato inserito correttamente");
			alert.show();
		}
	}
}

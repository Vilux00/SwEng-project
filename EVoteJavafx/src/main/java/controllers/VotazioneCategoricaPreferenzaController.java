package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import model.DaoFactory;
import model.Partito;
import model.PartitoDao;
import model.SessioneDiVotoHolder;

public class VotazioneCategoricaPreferenzaController extends DefaultSceneController implements Initializable{
	
	@FXML private ComboBox<String> comboBoxPartiti;
	@FXML private ComboBox<String> comboBoxCandidati;
	@FXML private ComboBox<String> comboBoxCandidatiScelti;
	
	public void confermaVotazione(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma dati");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			//aggiorna db
			//output visivo
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}
	}

	public void setSchedaBianca(ActionEvent event) {
		
	}
	public void aggiungiCandidato(ActionEvent event) {
		
	}
	
	public void rimuoviCandidato(ActionEvent event) {
		
	}
	
	public void updateCandidati(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
		List<Partito> l = pd.getPartiti(SessioneDiVotoHolder.getInstance().getSessione());
		for(Partito p : l) {
			System.out.println(p);
			comboBoxPartiti.getItems().addAll(p.toString());
			
		}
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}
}

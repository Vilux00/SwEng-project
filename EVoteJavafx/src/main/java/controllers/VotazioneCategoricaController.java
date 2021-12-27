package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import model.Candidato;
import model.CandidatoDao;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreHolder;
import model.LogVoto;
import model.LogVotoDao;
import model.Partito;
import model.PartitoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoHolder;
import model.Voto;
import model.VotoDao;

public class VotazioneCategoricaController extends DefaultSceneController implements Initializable{
	
	private ObservableList<String> candidati = FXCollections.observableArrayList();
	private ObservableList<String> partiti = FXCollections.observableArrayList();
	private List<Candidato> candidatiL;
	private List<Partito> partitiL;
	@FXML private ComboBox<String> comboBoxCandidatiPartiti;
    @FXML private Button buttonCandidati;
    @FXML private Button buttonPartiti;
    @FXML private Button buttonConferma;

	public void votaPerCandidati(ActionEvent event) throws IOException{
		comboBoxCandidatiPartiti.setVisible(true);
		buttonConferma.setVisible(true);
		comboBoxCandidatiPartiti.getItems().clear();
		comboBoxCandidatiPartiti.getItems().addAll(candidati);
		comboBoxCandidatiPartiti.setPromptText("Candidati");
	}
	
	public void votaPerPartiti(ActionEvent event) throws IOException{
		comboBoxCandidatiPartiti.setVisible(true);
		buttonConferma.setVisible(true);
		comboBoxCandidatiPartiti.getItems().clear();
		comboBoxCandidatiPartiti.getItems().addAll(partiti);
		comboBoxCandidatiPartiti.setPromptText("Partiti");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		CandidatoDao cd = (CandidatoDao) DaoFactory.getInstance().getDao("Candidato");
		PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
		
		candidatiL = cd.getCandidatiSessione(s);
		partitiL = pd.getPartiti(s); 
		for(Candidato c : candidatiL) candidati.add(c.toString());
		for(Partito p : partitiL) partiti.add(p.toString());
	}
	
	public void confermaVotazione(ActionEvent event) throws IOException{
		String preferenza = comboBoxCandidatiPartiti.getValue();
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		Elettore e = ElettoreHolder.getInstance().getElettore();
		PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
	
		if(comboBoxCandidatiPartiti.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Inserisci una preferenza");
			alert.setTitle("Errore");
			alert.show();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma di voler votare per: " + preferenza + "?");
		alert.setTitle("Conferma dati");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			// Hopefully short circuit evaluation does his job
			if((data != null && ((String)data).equals("SuperUser")) || insertLog(s, e.getCodF())) {
				if(preferenza.contains("(")) {
					Voto v = new Voto(s);
					for(Candidato c : candidatiL) 
						if(c.toString().equals(preferenza)) {
							v.setPreferenze_candidato(new int[]{c.getId()});
							VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
							vd.inserisciVotoNonReferendum(v);
							break;
						}	
				}else {
					Voto v = new Voto(s);
					v.setPreferenze_partito(new int[]{pd.getId(new Partito(preferenza))});
					VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
					vd.inserisciVotoNonReferendum(v);
				}
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Preferenza inserita correttamente");
				alert.setTitle("Votazione completata");
				alert.show();
				rimuoviScenaPrecedente();
				goToScenaPrecedente(event);
				SessioneDiVotoHolder.getInstance().setSessione(null);
			}else {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}		
		}
	}
	
	public void setSchedaBianca(ActionEvent event) throws IOException{
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		VotoDao v = (VotoDao) DaoFactory.getInstance().getDao("Voto");
		Elettore e = ElettoreHolder.getInstance().getElettore();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Conferma di voler lasciare scheda bianca?");
		alert.setTitle("Conferma");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if((data != null && ((String)data).equals("SuperUser")) || insertLog(s, e.getCodF())) {
				if(v.inserisciVotoNonReferendum(new Voto(s))) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Preferenza inserita correttamente");
					alert.setTitle("Votazione completata");
					alert.show();
					rimuoviScenaPrecedente();
					goToScenaPrecedente(event);
				}
				else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Qualcosa è andato storto");
					alert.setTitle("Errore");
					alert.show();
				}			
			}else {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Impossibile registrare il voto per la sessione");
				alert.setTitle("Errore");
				alert.show();
			}
		}
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
	
	public boolean insertLog(SessioneDiVoto s, String codF) {
		LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
		return ld.inserisciLog(new LogVoto(s.getId(), codF));
	}
}

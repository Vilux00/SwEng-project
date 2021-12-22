package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Candidato;
import model.CandidatoDaoImpl;
import model.DaoFactory;
import model.Partito;
import model.PartitoDaoImpl;
import model.SessioneDiVoto;
import model.SessioneDiVotoHolder;

public class VotazioneOrdinaleController extends DefaultSceneController implements Initializable{

	private List<Candidato> candidati;
	private List<Partito> partiti;
	
	@FXML private Button bottoneConferma;
	@FXML private Label label1;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private Label label4;
	@FXML private Label label5;
	@FXML private TextField textField1;
	@FXML private TextField textField2;
	@FXML private TextField textField3;
	@FXML private TextField textField4;
	@FXML private TextField textField5;
	@FXML private VBox vBoxElenco;
	@FXML private Button votaPerPartiti;
	
	public void confermaVotazione(ActionEvent event) throws IOException {

	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}

	public void setSchedaBianca(ActionEvent event) {

	}

	public void votaPerCandidati(ActionEvent event) {
		setVisible();
	}

	public void votaPerPartiti(ActionEvent event) {
		setVisible();
		
	}

	private void setVisible() {
		vBoxElenco.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		CandidatoDaoImpl cDao = (CandidatoDaoImpl)DaoFactory.getInstance().getDao("Candidato");
		PartitoDaoImpl pDao = (PartitoDaoImpl)DaoFactory.getInstance().getDao("Partito");
		candidati = cDao.getCandidatiSessione(s);
		partiti = pDao.getPartiti(s);
	}
}
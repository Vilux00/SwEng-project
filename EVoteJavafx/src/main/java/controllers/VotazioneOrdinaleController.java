package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private List<TextField> listTextField;
	
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
	
	private char tipoElenco;
	
	public void confermaVotazione(ActionEvent event) throws IOException {
		if (!checkValidita()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Errore inserimento votazione");
			alert.setTitle("Errore");
			alert.setContentText("Numera correttamente i candidati/partiti scelti");
			alert.show();
		}
		//inserimento
	}

	
	private boolean checkValidita() {
		List <String> l = new ArrayList<>();
		for (TextField tF : listTextField) {
			l.add(tF.getText());
		}
		if (tipoElenco == 'c') {
			for (int i = 1; i <= candidati.size(); i++) {
				if (!l.contains((i+""))) return false;
			}
		}else {
			for (int i = 1; i <= partiti.size(); i++) {
				if (!l.contains((i+""))) return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}

	public void setSchedaBianca(ActionEvent event) {
		
	}

	public void votaPerCandidati(ActionEvent event) {
		tipoElenco = 'c';
		setVisible();
		resetVBox();
		for (int i = 0; i < candidati.size(); i++) {
			listTextField.get(i).setVisible(true);
		}
		try {
			label1.setText(candidati.get(0).getNome() + " " + candidati.get(0).getCognome());
			label2.setText(candidati.get(1).getNome() + " " + candidati.get(1).getCognome());
			label3.setText(candidati.get(2).getNome() + " " + candidati.get(2).getCognome());
			label4.setText(candidati.get(3).getNome() + " " + candidati.get(3).getCognome());
			label5.setText(candidati.get(4).getNome() + " " + candidati.get(4).getCognome());
		}catch(IndexOutOfBoundsException e) {
			//e.printStackTrace();
		}
	}

	public void votaPerPartiti(ActionEvent event) {
		tipoElenco = 'p';
		setVisible();
		resetVBox();
		for (int i = 0; i < partiti.size(); i++) {
			listTextField.get(i).setVisible(true);
		}
		try {
			label1.setText(partiti.get(0).getNome());
			label2.setText(partiti.get(1).getNome());
			label3.setText(partiti.get(2).getNome());
			label4.setText(partiti.get(3).getNome());
			label5.setText(partiti.get(4).getNome());
		}catch(IndexOutOfBoundsException e) {
			//e.printStackTrace();
		}
		
	}

	private void setVisible() {
		vBoxElenco.setVisible(true);
		bottoneConferma.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listTextField = new ArrayList<>(); 
		listTextField.add(textField1);
		listTextField.add(textField2);
		listTextField.add(textField3);
		listTextField.add(textField4);
		listTextField.add(textField5);
		loadData();
	}
	
	private void resetVBox() {
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
		for (TextField tF : listTextField) {
			tF.setText("");
			tF.setVisible(false);
		}
	}
	
	private void loadData() {
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		CandidatoDaoImpl cDao = (CandidatoDaoImpl)DaoFactory.getInstance().getDao("Candidato");
		PartitoDaoImpl pDao = (PartitoDaoImpl)DaoFactory.getInstance().getDao("Partito");
		candidati = cDao.getCandidatiSessione(s);
		partiti = pDao.getPartiti(s);
	}
}
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Stream;
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
import model.ElettoreHolder;
import model.LogVoto;
import model.LogVotoDao;
import model.Partito;
import model.PartitoDao;
import model.PartitoDaoImpl;
import model.SessioneDiVoto;
import model.SessioneDiVotoHolder;
import model.Voto;
import model.VotoDao;

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
			alert.setHeaderText("Numera correttamente i candidati/partiti scelti");
			alert.setTitle("Errore");
			alert.show();
		}
		Map<String, Integer> map = new TreeMap<>();
		fillMap(map);
		Stream<Map.Entry<String,Integer>> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue());
		Voto v = new Voto(SessioneDiVotoHolder.getInstance().getSessione());
		int []arr;
		int i = 0;
		if(tipoElenco == 'c') {
			arr = new int[candidati.size()];
			for (Map.Entry<String,Integer> s : (Iterable<Map.Entry<String, Integer>>) () -> sortedMap.iterator())
				for(Candidato c : candidati) if(c.toString().equals(s.getKey())) arr[i++] = c.getId();
			v.setPreferenze_candidato(arr);
		}
		else {
			arr = new int[partiti.size()];
			PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
			for (Map.Entry<String,Integer> s : (Iterable<Map.Entry<String, Integer>>) () -> sortedMap.iterator())	
				arr[i++] = pd.getId(new Partito(s.getKey()));
			v.setPreferenze_partito(arr);
		}
		LogVotoDao lv = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
		VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
		lv.inserisciLog(new LogVoto(SessioneDiVotoHolder.getInstance().getSessione().getId(), ElettoreHolder.getInstance().getElettore().getCodF()));
		if(!vd.inserisciVotoNonReferendum(v)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Errore inserimento voto");
			alert.setTitle("Errore");
			alert.show();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Voto inserito correttamente");
			alert.setTitle("Voto inserito");
			alert.show();
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
		}
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

	public void setSchedaBianca(ActionEvent event) throws IOException{
		Voto v = new Voto(SessioneDiVotoHolder.getInstance().getSessione());
		VotoDao vd = (VotoDao) DaoFactory.getInstance().getDao("Voto");
		LogVotoDao ld = (LogVotoDao) DaoFactory.getInstance().getDao("LogVoto");
		ld.inserisciLog(new LogVoto(SessioneDiVotoHolder.getInstance().getSessione().getId(), ElettoreHolder.getInstance().getElettore().getCodF()));
		if(vd.inserisciVotoNonReferendum(v)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Preferenza inserita correttamente");
			alert.setTitle("Votazione completata");
			alert.show();
			rimuoviScenaPrecedente();
			goToScenaPrecedente(event);
			return;
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Errore inserimento voto");
		alert.setTitle("Errore");
		alert.show();
	}

	public void votaPerCandidati(ActionEvent event) {
		tipoElenco = 'c';
		setVisible();
		resetVBox();
		for (int i = 0; i < candidati.size(); i++) {
			listTextField.get(i).setVisible(true);
		}
		try {
			label1.setText(candidati.get(0).toString());
			label2.setText(candidati.get(1).toString());
			label3.setText(candidati.get(2).toString());
			label4.setText(candidati.get(3).toString());
			label5.setText(candidati.get(4).toString());
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
	
	private void fillMap(Map<String, Integer> map) {
		if(!label1.getText().equals("")) map.put(label1.getText(), Integer.parseInt(textField1.getText()));
		if(!label2.getText().equals("")) map.put(label2.getText(), Integer.parseInt(textField2.getText()));
		if(!label3.getText().equals("")) map.put(label3.getText(), Integer.parseInt(textField3.getText()));
		if(!label4.getText().equals("")) map.put(label4.getText(), Integer.parseInt(textField4.getText()));
		if(!label5.getText().equals("")) map.put(label5.getText(), Integer.parseInt(textField5.getText()));
	}
	
	private void loadData() {
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		CandidatoDaoImpl cDao = (CandidatoDaoImpl)DaoFactory.getInstance().getDao("Candidato");
		PartitoDaoImpl pDao = (PartitoDaoImpl)DaoFactory.getInstance().getDao("Partito");
		candidati = cDao.getCandidatiSessione(s);
		partiti = pDao.getPartiti(s);
	}
}
package controllers;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.NuovoUtente;
import model.NuovoUtenteHolder;


public class RegistrazioneUtente1Controller  extends DefaultSceneController implements Initializable{
		
	private ObservableList<Integer> ol = FXCollections.observableArrayList();
	private ObservableList<String> ol2 = FXCollections.observableArrayList();
	
	@FXML private TextField nome;
	@FXML private TextField cognome;
	@FXML private TextField codF;
	@FXML private TextField paese;
	@FXML private TextField nazione;
 	@FXML private ComboBox<Integer> comboBoxAnno;
    @FXML private ComboBox<Integer> comboBoxGiorno;
    @FXML private ComboBox<Integer> comboBoxMese;
    @FXML private ChoiceBox<String> choiceBoxPrivilegio;
    @FXML private ChoiceBox<String> choiceBoxSesso;

	public void confermaDatiRegistrazione(ActionEvent event) throws IOException{
		String nome = this.nome.getText();
		String cognome = this.cognome.getText();
		String codF = this.codF.getText();	
		String paese = this.paese.getText();
		String nazione = this.nazione.getText();
		String privilegio = choiceBoxPrivilegio.getValue();
		String sesso = choiceBoxSesso.getValue();
		if(nome.equals("") || cognome.equals("") || codF.equals("") || paese.equals("") || nazione.equals("")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Alcuni campi non sono stati riempiti");
			alert.setTitle("Campi mancanti");
			alert.show();
		} else {
			int anno = comboBoxAnno.getValue();
			int mese = comboBoxMese.getValue();
			int giorno = comboBoxGiorno.getValue();
			NuovoUtente n = new NuovoUtente(nome, cognome, codF, LocalDate.of(anno, mese, giorno), paese, nazione, sesso.charAt(0), null, privilegio.charAt(0));
			if(!n.checkCodFisc()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Errore codice fiscale inserito");
				alert.setTitle("Errore");
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Conferma i dati inseriti per procedere");
				alert.setHeaderText("Conferma dati");
				alert.setTitle("Conferma dati");
				alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert.setContentText("Nome, cognome: " + nome + ", " + cognome + "\nCodice fiscale: " + codF + "\nLuogo di nascita: " + paese + " (" + nazione + ") \nData di nascita: " +
									giorno + "-" + mese + "-" + anno + "\nSesso: " +sesso+"\nPrivilegio: " +privilegio);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					NuovoUtenteHolder.getInstance().setUtente(n);
					setScenaPrecedente("registrazioneUtenteView1.fxml", "Registrazione utente");
					changeScene(event, "registrazioneUtenteView2.fxml", "Registrazione utente");
				}
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	private void loadData() {
		for (int i = 1; i <= 31; i++) ol.add(i);
		comboBoxGiorno.getItems().addAll(ol);
		ol.clear();
		for (int i = 1900; i <= 2021; i++) ol.add(i);
		comboBoxAnno.getItems().addAll(ol);
		ol.clear();
		for(int i = 1; i < 13; i++) ol.addAll(i);
		comboBoxMese.getItems().addAll(ol);
		ol2.clear();
		ol2.addAll("Maschio", "Femmina");
		choiceBoxSesso.getItems().addAll(ol2);
		ol2.clear();
		ol2.addAll("Elettore", "Scrutatore", "Gestore di sistema");
		choiceBoxPrivilegio.getItems().addAll(ol2);
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}
}

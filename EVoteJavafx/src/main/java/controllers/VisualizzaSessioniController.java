package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.DaoFactory;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;
import model.SessioneDiVotoHolder;

public class VisualizzaSessioniController extends DefaultSceneController implements Initializable {
	private ObservableList<SessioneDiVoto> sessioni = FXCollections.observableArrayList();
	@FXML
	private TableColumn<SessioneDiVoto, String> colonnaDataScadenza;
	@FXML
	private TableColumn<SessioneDiVoto, String> colonnaNomeSessione;
	@FXML
	private TableColumn<SessioneDiVoto, String> colonnaScrutinio;
	@FXML
	private TableView<SessioneDiVoto> tabella;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabella.setEditable(false);
		colonnaNomeSessione.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto, String>("nome"));
		colonnaDataScadenza.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto, String>("scadenzaAsString"));
		colonnaScrutinio.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto, String>("scrutinio"));
		loadData();
	}

	private void loadData() {
		sessioni = FXCollections.observableArrayList();
		SessioneDiVotoDao sDAO = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		List<SessioneDiVoto> list = sDAO.getSessioniAll();
		for (SessioneDiVoto s : list)
			sessioni.add(s);
		tabella.setItems(sessioni);
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

	@FXML
	public void avviaScrutinio(ActionEvent event) throws IOException {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		SessioneDiVoto s = tabella.getSelectionModel().getSelectedItem();
		if (s.getScrutinio().equals("Avviato")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Scrutinio gia' avviato");
			alert.setTitle("Errore");
			alert.show();
		} else {
			if (s.getScadenza().isAfter(LocalDateTime.now())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Non è ancora possibile avviare lo scrutinio, la sessione non è terminata");
				alert.setTitle("Errore");
				alert.show();
			} else {
				sd.avviaScrutinio(s);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(sd.getRisultati(s));
				alert.setTitle("Vincitore sessione");
				alert.show();
				changeScene(event, "visualizzaSessioniView.fxml", "Visualizza sessioni");
			}
		}
	}

	@FXML
	public void visualizzaInformazioni(ActionEvent event) {
		SessioneDiVoto s = tabella.getSelectionModel().getSelectedItem();
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		String modVoto, modVincitore;
		try {
			if (s.getModalitaVoto().equals("REF"))
				modVoto = "Referendum";
			else if (s.getModalitaVoto().equals("CATP"))
				modVoto = "Categorica con preferenza";
			else if (s.getModalitaVoto().equals("CAT"))
				modVoto = "Categorica";
			else
				modVoto = "Ordinale";
			if (s.getModVincitore() == null)
				modVincitore = "-";
			else if (s.getModVincitore().equals("MAGA"))
				modVincitore = "Maggioranza assoluta";
			else if (s.getModVincitore().equals("REF"))
				modVincitore = "Referendum";
			else if (s.getModVincitore().equals("REFQ"))
				modVincitore = "Referendum con quorum";
			else
				modVincitore = "Maggioranza";

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setTitle("Informazioni sessione");
			alert.setHeaderText("Informazioni relative a:" + s.getNome());
			alert.setContentText("Nome sessione: " + s.getNome() + "\nID: " + s.getId() + "\nModalita' voto: " + modVoto
					+ "\nModalita' vincitore : " + modVincitore + "\nScadenza sessione: " + s.getScadenzaAsString()
					+ "\nScrutinio: " + s.getScrutinio() + "\nNumero voti registrati: " + sd.getNumeroVoti(s)
					+ (sd.getScrutinio(s) ? ("\n" + sd.getRisultati(s)) : ""));
			alert.show();
		} catch (NullPointerException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Selezionare una sessione");
			alert.show();
			return;
		}
	}

	public void visualizzaStatistiche(ActionEvent event) throws IOException {
		try {
			SessioneDiVotoHolder.getInstance().setSessione(tabella.getSelectionModel().getSelectedItem());
			setScenaPrecedente("visualizzaSessioniView.fxml", "Visualizza sessioni");
			changeScene(event, "visualizzaStatisticheSessioneView.fxml", "Statistiche sessione");
		} catch (NullPointerException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Selezionare una sessione");
			alert.show();
			return;
		}

	}

}
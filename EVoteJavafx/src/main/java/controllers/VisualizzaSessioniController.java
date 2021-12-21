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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Candidato;
import model.DaoFactory;
import model.PartitoDao;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class VisualizzaSessioniController extends DefaultSceneController implements Initializable{
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
		colonnaNomeSessione.setCellValueFactory(
    			new PropertyValueFactory<SessioneDiVoto, String>("nome"));
		colonnaDataScadenza.setCellValueFactory(
    			new PropertyValueFactory<SessioneDiVoto, String>("scadenzaAsString"));
		/*colonnaScrutinio.setCellValueFactory(
    			new PropertyValueFactory<SessioneDiVoto, String>(""));*/
		loadData();
	}

	private void loadData() {
		sessioni = FXCollections.observableArrayList();
		SessioneDiVotoDao sDAO= (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		List<SessioneDiVoto> list = sDAO.getSessioni();
		for (SessioneDiVoto s : list) {
			sessioni.add(s);
		}
		tabella.setItems(sessioni);
	}
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    void avviaScrutinio(ActionEvent event) {
		System.out.println("scrutinio avviato");
		aggiorna database
    }

    @FXML
    void visualizzaInformazioni(ActionEvent event) {
    	SessioneDiVoto s = tabella.getSelectionModel().getSelectedItem();
    	System.out.println(s.getNome());    
    	
    }
	
    
}
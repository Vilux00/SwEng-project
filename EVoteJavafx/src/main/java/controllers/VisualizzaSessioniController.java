package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SessioneDiVoto;

public class VisualizzaSessioniController extends DefaultSceneController implements Initializable{

		@FXML
	    private TableColumn<SessioneDiVoto, String> colonnaDataScadenza;

	    @FXML
	    private TableColumn<SessioneDiVoto, String> colonnaModSessione;

	    @FXML
	    private TableColumn<SessioneDiVoto, String> colonnaNomeSessione;

	    @FXML
	    private TableColumn<SessioneDiVoto, String> colonnaScrutinio;

	    @FXML
	    private TableView<SessioneDiVoto> tabella;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<SessioneDiVoto> list = FXCollections.observableArrayList(
				new SessioneDiVoto("ciao", "hello"));
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    void avviaScrutinio(ActionEvent event) {
		System.out.println("scrutinio avviato");
    }

    @FXML
    void visualizzaInformazioni(ActionEvent event) {
    	SessioneDiVoto sessioneSelezionata =  tabella.getSelectionModel().getSelectedItem();
    	int index = tabella.getSelectionModel().selectedIndexProperty().get();
    }
	
    
}
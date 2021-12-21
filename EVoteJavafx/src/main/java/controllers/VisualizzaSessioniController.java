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
        colonnaScrutinio.setCellValueFactory(
                new PropertyValueFactory<SessioneDiVoto, String>("scrutinio"));
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
    	changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
    }
    
    @FXML
    void avviaScrutinio(ActionEvent event) throws IOException {
        SessioneDiVotoDao sDAO= (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
        SessioneDiVoto s = tabella.getSelectionModel().getSelectedItem();
        if(s.getScrutinio().equals("Avviato")) {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Scrutinio gia' avviato");
			alert.setTitle("Attento!");
			alert.show();
        }else {
        	
        	/*
        	 * Bisogna aggiungere i controlli per controllare se e' possibile attivare lo scrutinio
        	 * ovvero bisogna controllare se la sessione e' scaduta o meno
        	 */        	
        	sDAO.avviaScrutinio(s);
            changeScene(event, "visualizzaSessioniView.fxml", "Visualizza sessioni", data);
        }
    }

    @FXML
    void visualizzaInformazioni(ActionEvent event) {
        SessioneDiVoto s = tabella.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    	alert.setTitle("Informazioni sessione");
		alert.setHeaderText("Informazioni relative a:" + s.getNome());
		alert.setContentText("Nome sessione: " + s.getNome() 
							+ "\nID: " + s.getId()
							+ "\nModalita' voto: " + s.getModalitaVoto()
							+ "\nModalita' vincitore : " + s.getModVincitore()
							+ "\nScadenza sessione: " + s.getScadenzaAsString()
							+ "\nScrutinio: " + s.getScrutinio());
		alert.show();        
    }
    
    
}
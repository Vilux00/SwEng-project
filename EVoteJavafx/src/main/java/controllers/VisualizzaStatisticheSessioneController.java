package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;


public class VisualizzaStatisticheSessioneController extends DefaultSceneController implements Initializable {

	@FXML
	private PieChart torta;

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}

	private void loadData() {
		SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
		if(s.getModalitaVoto().equals("REF")) {
			loadREFData(s);
		}
		/*
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		torta.setData(pieChartData);
		*/
	}
	
	private void loadREFData(SessioneDiVoto s) {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Voti favorevoli", sd.getNumeroVotiFavorevoli(s)), 
				new PieChart.Data("Voti contari", sd.getNumeroVotiContrari(s)),
				new PieChart.Data("Schede bianche", sd.getNumeroSchedeBianche(s)));
		torta.setData(pieChartData);
	}

}

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;


public class VisualizzaStatisticheSessioneController extends DefaultSceneController implements Initializable {

	@FXML
	private PieChart torta;
	@FXML 
	private Button bottoneSwitch;
	
	private SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
	private SessioneDiVoto s = SessioneDiVotoHolder.getInstance().getSessione();
	
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}

	private void loadData() {
		if(s.getModalitaVoto().equals("REF")) {
			loadREFData();
		}else if (s.getModalitaVoto().equals("CAT")) {
			bottoneSwitch.setVisible(true);
			bottoneSwitch.setDisable(false);
			loadCATData();
		}else if (s.getModalitaVoto().equals("CATP")) {
			loadCATPData();
		}else {
			bottoneSwitch.setVisible(true);
			bottoneSwitch.setDisable(false);
			loadORDData();
		}
		/*
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		torta.setData(pieChartData);
		*/
	}
	
	public void switchPartitiCandidati(ActionEvent event) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		if(s.getModalitaVoto().equals("CAT")) {
			if (bottoneSwitch.getText().equals("Mostra statistiche partiti")) {		
				pieChartData.clear();
				for (String key : sd.getStatsPartiti(s).keySet()) {
			        pieChartData.add(new PieChart.Data(key, sd.getStatsPartiti(s).get(key)));
			    }
				bottoneSwitch.setText("Mostra statistiche candidati");
			}else {
				pieChartData.clear();
				for (String key : sd.getStatsCandidati(s).keySet()) {
			        pieChartData.add(new PieChart.Data(key, sd.getStatsCandidati(s).get(key)));
			    }
				bottoneSwitch.setText("Mostra statistiche partiti");
			}
		}else{
			if (bottoneSwitch.getText().equals("Mostra statistiche partiti")) {		
				pieChartData.clear();
				for (String key : sd.getStatsPartitiOrd(s).keySet()) {
			        pieChartData.add(new PieChart.Data(key, sd.getStatsPartitiOrd(s).get(key)));
			    }
				bottoneSwitch.setText("Mostra statistiche candidati");
			}else {
				pieChartData.clear();
				for (String key : sd.getStatsCandidatiOrd(s).keySet()) {
			        pieChartData.add(new PieChart.Data(key, sd.getStatsCandidatiOrd(s).get(key)));
			    }
				bottoneSwitch.setText("Mostra statistiche partiti");
			}
		}
		torta.setData(pieChartData);
	}
	
	
	private void loadCATData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (String key : sd.getStatsCandidati(s).keySet()) {
	        pieChartData.add(new PieChart.Data(key, sd.getStatsCandidati(s).get(key)));
	    }
		torta.setData(pieChartData);
	}
	
	private void loadCATPData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (String key : sd.getStatsCandidati(s).keySet()) {
	        pieChartData.add(new PieChart.Data(key, sd.getStatsCandidati(s).get(key)));
	    }
		torta.setData(pieChartData);
	}
	
	private void loadORDData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (String key : sd.getStatsCandidatiOrd(s).keySet()) {
			pieChartData.add(new PieChart.Data(key, sd.getStatsCandidatiOrd(s).get(key)));
	    }
		torta.setData(pieChartData);
	}
	
	private void loadREFData() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Voti favorevoli", sd.getNumeroVotiFavorevoli(s)), 
				new PieChart.Data("Voti contari", sd.getNumeroVotiContrari(s)),
				new PieChart.Data("Schede bianche", sd.getNumeroSchedeBianche(s)));
		torta.setData(pieChartData);
	}

}

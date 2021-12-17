package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import data.DbManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Elettore;

public class ProfiloElettoreHandler extends DefaultSceneHandler implements Initializable{
	
	private Label gener;
	private Label id;
	private Label codF;

	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password");
	}
	
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Elettore e = (Elettore)data;
		List<String> props = DbManager.getInstance().getInfoElettoreByCodF(e.getCodF());
		gener.setText(gener.getText() + props.get(0) + " " + props.get(1));
		id.setText("Nato a: " + props.get(2));
		codF.setText(codF.getText() + props.get(3));
		
	}
	
}

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Elettore;

public class ProfiloElettoreController extends DefaultSceneController implements Initializable{
	
	protected boolean infoMostrate = false;
	
	@FXML
	private Label gener;
	@FXML
	private Label id;
	@FXML
	private Label codF;
	
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password", data);
	}
	
	public void vota(ActionEvent event) throws IOException {
		
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*List<String> props = DbManager.getInstance().getInfoElettoreByCodF(e.getCodF());
		gener.setText(gener.getText() + props.get(0) + " " + props.get(1));
		id.setText("Nato a: " + props.get(2));
		codF.setText(codF.getText() + props.get(3));*/
	}
	
	public void mostraInfo(ActionEvent event) throws IOException{
		Elettore e = (Elettore)data;
		if (!infoMostrate) {
			gener.setText(gener.getText().replace("***********", "Nome e cognome"));
			id.setText(id.getText().replace("***********", "data di nascita"));
			codF.setText(codF.getText().replace("***********", e.getCodF()));
			infoMostrate = true;
		}else {
			gener.setText("Nome, Cognome: ************");
			id.setText("ID: ************");
			codF.setText("Codice Fiscale: ************");
			infoMostrate = false;
		}
	}
}

package controllers;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreDao;

public class ProfiloElettoreController extends DefaultSceneController{
	private boolean infoMostrate = false;
	@FXML private Label codF;
	@FXML private Label nc;
	@FXML private Label nascita;
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password", data);
	}
	
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
	
	public void mostraInfo(ActionEvent event) throws IOException{
		Elettore e = (Elettore)data;
		ElettoreDao el = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		List<String> info = el.getInfoByCodF(e);
		if (!infoMostrate) {
			nc.setText(nc.getText().replace("*", "") + ": " + info.get(0) + ", " + info.get(1));
			nascita.setText(nascita.getText().replace("*", "") + info.get(2) + ", " + info.get(4) + ", " + info.get(3));
			codF.setText(codF.getText().replace("*", "") + e.getCodF());
		}else {
			nc.setText("Nome, Cognome: ***************");
			nascita.setText("Data di nascita, luogo: ***************");
			codF.setText("Codice fiscale: ***************");
		}
		infoMostrate = !infoMostrate;
	}
}

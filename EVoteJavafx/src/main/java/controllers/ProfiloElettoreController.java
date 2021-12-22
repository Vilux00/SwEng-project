package controllers;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.DaoFactory;
import model.Elettore;
import model.ElettoreDao;
import model.ElettoreHolder;

public class ProfiloElettoreController extends DefaultSceneController{
	private boolean infoMostrate = false;
	@FXML private Label codF;
	@FXML private Label nc;
	@FXML private Label nascita;
	@FXML private ImageView immagineOcchioChiuso;
	@FXML private ImageView immagineOcchioAperto;
	
	public void cambiaPassword(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "modificaPasswordView.fxml", "Modifica password");
	}
	
	public void vota(ActionEvent event) throws IOException {
		setScenaPrecedente("profiloElettoreView.fxml", "Profilo elettore");
		changeScene(event, "selezioneSessioneView.fxml", "Selezione sessione");
	}
	
	public void mostraInfo(ActionEvent event) throws IOException{
		Elettore e = ElettoreHolder.getInstance().getElettore();
		ElettoreDao el = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		List<String> info = el.getInfoByCodF(e);
		if (!infoMostrate) {
			immagineOcchioAperto.setVisible(true);
			immagineOcchioChiuso.setVisible(false);
			nc.setText(nc.getText().replace("*", "") + ": " + info.get(0) + ", " + info.get(1));
			nascita.setText(nascita.getText().replace("*", "") + info.get(2) + ", " + info.get(4) + ", " + info.get(3));
			codF.setText(codF.getText().replace("*", "") + e.getCodF());
		}else {
			immagineOcchioAperto.setVisible(false);
			immagineOcchioChiuso.setVisible(true);
			nc.setText("Nome, Cognome: ***************");
			nascita.setText("Data di nascita, luogo: ***************");
			codF.setText("Codice fiscale: ***************");
		}
		infoMostrate = !infoMostrate;
	}

	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		backToHomeScene(event);
	}
}

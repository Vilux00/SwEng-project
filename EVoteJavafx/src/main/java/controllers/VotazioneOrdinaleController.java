package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

public class VotazioneOrdinaleController extends DefaultSceneController{
	@Override
	public void goToScenaPrecedente(ActionEvent event) throws IOException {
		changeScene(event, scenaPrecedente.pop(), scenaPrecedenteTitolo.pop(), data);
	}
}

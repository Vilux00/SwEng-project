import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Candidato;
import model.DaoFactory;
import model.Partito;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class SessioneDiVotoDaoImplTest {
	@Test
	public void inserisciSessioneReferendumTrueTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		SessioneDiVoto s = new SessioneDiVoto("test", "REF");
		s.setQuesito("test");
		s.setModVincitore("REFQ");
		s.setScadenza(LocalDateTime.now());
		assertTrue(sd.inserisciSessioneReferendum(s));
	}
	
	@Test
	public void inserisciSessioneNonReferendumTrueTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		SessioneDiVoto s = new SessioneDiVoto("test", "CAT");
		s.setModVincitore("MAG");
		Candidato c = new Candidato("Nome", "Cognome");
		c.setPartito(new Partito("Nome partito"));
		s.addCandidato(c);
		s.setScadenza(LocalDateTime.now());
		assertTrue(sd.inserisciSessioneNonReferendum(s));
	}
	
	@Test
	public void getSessioniNotNullTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		assertNotNull(sd.getSessioni());
	}
	
	@Test
	public void getScrutinioCorrectTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		for(SessioneDiVoto s : sd.getSessioni()) {
			if(s.getScadenza().isAfter(LocalDateTime.now()))
				assertFalse(sd.getScrutinio(s));
		}
	}
}

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.DaoFactory;
import model.SessioneDiVoto;
import model.SessioneDiVotoDao;

public class SessioneDiVotoDaoImplTest {	
	@Test
	public void getSessioniNotNullTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		assertNotNull(sd.getSessioni());
	}
	
	@Test
	public void getScrutinioCorrectTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		for(SessioneDiVoto s : sd.getSessioni()) {
			if(s.getScadenza().isAfter(LocalDateTime.now())) assertFalse(sd.getScrutinio(s));
		}
	}
	
	@Test
	public void getScrutinioFalseTest() {
		SessioneDiVotoDao sd = (SessioneDiVotoDao) DaoFactory.getInstance().getDao("SessioneDiVoto");
		SessioneDiVoto s = new SessioneDiVoto("Test", "REF");
		s.setId(-1);
		assertFalse(sd.getScrutinio(s));
	}
}

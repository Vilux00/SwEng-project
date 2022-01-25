import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import model.CandidatoDao;
import model.DaoFactory;

public class CandidatoDaoImplTest {
	@Test
	public void getCandidatoByIdCorrectTest() {
		CandidatoDao cd = (CandidatoDao) DaoFactory.getInstance().getDao("Candidato");
		assertEquals(cd.getCandidatoById(1).getNome(), "Alessandro");
	}
	
	@Test
	public void getCandidatoByIdNullTest() {
		CandidatoDao cd = (CandidatoDao) DaoFactory.getInstance().getDao("Candidato");
		assertNull(cd.getCandidatoById(-1));
	}
}

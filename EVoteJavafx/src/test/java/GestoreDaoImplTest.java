import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.DaoFactory;
import model.Elettore;
import model.GestoreDao;
public class GestoreDaoImplTest {
	
	/** Test login method **/
	@Test
	public void loginFalseTest() {
		GestoreDao ed = (GestoreDao) DaoFactory.getInstance().getDao("Gestore");
		assertFalse(ed.login(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	@Test
	public void loginTrueTest() {
		GestoreDao ed = (GestoreDao) DaoFactory.getInstance().getDao("Gestore");
		assertTrue(ed.login(new Elettore("RSVMRA65A01F839V", "password_0")));
	}
}

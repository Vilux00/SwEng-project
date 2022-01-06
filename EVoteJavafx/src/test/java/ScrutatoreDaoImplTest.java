import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.DaoFactory;
import model.Elettore;
import model.ScrutatoreDao;
public class ScrutatoreDaoImplTest {
	
	/** Test login method **/
	@Test
	public void loginFalseTest() {
		ScrutatoreDao ed = (ScrutatoreDao) DaoFactory.getInstance().getDao("Scrutatore");
		assertFalse(ed.login(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	@Test
	public void loginTrueTest() {
		ScrutatoreDao ed = (ScrutatoreDao) DaoFactory.getInstance().getDao("Scrutatore");
		assertTrue(ed.login(new Elettore("HTKKSH70A01F205E", "password_4")));
	}
}
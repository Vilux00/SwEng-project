import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.DaoFactory;
import model.Elettore;
import model.ElettoreDao;
public class ElettoreDaoImplTest {
	
	/** Test login method **/
	@Test
	public void loginFalseTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertFalse(ed.login(new Elettore("test", "test")));
	}
	
	@Test
	public void loginTrueTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertTrue(ed.login(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	/** Test update password method **/
	@Test
	public void updatePasswordTrueTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertTrue(ed.login(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	@Test
	public void updatePasswordFalseTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertFalse(ed.login(new Elettore("test", "test")));
	}
	
	/** Test getInfoByCodF method **/
	@Test
	public void getInfoByCodFNullTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertNull(ed.getInfoByCodF(new Elettore("test", "test")));
	}
	
	@Test
	public void getInfoByCodFNotNullTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertNotNull(ed.getInfoByCodF(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	/** Test getNomeByCodF method **/
	@Test
	public void getNomeByCodFNullTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertNull(ed.getNomeByCodF(new Elettore("test", "password_5")));
	}
	
	@Test
	public void getNomeByCodFNotNullTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertEquals(ed.getNomeByCodF(new Elettore("LSBGNI00A01C352Z", "password_5")), "Gino");
	}
	
	/** Test getCognomeByCodF method **/
	@Test
	public void getCognomeByCodFNull() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertNull(ed.getCognomeByCodF(new Elettore("test", "password_5")));
	}
	
	@Test
	public void getCognomeByCodFNotNull() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertEquals(ed.getCognomeByCodF(new Elettore("LSBGNI00A01C352Z", "password_5")), "Losballo");
	}
	
	/** Test maggiorenne method **/
	@Test
	public void isMaggiorenneTrueTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertTrue(ed.maggiorenne(new Elettore("LSBGNI00A01C352Z", "password_5")));
	}
	
	@Test
	public void isMaggiorenneFalseTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertFalse(ed.maggiorenne(new Elettore("VRDGRG05A01H703O", "password_6")));
	}
	
	/** Test isIn method **/
	@Test
	public void isInTrueTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertTrue(ed.isIn("LSBGNI00A01C352Z"));
	}
	
	@Test
	public void isInFalseTest() {
		ElettoreDao ed = (ElettoreDao) DaoFactory.getInstance().getDao("Elettore");
		assertFalse(ed.isIn("test"));
	}
}

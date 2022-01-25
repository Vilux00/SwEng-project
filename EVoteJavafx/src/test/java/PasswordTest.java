import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Password;

public class PasswordTest {
	@Test
	public void checkPasswordTrueTest() {
		assertTrue(Password.checkPassword("123_abCDa"));
	}
	
	@Test
	public void checkPasswordFalseTest() {
		assertFalse(Password.checkPassword("123"));
	}
	
	@Test
	public void checkPasswordFalse2Test() {
		assertFalse(Password.checkPassword("123adsada13"));
	}
	
	@Test
	public void checkPasswordFalse3Test() {
		assertFalse(Password.checkPassword("asdadsadsads_"));
	}
	
	@Test
	public void generateRandomPasswordTest() {
		for (int i = 0; i < 50; i++) {
			assertTrue(Password.checkPassword(Password.generateRandomPassword()));
		}
	}
}

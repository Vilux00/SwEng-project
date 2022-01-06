import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import data.DbManager;

public class DbManagerTest {
	@Test
	public void openTest() {
		assertDoesNotThrow(() -> DbManager.getInstance().open());
	}
	
	@Test
	public void closeTest() {
		DbManager db = DbManager.getInstance();
		assertDoesNotThrow(() -> db.close(db.open()));
	}
}

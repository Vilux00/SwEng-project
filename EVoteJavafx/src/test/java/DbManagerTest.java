import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import data.DbManager;

public class DbManagerTest {
	@Test
	public void testConnection() {
		assertNotNull(DbManager.getInstance().open());
	}
}

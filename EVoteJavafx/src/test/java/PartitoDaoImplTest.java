import org.junit.jupiter.api.Test;

import model.DaoFactory;
import model.Partito;
import model.PartitoDao;

public class PartitoDaoImplTest {
	@Test
	public void isInTest() {
		PartitoDao pd = (PartitoDao) DaoFactory.getInstance().getDao("Partito");
		for(Partito p : pd.getPartiti()) pd.isIn(p);
	}
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import model.SessioneDiVoto;
import model.Voto;

public class VotoTest {
	@Test
	public void creationTest() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		assertNotNull(v);
	}
	
	@Test
	public void setGetSessioneTest() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		SessioneDiVoto s2 = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		v.setS(s2);
		assertEquals(v.getS(), s2);
	}
	
	@Test
	public void setGetPreferenzeCandidato() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		int []arr = new int[] {1, 2, 3};
		v.setPreferenze_candidato(arr);
		assertEquals(v.getPreferenze_candidato(), arr);
	}
	
	@Test
	public void setGetPreferenzePartito() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		int []arr = new int[] {1, 2, 3};
		v.setPreferenze_partito(arr);
		assertEquals(v.getPreferenze_partito(), arr);
	}
	
	@Test
	public void setGetRQuesito() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		v.setR_quesito(false);
		assertEquals(v.getR_quesito(), false);
	}
	
	@Test
	public void setGetId() {
		SessioneDiVoto s = new SessioneDiVoto("prova", "REF");
		Voto v = new Voto(s);
		v.setId(1);
		assertEquals(v.getId(), 1);
	}
}

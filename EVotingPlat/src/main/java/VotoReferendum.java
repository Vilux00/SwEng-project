/**
 * Le istanze di questa classe rappresentano un voto di tipo referendum
 */
public class VotoReferendum extends Voto{
	
	/** La risposta alla domanda del referendum */
	private final boolean voto;
	
	public VotoReferendum(String idSessione, boolean voto) {
		super(idSessione);
		this.voto = voto;
	}
	
	@Override
	public void inserisciVoto() {
		// TODO Connessione al database e inserimento del voto
	}
	
	@Override
	public String toString() {
		return "Voto espresso: " + (voto ? "Sono d'accordo" : "Non sono d'accordo");
	}
	
}

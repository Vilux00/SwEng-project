/**
 * Classe astratta che rappresenta un voto espresso
 */
public abstract class Voto{
	
	/** Id della sessione di voto */
	protected final String idSessione;	

	public Voto(String idSessione) {
		this.idSessione = idSessione;
	}

    /**
     * Inserisce il voto nel database
     */
	public abstract void inserisciVoto();

}
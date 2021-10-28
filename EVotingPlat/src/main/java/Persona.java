import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Classe astratta rappresentante una persona, gestore o utente del sistema.
 */
public abstract class Persona{
	
	/** Codice fiscale della persona */
	private final String codFiscale;

	/**
	 * @param codFiscale il codice fiscale della persona
	 */
	public Persona(String codFiscale){
		this.codFiscale = Objects.requireNonNull(codFiscale);
		
	}

	/**
	 * Metodo statico che logga una persona sulla base dei parametri ricevuti dall'interfaccia utente.
	 * Il metodo è invocato al click di un bottone sull'interfaccia
	 * 
	 * @param codFiscale il codice fiscale dell'individuo
	 * @param password la password
	 * @param type il tipo di login (1- utente, 0- gestore)
	 * @return una istanza di utente o gestore in base al tipo di login effettuato
	 */
	public static Persona login(String codFiscale, String password, boolean type /* Passati dall'interfaccia */){
		if(codFiscale == null || password == null) return null;
		
		// TODO DB connection
		
		String hashedPassword = null;
		
		// Calcolo SHA-512
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            hashedPassword = hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
		
		// TODO Controllo se <codFiscale, hashedPassowrd> è nel database
		
		return (type ? new Utente(codFiscale) : new Gestore(codFiscale));	
	}

	/**
	 * Inserisce il voto nel database
	 */
	public void vota(String idSessione, String preferenza /* Passati dall'interfaccia */){
		if(/* this non ha ancora votato */){
			// TODO Inserimento voto espresso nel database
		
			// TODO Inserire nel database che la persona ha votato
	
			// Inserimento log di voto nel database
			new Log(this.codFiscale).insertLog();
		}
	}	

	/**
	 * Preleva dal database e restituisce le votazioni a cui ha partecipato la persona
	 * 
	 * @return la lista di votazioni a cui la persona ha partecipato
	 */
	public List<String> mostraVotazioni(){
		// TODO connessione al db e prelievo sessioni di voto a cui la persona ha partecipato
	}
	
	/**
	 * Mostra le informazioni personali
	 * 
	 * @return le informazioni personali di this
	 */
	public String mostraProfilo(){
		// TODO Accesso a DB e prelievo info utente, poi mostrate su di una specifica interfaccia
	}
}

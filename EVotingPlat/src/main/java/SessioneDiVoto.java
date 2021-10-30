import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Le istanze di questa classe sono sessioni di voto che il gestore ha configurato o deve completare di configurare.
 * Ogni istanza è definita da un id, da una modalità di voto, da un nome e da una lista di partiti votabili.
 */
public class SessioneDiVoto{

    /** Id della sessione di voto */
	public final String id;
	/** Modalità della sessione di voto */
	public final String modalità;
	/** Nome sella sessione */
	public final String nome;
    /** La lista di partiti candidati nella sessione */
    private List<Partito> partiti;

    /**
     * @param id l'id della sessione di voto
     * @param modalità la modalità di voto
     * @param nome il nome della sessione
     */
	public SessioneDiVoto(String id, String modalità, String nome /* Tutti presi dall'interfaccia utente che ha compilato il gestore */){
		this.id = id; 
		this.modalità = modalità;
		this.nome = nome;
		this.partiti = new ArrayList<>();
	}
    
    /**
     * Aggiunge un nuovo partito alla sessione di voto
     * 
     * @param partito il partito da aggiungere
     */
    public void aggiungiPartito(Partito partito){
        partiti.add(partito);
    }

    /**
     * Inserisce nel database le informazioni sulla sessione
     */
	public void inserisciSessione(){
        // TODO Connessione al database e inserimento informazioni sessione
	}

    /** 
     * Si collega al db, esegue una query per conteggiare voti della sessione e restituisce il numero di voti per partito/candidato
     * 
     * @return mappa di voti per ogni candidato nel formato <candidato, numero voti>
     */
	public Map<String, Integer> conteggioVoti(){
        // TODO Connessione al db, conteggio voti sessione
	}
	
	@Override
	public String toString() {
		return id + " " + " " + nome;
	}
}
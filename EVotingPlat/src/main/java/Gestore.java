import java.util.Map;
import java.util.Objects;

/**
 * Le istanze di questa classe rappresentano gestori di sistema.
 */
public class Gestore extends Persona{
	
	public Gestore(String codFiscale){
		super(codFiscale);
	}

	/**
	 * Inserisce una nuova sessione di voto nel database
	 * 
	 * @param id id della sessione
	 * @param mod la modalità di voto
	 */
	public void creaSessione(String id, String mod, String nome/* Parametri presi dall'interfaccia utente */) {
		new SessioneDiVoto(Objects.requireNonNull(id), Objects.requireNonNull(mod), Objects.requireNonNull(nome)).inserisciSessione();
		//
	}
	
	/**
	 * Aggiorna una sessione già creata aggiungendo un partito candidato
	 * 
	 * @param id id della sessione di voto
	 * @param p il partito da aggiungere
	 */
	public void aggiornaSessione(SessioneDiVoto sessione, Partito p) {
		Objects.requireNonNull(sessione).aggiungiPartito(Objects.requireNonNull(p));
	}

	/**
	 * Invocata quando il gestore clicca su un bottone 'conta voti' nella pagina della sessione
	 * Conta i voti della sessione identificata da idSessione
	 * 
	 * @param id id della sessione di voto della quale contare i voti
	 * @return mappa dei voti ottenuti da ciascun candidato <candidato, numero voti>
	 */
	public Map<String, Integer> contaVoti(String id){
		// TODO
		// Conteggio voti e return lista voti per ogni candidato, inseriti poi nell'interfaccia.
	}

	/**
	 * Aggiunge un nuovo account utente al database
	 */
	public void creaAccountUtente(/* Parametri interfaccia codFiscale, password, nome...*/){
		// TODO
		// Dall'interfaccia compilata dal gestore sono passati i parametri, la funzione è invocata al click di un bottone della interfaccia
	}
	
	/**
	 * Aggiunge un nuovo account gestore al database
	 */
	public void creaAccountGestore(/* Parametri interfaccia codFiscale, password, nome...*/){
		// TODO
		// Dall'interfaccia compilata dal gestore sono passati i parametri, la funzione è invocata al click di un bottone della interfaccia
	}

	/**
	 * Aggiunge al database un nuovo voto cartaceo per la sessione idSessione
	 * 
	 * @param idSessione l'id della sessione di voto
	 */
	public void inserisciVotoCartaceo(String idSessione /* + parametri interfaccia utente + votazione */){
		// TODO
		// Dall'interfaccia utente è prelevato il voto che il gestore riporta da una scheda di voto, viene messo nel db.
	}
	
	@Override
	public String mostraProfilo() {
		return "Gestore di sistema" + super.mostraProfilo();
	}
}
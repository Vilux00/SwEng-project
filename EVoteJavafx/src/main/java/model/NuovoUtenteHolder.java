package model;

public class NuovoUtenteHolder {
	private NuovoUtente utente;
	private static NuovoUtenteHolder nuHolder;
	
	private NuovoUtenteHolder() {}
	
	public static NuovoUtenteHolder getInstance() {
		if (nuHolder == null) nuHolder = new NuovoUtenteHolder();
		return nuHolder;
	}
	
	public NuovoUtente getUtente() {
		return utente;
	}
	
	public void setUtente(NuovoUtente utente) {
		nuHolder.utente = utente;
	}
	
}

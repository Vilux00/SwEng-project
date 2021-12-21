package model;

public class LogVoto {
	private SessioneDiVoto sessione;
	private String codFiscale;
	
	public SessioneDiVoto getSessione() {
		return sessione;
	}
	public void setSessione(SessioneDiVoto sessione) {
		this.sessione = sessione;
	}
	public String getCodFiscale() {
		return codFiscale;
	}
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
	
}

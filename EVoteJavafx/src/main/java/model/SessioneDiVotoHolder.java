package model;

public class SessioneDiVotoHolder {
	private SessioneDiVoto sessione;
	private static SessioneDiVotoHolder sessHolder;
	
	private SessioneDiVotoHolder() {}
	
	public static SessioneDiVotoHolder getInstance() {
		if (sessHolder == null) sessHolder = new SessioneDiVotoHolder();
		return sessHolder;
	}
	
	public SessioneDiVoto getSessione() {
		return sessione;
	}
	
	public void setSessione(SessioneDiVoto sessione) {
		sessHolder.sessione = sessione;
	}
	
}

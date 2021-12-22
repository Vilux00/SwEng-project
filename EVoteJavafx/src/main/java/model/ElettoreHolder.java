package model;

public class ElettoreHolder {
	private Elettore elettore;
	private static ElettoreHolder elHolder;
	
	private ElettoreHolder() {}
	
	public static ElettoreHolder getInstance() {
		if (elHolder == null) elHolder = new ElettoreHolder();
		return elHolder;
	}
	
	public Elettore getElettore() {
		return elettore;
	}
	
	public void setElettore(Elettore elettore) {
		elHolder.elettore = elettore;
	}
	
}

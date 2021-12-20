package model;

public interface SessioneDiVotoDao extends DaoInterface{
	public boolean inserisciSessioneReferendum(SessioneDiVoto s);
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s);
	public boolean getIdSessione(SessioneDiVoto s);
}

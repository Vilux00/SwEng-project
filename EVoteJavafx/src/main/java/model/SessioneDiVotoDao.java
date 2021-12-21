package model;

import java.util.List;

public interface SessioneDiVotoDao extends DaoInterface{
	public boolean inserisciSessioneReferendum(SessioneDiVoto s);
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s);
	public List<SessioneDiVoto> getSessioni();
}

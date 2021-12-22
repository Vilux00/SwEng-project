package model;

import java.util.List;

public interface SessioneDiVotoDao extends DaoInterface{
	public boolean inserisciSessioneReferendum(SessioneDiVoto s);
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s);
	public List<SessioneDiVoto> getSessioni();
	public List<SessioneDiVoto> getSessioni(String codF);
	public void avviaScrutinio(SessioneDiVoto s);
	public int getNumeroVoti(SessioneDiVoto s);
	public String getQuesito(SessioneDiVoto s);
}

package model;

import java.util.List;
import java.util.Map;

public interface SessioneDiVotoDao extends DaoInterface{
	public boolean inserisciSessioneReferendum(SessioneDiVoto s);
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s);
	public List<SessioneDiVoto> getSessioni();
	public List<SessioneDiVoto> getSessioni(String codF);
	public void avviaScrutinio(SessioneDiVoto s);
	public int getNumeroVoti(SessioneDiVoto s);
	public String getQuesito(SessioneDiVoto s);
	public String getRisultati(SessioneDiVoto s);
	public boolean getScrutinio(SessioneDiVoto s);
	public int getNumeroVotiFavorevoli(SessioneDiVoto s);
	public int getNumeroVotiContrari(SessioneDiVoto s);
	public int getNumeroSchedeBianche(SessioneDiVoto s);
	Map<String, Integer> getStatsCandidati(SessioneDiVoto s);
	Map<String, Integer> getStatsPartiti(SessioneDiVoto s);
}

package model;

import java.util.List;

public interface CandidatoDao extends DaoInterface{
	public boolean inserisciCandidato(Candidato c, Partito p);
	public Candidato getCandidatoById(int id);
	public List<Candidato> getCandidati();
	public boolean inserisciCandidatoSessione(Candidato c, int id);
	public List<Candidato> getCandidatiSessione(SessioneDiVoto s);
}

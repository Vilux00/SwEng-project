package model;

public interface CandidatoDao extends DaoInterface{
	public boolean inserisciCandidato(Candidato c, Partito p);
	public Candidato getCandidatoById(String id);
}

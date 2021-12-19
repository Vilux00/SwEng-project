package model;

import java.util.List;

public interface PartitoDao extends DaoInterface{
	public boolean insertPartito(Partito p);
	public boolean isIn(Partito p);
	public List<Candidato> getCandidati(Partito p);
	public Integer getId(Partito p);
}

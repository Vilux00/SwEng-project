package model;

public interface VotoDao extends DaoInterface{
	public boolean inserisciVotoReferendum(Voto v);
	public boolean inserisciVotoNonReferendum(Voto v);
}

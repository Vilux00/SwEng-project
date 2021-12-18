package model;

public interface ElettoreDao extends DaoInterface{
	public boolean login(Elettore e);
	public boolean updatePassword(Elettore e);
}

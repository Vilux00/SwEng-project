package model;

import java.util.List;

public interface ElettoreDao extends DaoInterface{
	public boolean login(Elettore e);
	public boolean updatePassword(Elettore e);
	public String getNomeByCodF(Elettore e);
	public String getCognomeByCodF(Elettore e);
	public List<String> getInfoByCodF(Elettore e);
	public boolean isIn(String codF);
}

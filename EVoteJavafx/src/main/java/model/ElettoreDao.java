package model;

import java.util.List;

public interface ElettoreDao {
	public void login(Elettore e);
	public List<String> getInfoByCodF(Elettore e);
}

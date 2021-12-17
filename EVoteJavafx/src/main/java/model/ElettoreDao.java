package model;

import java.util.List;

public interface ElettoreDao {
	public boolean login(Elettore e);
	public List<String> getInfoByCodF(Elettore e);
}

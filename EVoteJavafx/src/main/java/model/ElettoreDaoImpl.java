package model;

import java.util.List;

import data.DbManager;

public class ElettoreDaoImpl implements ElettoreDao{

	@Override
	public boolean login(Elettore e) {
		return DbManager.getInstance().login(e.getCodF(), e.getPassword());
	}

	@Override
	public List<String> getInfoByCodF(Elettore e) {
		return null;
	}

}

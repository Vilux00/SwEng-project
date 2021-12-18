package model;

import java.sql.Connection;
import java.sql.SQLException;

import data.DbManager;

public class SessioneDiVotoDaoImpl implements SessioneDiVotoDao{

	@Override
	public void inserisciSessione(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			c.prepareStatement("INSERT INTO evoting.sessione_voto VALUES(?, ?, ?, ?, ?, ?, ?)");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbM.close(c);
		}
	}
}

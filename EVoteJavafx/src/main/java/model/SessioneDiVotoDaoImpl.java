package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import data.DbManager;

public class SessioneDiVotoDaoImpl implements SessioneDiVotoDao{

	@Override
	public boolean inserisciSessioneReferendum(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.sessione_voto(nome, modalita_voto, modalita_vincitore, p_or_c, quesito, termine) VALUES(?, ?, ?, ?, ?, ?)");
			ps.setString(1, s.getNome());
			ps.setString(2, s.getModalitaVoto());
			ps.setString(3, s.getModVincitore());
			ps.setNull(4, Types.NULL);
			ps.setString(5, s.getQuesito());
			ps.setObject(6, s.getScadenza());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(c);
		}
	}
	
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s) {
		return false;
	}
	
	public boolean getIdSessione(SessioneDiVoto s) {
		return false;
	}
	
	public void inserisciPartitoSessione() {
		
	}
}

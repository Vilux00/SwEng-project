package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data.DbManager;

public class CandidatoDaoImpl implements CandidatoDao{

	@Override
	public boolean inserisciCandidato(Candidato c, Partito p) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		PartitoDaoImpl pa = (PartitoDaoImpl) DaoFactory.getInstance().getDao("Partito");
		try {
			if(!pa.isIn(p)) pa.insertPartito(p);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO evoting.candidato(nome, cognome, id_partito) VALUES (?, ?, ?)");
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, pa.getId(p));
			return ps.executeQuery().next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public Candidato getCandidatoById(String id) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT nome, cognome FROM evoting.candidato c.id = ?");
			ps.setString(1, id);
			ResultSet r = ps.executeQuery();
			if(r.next()) return new Candidato(r.getString(1), r.getString(2));
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			dbM.close(conn);
		}
	}

}

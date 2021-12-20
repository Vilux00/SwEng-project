package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DbManager;

public class CandidatoDaoImpl implements CandidatoDao{

	@Override
	public boolean inserisciCandidato(Candidato c, Partito p) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		PartitoDaoImpl pa = (PartitoDaoImpl) DaoFactory.getInstance().getDao("Partito");
		try {
			if(!pa.isIn(p)) pa.insertPartito(p);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO evoting.candidato_partito(nome, cognome, id_partito) VALUES (?, ?, ?)");
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, pa.getId(p));
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public Candidato getCandidatoById(int id) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT nome, cognome FROM evoting.candidato_partito WHERE id = ?");
			ps.setInt(1, id);
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

	@Override
	public List<Candidato> getCandidati() {
		List<Candidato> candidati = new ArrayList<>();
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement s = conn.prepareStatement("SELECT * FROM evoting.candidato");
			ResultSet r = s.executeQuery();
			while(r.next()) candidati.add(new Candidato(r.getString(2), r.getString(3)));
			return candidati;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbM.close(conn);
		}
	}

}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import data.DbManager;

public class SessioneDiVotoDaoImpl implements SessioneDiVotoDao{

	@Override
	public boolean inserisciSessioneReferendum(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.sessione_voto(nome, modalita_voto, modalita_vincitore, p_or_c, quesito, termine, scrutinio) VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, s.getNome());
			ps.setString(2, s.getModalitaVoto());
			ps.setString(3, s.getModVincitore());
			ps.setNull(4, Types.NULL);
			ps.setString(5, s.getQuesito());
			ps.setObject(6, s.getScadenza());
			ps.setBoolean(7, false);
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(c);
		}
	}
	
	@Override
	public boolean inserisciSessioneNonReferendum(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.sessione_voto(nome, modalita_voto, modalita_vincitore, p_or_c, quesito, termine, scrutinio) VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, s.getNome());
			ps.setString(2, s.getModalitaVoto());
			ps.setString(3, s.getModVincitore());
			ps.setString(4, "C");
			ps.setNull(5, Types.NULL);
			ps.setObject(6, s.getScadenza());
			ps.setBoolean(7, false);
			if(ps.executeUpdate() == 0) return false;
			ps = c.prepareStatement("SELECT MAX(s.id) FROM evoting.sessione_voto AS s");
			ResultSet r = ps.executeQuery();
			if(!r.next()) return false;
			int id = r.getInt(1);
			CandidatoDao ca = (CandidatoDaoImpl) DaoFactory.getInstance().getDao("Candidato");
			for(Candidato cand : s)
				if(!ca.inserisciCandidatoSessione(cand, id)) return false;
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(c);
		}
	}

	@Override
	public List<SessioneDiVoto> getSessioni() {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		List<SessioneDiVoto> l = new ArrayList<>();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT nome, modalita_voto, modalita_vincitore, quesito, termine, p_or_c, id, scrutinio FROM evoting.sessione_voto");
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				SessioneDiVoto s = new SessioneDiVoto(r.getString(1), r.getString(2));
				s.setId(r.getInt(7));
				s.setModVincitore(r.getString(3));
				s.setQuesito(r.getString(4));
				s.setScadenza(r.getObject(5,LocalDateTime.class));
				s.setScrutinio(r.getBoolean(8));
				l.add(s);
			}
			return l;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			dbM.close(c);
		}
	}


}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
            PreparedStatement ps = c.prepareStatement("SELECT nome, modalita_voto, modalita_vincitore, quesito, termine, id, scrutinio FROM evoting.sessione_voto WHERE termine > ?");
            ps.setObject(1, LocalDateTime.now());
            ResultSet r = ps.executeQuery();
            while(r.next()) {
                SessioneDiVoto s = new SessioneDiVoto(r.getString(1), r.getString(2));
                s.setModVincitore(r.getString(3));
                s.setQuesito(r.getString(4));
                s.setScadenza(r.getObject(5, LocalDateTime.class));
                s.setId(r.getInt(6));
                s.setScrutinio(r.getBoolean(7));
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

    @Override
    public void avviaScrutinio(SessioneDiVoto s) {
        DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE evoting.sessione_voto SET scrutinio = true WHERE id = ?");
            ps.setInt(1, s.getId());
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            dbM.close(c);
        }
    }

	@Override
	public int getNumeroVoti(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM evoting.log_voto WHERE id_sessione = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getInt(1);
            return -1;
        }catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            dbM.close(c);
        }
	}
    
	 @Override
	 public List<SessioneDiVoto> getSessioni(String codF) {
	        DbManager dbM = DbManager.getInstance();
	        Connection c = dbM.open();
	        List<SessioneDiVoto> l = new ArrayList<>();
	        try {
	            PreparedStatement ps = c.prepareStatement("SELECT s.nome, s.modalita_voto, s.modalita_vincitore, s.quesito, s.termine, s.id, s.scrutinio FROM evoting.sessione_voto AS s WHERE s.termine > ? AND NOT EXISTS(SELECT * FROM evoting.log_voto WHERE id_sessione = s.id AND log_voto.codice_fiscale = ?)");
	            ps.setObject(1, LocalDateTime.now());
	            ps.setString(2, codF);
	            ResultSet r = ps.executeQuery();
	            while(r.next()) {
	                SessioneDiVoto s = new SessioneDiVoto(r.getString(1), r.getString(2));
	                s.setModVincitore(r.getString(3));
	                s.setQuesito(r.getString(4));
	                s.setScadenza(r.getObject(5, LocalDateTime.class));
	                s.setId(r.getInt(6));
	                s.setScrutinio(r.getBoolean(7));
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

	@Override
	public String getQuesito(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT quesito FROM evoting.sessione_voto WHERE id = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getString(1);
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            dbM.close(c);
        }
	}
	
	public String getRisultati(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
        	PreparedStatement ps = c.prepareStatement("SELECT evoting.scrutinio(?)");
        	ps.setInt(1, s.getId());
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) return rs.getString(1);
        	return null;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            dbM.close(c);
        }
	}
	
	@Override
	public boolean getScrutinio(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT scrutinio FROM evoting.sessione_voto WHERE id = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getBoolean(1);
            return false;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            dbM.close(c);
        }
	}

	@Override
	public int getNumeroVotiFavorevoli(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(id_voto) FROM voto WHERE r_quesito = 'S' AND id_sessione = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getInt(1);
            return 0;
        }catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            dbM.close(c);
        }
	}

	@Override
	public int getNumeroVotiContrari(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(id_voto) FROM voto WHERE r_quesito = 'N' AND id_sessione = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getInt(1);
            return 0;
        }catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            dbM.close(c);
        }
	}

	@Override
	public int getNumeroSchedeBianche(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(id_voto) FROM voto WHERE r_quesito = 'X' AND id_sessione = ?");
            ps.setInt(1, s.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()) return r.getInt(1);
            return 0;
        }catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            dbM.close(c);
        }
	}
	
	@Override
	public Map<String, Integer> getStatsCandidati(SessioneDiVoto s){
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        Map<String, Integer> map = new TreeMap<>();
		try {
			PreparedStatement ps = c.prepareStatement("    WITH selezione_voti(preferenza) AS (" + 
					"        SELECT v.preferenza_c FROM voto AS v JOIN sessione_voto AS s ON s.id = v.id_sessione WHERE v.id_sessione = ?" + 
					"    ), voti(pref) AS (" + 
					"        SELECT unnest FROM selezione_voti AS s, UNNEST(s.preferenza)" + 
					"    ), voti_c(candidato, voti_ottenuti) AS (" + 
					"        SELECT v.pref, COUNT(*) FROM voti AS v WHERE v.pref IS NOT NULL GROUP BY v.pref" + 
					"    ) SELECT c.nome, c.cognome, p.nome, v.voti_ottenuti FROM voti_c AS v JOIN candidato_partito AS c ON c.id = v.candidato" + 
					"        JOIN partito AS p ON p.id = c.id_partito ORDER BY v.voti_ottenuti DESC LIMIT 7");
			ps.setInt(1, s.getId());
			ResultSet r = ps.executeQuery();
			while(r.next()) map.put("(" + r.getString(3) + ")" + r.getString(1) + r.getString(2), r.getInt(4));
			return map;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			dbM.close(c);
		}
	}

	@Override
	public Map<String, Integer> getStatsPartiti(SessioneDiVoto s) {
		DbManager dbM = DbManager.getInstance();
        Connection c = dbM.open();
        Map<String, Integer> map = new TreeMap<>();
		try {
			PreparedStatement ps = c.prepareStatement("    WITH selezione_voti(preferenza) AS (" + 
					"        SELECT v.preferenza_p FROM voto AS v JOIN sessione_voto AS s ON s.id = v.id_sessione WHERE v.id_sessione = ?" + 
					"    ), voti(pref) AS (" + 
					"        SELECT unnest FROM selezione_voti AS s, UNNEST(s.preferenza)" + 
					"    ), voti_c(candidato, voti_ottenuti) AS (" + 
					"        SELECT v.pref, COUNT(*) FROM voti AS v WHERE v.pref IS NOT NULL GROUP BY v.pref" + 
					"    ) SELECT c.nome, c.cognome, p.nome, v.voti_ottenuti FROM voti_c AS v JOIN candidato_partito AS c ON c.id = v.candidato" + 
					"        JOIN partito AS p ON p.id = c.id_partito ORDER BY v.voti_ottenuti DESC LIMIT 7");
			ps.setInt(1, s.getId());
			ResultSet r = ps.executeQuery();
			while(r.next()) map.put("(" + r.getString(3) + ") " + r.getString(1) + " " +  r.getString(2), r.getInt(4));
			return map;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			dbM.close(c);
		}
	}
	
    


}
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import data.DbManager;

public class ElettoreDaoImpl implements ElettoreDao{

	@Override
	public boolean login(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ? AND password = ? AND privilegio = 'E'");
	        stm.setString(1, e.getCodF());
	        stm.setString(2, DigestUtils.sha256Hex(e.getPassword()));
	        return stm.executeQuery().next();
		} catch(SQLException ex){
			ex.printStackTrace();
			return false;
		} finally {
			dbM.close(conn);
		}
	}

	@Override
	public boolean updatePassword(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("UPDATE evoting.elettore SET password = ? WHERE codice_fiscale = ?");
	        stm.setString(1, DigestUtils.sha256Hex(e.getPassword()));
	        stm.setString(2, e.getCodF());
			return stm.executeUpdate() > 0; 
		} catch(SQLException ex){
			ex.printStackTrace();
			return false;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public List<String> getInfoByCodF(Elettore e){
		DbManager dbM = DbManager.getInstance();
		List<String> l = new ArrayList<>();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) {
				l.add(r.getString(2));
				l.add(r.getString(3));
				l.add(r.getDate(4).toString());
				l.add(r.getString(5));
				l.add(r.getString(6));
				return l;
			}
			return null;
		} catch(SQLException ex){
			ex.printStackTrace();
			return null;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public String getNomeByCodF(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT nome FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) return r.getString(1);
			return null;
		} catch(SQLException ex){
			ex.printStackTrace();
			return null;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public String getCognomeByCodF(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT cognome FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) return r.getString(1);
			return null;
		} catch(SQLException ex){
			ex.printStackTrace();
			return null;
		} finally {
			dbM.close(conn);
		}
	}

}

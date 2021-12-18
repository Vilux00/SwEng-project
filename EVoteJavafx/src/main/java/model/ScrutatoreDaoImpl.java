package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import data.DbManager;

public class ScrutatoreDaoImpl extends ElettoreDaoImpl implements ScrutatoreDao{
	@Override
	public boolean login(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ? AND password = ? AND privilegio = 'S'");
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
}

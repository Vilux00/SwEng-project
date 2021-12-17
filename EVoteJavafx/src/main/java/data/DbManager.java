package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

public class DbManager {
    private static DbManager dbMgr;
    private String connString = "jdbc:postgresql://localhost/eVoting-platform?user=user_1&password=password&ssl=false"; //Not working with ssl=true
    
    public static DbManager getInstance(){
        if(dbMgr == null) {
            dbMgr = new DbManager();
        }
        return dbMgr;
    }

    private Connection open(){
        try{
            return DriverManager.getConnection(connString);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    private void close(Connection c) {
    	try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public boolean login(String codF, String password) {
		Connection conn = open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ? AND password = ?");
	        stm.setString(1, codF);
	        stm.setString(2, DigestUtils.sha256Hex(password));
	        ResultSet r = stm.executeQuery();
	        return r.next();
		} catch(SQLException ex){
			ex.printStackTrace();
			return false;
		} finally {
			close(conn);
		}
	}

    public List<String> getInfoElettoreByCodF(String codF){
        Connection conn = open();
        try{
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ?");
            stm.setString(1, codF);
            ResultSet r = stm.executeQuery();
            if(r.next()){
                List<String> l = new ArrayList<>();
                l.add(r.getString("nome"));
                l.add(r.getString("cognome"));
                l.add(r.getString("data_nascita"));
                return l;
            }
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        } finally{
            close(conn);
        }
    }


}

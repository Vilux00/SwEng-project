package data;

import java.sql.*;
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

    public void testDB(){
        String query = "SELECT * FROM eVoting.elettore";
        try{
            ResultSet r = open().createStatement().executeQuery(query);
            if(r.next()) System.out.println(r.getString(1) + " " + r.getString(2));
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

	public boolean login(String codF, String password) {
		Connection conn = open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE username = ? AND password = ?");
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


}

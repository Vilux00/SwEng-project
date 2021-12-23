package data;

import java.sql.*;

public class DbManager {
    private static DbManager dbMgr;
    private String connString = "jdbc:postgresql://localhost/eVoting-platform?user=user_1&password=password&currentSchema=evoting&ssl=false"; //Not working with ssl=true
    
    private DbManager(){}

    public static DbManager getInstance(){
        if(dbMgr == null) {
            dbMgr = new DbManager();
        }
        return dbMgr;
    }

    public Connection open(){
        try{
            return DriverManager.getConnection(connString);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void close(Connection c) {
    	try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    /*
    public List<String> getInfoElettoreByCodF(String codF){
    	System.out.println(1);
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
    */

}

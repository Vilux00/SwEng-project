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

}

package data;

import java.sql.*;

public class DbManager {
    private static DbManager dbMgr;
    private static String connString = "jdbc:postgresql://localhost/eVoting-platform?user=user_1&password=password&ssl=false"; //Not working with ssl true

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

    public void testDB(){
        String query = "SELECT * FROM eVoting.elettore";
        try{
            Statement c = open().createStatement(); 
            ResultSet r = c.executeQuery(query);
            if(r.next()){
                System.out.println(r.getString(1) + " " + r.getString(2));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


}

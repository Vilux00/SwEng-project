package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.ElettoreHolder;

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
        	try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append("user: " + ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " - " + e.getClass().toString() + " - " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
            return null;
        }
    }
    
    public void close(Connection c){
    	try {
			c.close();
		} catch (SQLException e) {
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append("user: " + ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " - " + e.getClass().toString() + " - " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
		}
    }

}

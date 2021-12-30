package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append(ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " " + e.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return false;
		} finally {
			dbM.close(conn);
		}
	}
}

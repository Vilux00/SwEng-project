package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import data.DbManager;

public class LogVotoDaoImpl implements LogVotoDao{

	@Override
	public boolean inserisciLog(LogVoto l) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.log_voto VALUES(?, ?, ?)");
			ps.setInt(1, l.getIdSessione());
			ps.setString(2, l.getCodFiscale());
			ps.setObject(3, LocalDateTime.now());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
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
		}finally {
			dbM.close(c);
		}
	}

}

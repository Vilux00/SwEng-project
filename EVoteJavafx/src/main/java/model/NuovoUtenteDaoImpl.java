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

public class NuovoUtenteDaoImpl implements NuovoUtenteDao{
	@Override
	public boolean register(NuovoUtente n) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.elettore VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, n.getCodF());
			ps.setString(2, n.getNome());
			ps.setString(3, n.getCognome());
			ps.setObject(4, n.getDataNascita());
			ps.setString(5, n.getNazioneNascita());
			ps.setString(6, n.getPaeseNascita());
			ps.setString(7, n.getSesso() + "");
			ps.setString(8, DigestUtils.sha256Hex(n.getPassword()));
			ps.setString(9, n.getPrivilegio() + "");
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
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

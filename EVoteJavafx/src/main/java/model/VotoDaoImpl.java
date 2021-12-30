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

public class VotoDaoImpl implements VotoDao{
	@Override
	public boolean inserisciVotoReferendum(Voto v) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.voto(r_quesito, id_sessione) VALUES(?, ?)");
			String rQuesito;
			System.out.println(v.getR_quesito());
			if(v.getR_quesito() == null) rQuesito = "X";
			else if(v.getR_quesito().equals(true)) rQuesito = "S";
			else rQuesito = "N";
			ps.setString(1, rQuesito);
			ps.setInt(2, v.getS().getId());
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
	
	@Override
	public boolean inserisciVotoNonReferendum(Voto v) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.voto(preferenza_c, preferenza_p, id_sessione) VALUES(?, ?, ?)");
			ps.setObject(1, v.getPreferenze_candidato());
			ps.setObject(2, v.getPreferenze_partito());
			ps.setInt(3, v.getS().getId());
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

package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import data.DbManager;

public class ElettoreDaoImpl implements ElettoreDao{

	@Override
	public boolean login(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ? AND password = ? AND privilegio = 'E'");
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
			    		+ " " + ex.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return false;
		} finally {
			dbM.close(conn);
		}
	}

	@Override
	public boolean updatePassword(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("UPDATE evoting.elettore SET password = ? WHERE codice_fiscale = ?");
	        stm.setString(1, DigestUtils.sha256Hex(e.getPassword()));
	        stm.setString(2, e.getCodF());
			return stm.executeUpdate() > 0; 
		} catch(SQLException ex){
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append(ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " " + ex.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return false;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public List<String> getInfoByCodF(Elettore e){
		DbManager dbM = DbManager.getInstance();
		List<String> l = new ArrayList<>();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) {
				l.add(r.getString(2));
				l.add(r.getString(3));
				l.add(r.getDate(4).toString());
				l.add(r.getString(5));
				l.add(r.getString(6));
				return l;
			}
			return null;
		} catch(SQLException ex){
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append(ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " " + ex.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return null;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public String getNomeByCodF(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT nome FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) return r.getString(1);
			return null;
		} catch(SQLException ex){
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append(ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " " + ex.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return null;
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public String getCognomeByCodF(Elettore e) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
	        PreparedStatement stm = conn.prepareStatement("SELECT cognome FROM evoting.elettore WHERE codice_fiscale = ?");
	        stm.setString(1, e.getCodF());
	        ResultSet r = stm.executeQuery();
			if(r.next()) return r.getString(1);
			return null;
		} catch(SQLException ex){
			try{
				FileWriter w;
			    w = new FileWriter("log.txt", true);
			    
			    BufferedWriter b;
			    b = new BufferedWriter(w);
	
			    b.append(ElettoreHolder.getInstance().getElettore().getCodF() 
			    		+ " " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() 
			    		+ " " + ex.getClass().toString() + " " + new Object(){}.getClass().getEnclosingMethod().getName() + "\n");
			    
				b.close();
			}catch(IOException i) {}
			return null;
		} finally {
			dbM.close(conn);
		}
	}

	@Override
	public boolean isIn(String codF) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM evoting.elettore WHERE codice_fiscale = ?");
			stm.setString(1, codF);
			return stm.executeQuery().next();
		} catch(SQLException e){
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

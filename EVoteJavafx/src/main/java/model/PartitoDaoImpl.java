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

import data.DbManager;

public class PartitoDaoImpl implements PartitoDao{
	@Override
	public boolean insertPartito(Partito p) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM evoting.partito WHERE nome = ?");
			ps.setString(1, p.getNome());
			if(ps.executeQuery().next()) return false;
			ps = conn.prepareStatement("INSERT INTO evoting.partito(nome) VALUES(?) ");
			ps.setString(1, p.getNome());
			if(ps.executeUpdate() == 0) return false;
			return true;
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
			dbM.close(conn);
		}
	}
	
	@Override
	public boolean isIn(Partito p) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM evoting.partito WHERE nome = ?");
			ps.setString(1, p.getNome());
		    return ps.executeQuery().next();
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
			dbM.close(conn);
		}
	}
	
	@Override
	public List<Candidato> getCandidati(Partito p){
		DbManager dbM = DbManager.getInstance();
		List<Candidato> candidati = new ArrayList<>();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT c.id, c.nome, c.cognome FROM evoting.candidato_partito AS c JOIN evoting.partito AS p ON c.id_partito = p.id WHERE p.nome = ?");
			ps.setString(1, p.getNome());
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Candidato c = new Candidato(r.getString(2), r.getString(3));
				c.setId(r.getInt(1));
				c.setPartito(p);
				candidati.add(c);
			}
			return candidati;
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
			return null;
		}finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public Integer getId(Partito p) {
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM evoting.partito WHERE nome = ?");
			ps.setString(1, p.getNome());
			ResultSet r = ps.executeQuery();
			if(r.next()) return r.getInt(1);
			return null;
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
			return null;
		}finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public List<Partito> getPartiti() {
		List<Partito> l = new ArrayList<>();
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM evoting.partito");
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Partito p = new Partito(r.getString(2));
				p.setCandidati(getCandidati(p));
				l.add(p);
			}
			return l;
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
			return null;
		}finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public List<Partito> getPartiti(SessioneDiVoto s){
		List<Partito> l = new ArrayList<>();
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT(p.*) FROM evoting.candidato_partito AS c JOIN evoting.candidato_per AS cp ON c.id = cp.id_candidato JOIN evoting.partito AS p ON p.id = c.id_partito WHERE cp.id_sessione = ?");
			ps.setInt(1, s.getId());
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Partito p = new Partito(r.getString(2));
				p.setId(r.getInt(1));
				p.setCandidati(getCandidati(p));
				l.add(p);
			}
			return l;
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
			return null;
		}finally {
			dbM.close(conn);
		}
	}

}

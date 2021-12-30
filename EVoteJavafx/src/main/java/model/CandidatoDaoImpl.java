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

public class CandidatoDaoImpl implements CandidatoDao{

	@Override
	public boolean inserisciCandidato(Candidato c, Partito p){
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		PartitoDaoImpl pa = (PartitoDaoImpl) DaoFactory.getInstance().getDao("Partito");
		try {
			if(!pa.isIn(p)) pa.insertPartito(p);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO evoting.candidato_partito(nome, cognome, id_partito) VALUES (?, ?, ?)");
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, pa.getId(p));
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
			dbM.close(conn);
		}
	}
	
	@Override
	public Candidato getCandidatoById(int id){
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT nome, cognome FROM evoting.candidato_partito WHERE id = ?");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			if(r.next()) return new Candidato(r.getString(1), r.getString(2));
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
	public List<Candidato> getCandidati(){
		List<Candidato> candidati = new ArrayList<>();
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement s = conn.prepareStatement("SELECT * FROM evoting.candidato");
			ResultSet r = s.executeQuery();
			while(r.next()) candidati.add(new Candidato(r.getString(2), r.getString(3)));
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
		} finally {
			dbM.close(conn);
		}
	}

	@Override
	public boolean inserisciCandidatoSessione(Candidato c, int id){
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		try {
			PreparedStatement s = conn.prepareStatement("INSERT INTO evoting.candidato_per VALUES(?, ?)");
			s.setInt(1, c.getId());
			s.setInt(2, id);
			return s.executeUpdate() > 0;
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
		} finally {
			dbM.close(conn);
		}
	}
	
	@Override
	public List<Candidato> getCandidatiSessione(SessioneDiVoto s){
		DbManager dbM = DbManager.getInstance();
		Connection conn = dbM.open();
		List<Candidato> l = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT c.id, c.nome, c.cognome, p.nome FROM evoting.candidato_partito AS c JOIN evoting.candidato_per AS cp ON c.id = cp.id_candidato JOIN evoting.partito AS p ON p.id = c.id_partito WHERE cp.id_sessione = ?");
			ps.setInt(1, s.getId());
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Candidato c = new Candidato(r.getString(2), r.getString(3));
				c.setId(r.getInt(1));
				c.setPartito(new Partito(r.getString(4)));
				l.add(c);
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
		} finally {
			dbM.close(conn);
		}
	}

}

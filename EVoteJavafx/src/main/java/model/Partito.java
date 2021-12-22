package model;

import java.util.Collections;
import java.util.List;

public class Partito {
	private int id;

	private String nome;
	private List<Candidato> l;
	
	public Partito(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addCandidato(Candidato c) {
		l.add(c);
	}
	
	public void setCandidati(List<Candidato> l) {
		this.l = l;
	}
	
	public List<Candidato> getCandidati() {
		return Collections.unmodifiableList(l);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object o) {
		Partito p = (Partito)o;
		return nome == p.nome;
	}
}

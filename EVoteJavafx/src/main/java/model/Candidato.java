package model;

public class Candidato {
	private int id;
	private String nome;
	private String cognome;
	private Partito partito;
	
	public Candidato(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setPartito(Partito partito) {
		this.partito = partito;
	}
	
	public Partito getPartito() {
		return partito;
	}
	
	@Override
	public String toString() {
		return "(" + partito.toString() + ") " + nome + " " + cognome;
	}
	
	@Override
	public boolean equals(Object o) {
		Candidato c = (Candidato) o;
		return nome.equals(c.nome) && cognome.equals(c.cognome) && partito.equals(c.partito);
	}

}

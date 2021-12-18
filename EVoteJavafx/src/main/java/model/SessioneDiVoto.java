package model;

import java.time.LocalDateTime;

public class SessioneDiVoto {
	private String nome;
	private String modalita;
	private LocalDateTime scadenza;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getModalita() {
		return modalita;
	}
	
	public void setModalita(String modalita) {
		this.modalita = modalita;
	}

	public int getGiorno() {
		return scadenza.getDayOfMonth();
	}
	
	public int getMese() {
		return scadenza.getMonthValue();
	}
	
	public int getAnno() {
		return scadenza.getYear();
	}
	
	public int getOre() {
		return scadenza.getHour();
	}
	
	public int getMinuti() {
		return scadenza.getMinute();
	}

	public void setScadenza(int giorno, int mese, int anno, int ore, int minuti) {
		this.scadenza = LocalDateTime.of(giorno, mese, anno, ore, minuti);
	}
}

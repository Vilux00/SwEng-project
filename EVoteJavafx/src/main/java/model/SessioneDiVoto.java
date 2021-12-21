package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessioneDiVoto implements Iterable<Candidato>{
	private int id;
	private String nome;
	private String modalitaVoto;
	private String modVincitore;
	private LocalDateTime scadenza;
	private char pOrC;
	private String quesito;
	private List<Candidato> candidati;
	
	public SessioneDiVoto(String nome, String modalitaVoto) {
		this.nome = nome;
		this.modalitaVoto = modalitaVoto;
		this.quesito = null;
		candidati = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getModalitaVoto() {
		return modalitaVoto;
	}
	
	public void setModalitaVoto(String modalitaVoto) {
		this.modalitaVoto = modalitaVoto;
	}
	
	public void setModVincitore(String modalitaVincitore) {
		this.modVincitore = modalitaVincitore;
	}
	
	public String getModVincitore() {
		return this.modVincitore;
	}
	
	public LocalDateTime getScadenza() {
		return this.scadenza;
	}

	public void setScadenza(int giorno, int mese, int anno, int ore, int minuti) {
		this.scadenza = LocalDateTime.of(anno, mese, giorno, ore, minuti);
	}
	
	public void setScadenza(LocalDateTime l) {
		this.scadenza = l;
	}

	public String getQuesito() {
		return quesito;
	}

	public void setQuesito(String quesito) {
		this.quesito = quesito;
	}

	public List<Candidato> getCandidati() {
		return candidati;
	}
	
	public boolean addCandidato(Candidato c) {
		return candidati.add(c);
	}
	
	public void addCandidati(List<Candidato> c) {
		for(Candidato cand : c) if(!candidati.contains(cand)) addCandidato(cand);
	}

	public char getPOrC() {
		return pOrC;
	}

	public void setPOrC(char pOrC) {
		this.pOrC = pOrC;
	}

	@Override
	public Iterator<Candidato> iterator() {
		return candidati.iterator();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		this.id = id;
	}
}

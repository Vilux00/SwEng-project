package model;

public class Voto {
	private int id;
	private SessioneDiVoto s;
	private Boolean r_quesito;
	private int []preferenze_partito;
	private int []preferenze_candidato;
	
	/*@
	   invariant(s.getModalitaVoto.equals("REF") ==> (r_quesito != null && preferenze_partito == null && preferenze_candidato == null)) &&
	  		(!s.getModalitaVoto.equals("REF") ==> (r_quesito == null && preferenze_partito != null && preferenze_candidato != null));	
	  @*/
	
	public Voto(SessioneDiVoto s) {
		this.s = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SessioneDiVoto getS() {
		return s;
	}

	public void setS(SessioneDiVoto s) {
		this.s = s;
	}

	public Boolean getR_quesito() {
		return r_quesito;
	}

	public void setR_quesito(Boolean r_quesito) {
		this.r_quesito = r_quesito;
	}

	public int[] getPreferenze_partito() {
		return preferenze_partito;
	}

	/*@
	   requires \forall int i; 0 <= i && i < preferenze_partito.length; !(\exists int j; 0 <= j && j < preferenze_partito.length && i != j; i = j)
	  @*/
	public void setPreferenze_partito(int[] preferenze_partito) {
		this.preferenze_partito = preferenze_partito;
	}

	public int[] getPreferenze_candidato() {
		return preferenze_candidato;
	}

	/*@
	   requires: \forall int i; 0 <= i && i < preferenze_candidato.length; !(\exists int j; 0 <= j && j < preferenze_candidato.length && i != j; i = j)
	  @*/
	public void setPreferenze_candidato(int[] preferenze_candidato) {
		this.preferenze_candidato = preferenze_candidato;
	}
}

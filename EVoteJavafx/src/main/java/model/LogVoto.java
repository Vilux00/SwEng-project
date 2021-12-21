package model;

public class LogVoto {
	private int idSessione;
	private String codFiscale;
	
	public LogVoto(int idSessione, String codFiscale) {
		this.idSessione = idSessione;
		this.codFiscale = codFiscale;
	}
	
	public int getIdSessione() {
		return idSessione;
	}
	
	public void setIdSessione(int idSessione) {
		this.idSessione = idSessione;
	}
	
	public String getCodFiscale() {
		return codFiscale;
	}
	
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
	
}

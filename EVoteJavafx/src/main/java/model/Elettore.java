package model;

public class Elettore {
	private String codF;
	private String password;
	
	public Elettore(String codF, String password) {
		this.setCodF(codF);
		this.setPassword(password);
	}

	public String getCodF() {
		return codF;
	}

	public void setCodF(String codF) {
		this.codF = codF;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

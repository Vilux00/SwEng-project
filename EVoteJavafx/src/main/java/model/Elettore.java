package model;

public class Elettore {
	protected String codF;
	protected String password;
	
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

	/*@
	    requires Password.checkPassword(password) == true; 
	  @*/
	public void setPassword(String password) {
		this.password = password;
	}
	
}

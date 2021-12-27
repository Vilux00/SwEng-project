package model;

import java.time.LocalDate;

public class NuovoUtente {
	private /*@ non_null @*/ String nome;
    private /*@ non_null @*/ String cognome;
    private /*@ non_null @*/ String codF;
    private /*@ non_null @*/ LocalDate dataNascita;
    private /*@ non_null @*/ String paeseNascita;
    private /*@ non_null @*/ String nazioneNascita;
    private char sesso;
    private /*@ non_null @*/ String password;
    private char privilegio;
    
    /*@
     * invariant: (sesso == 'M' || sesso == 'F') && (privilegio == 'E' || privilegio == 'S' || privilegio == 'G');
     @*/
    
    public NuovoUtente(String nome, String cognome, String codF, LocalDate dataNascita, String paeseNascita, String nazioneNascita, char sesso, String password, char privilegio) {
		this.nome = nome;
		this.cognome = cognome;
		this.codF = codF;
		this.dataNascita = dataNascita;
		this.paeseNascita = paeseNascita;
		this.nazioneNascita = nazioneNascita;
		this.sesso = sesso;
		this.password = password;
		this.privilegio = privilegio;
	}
    
	public char getPrivilegio() {
		return privilegio;
	}
	
	public void setPrivilegio(char privilegio) {
		this.privilegio = privilegio;
	}
	
	public char getSesso() {
		return sesso;
	}
	
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	public String getNazioneNascita() {
		return nazioneNascita;
	}
	
	public void setNazioneNascita(String nazioneNascita) {
		this.nazioneNascita = nazioneNascita;
	}
	
	public String getPaeseNascita() {
		return paeseNascita;
	}
	
	public void setPaeseNascita(String paeseNascita) {
		this.paeseNascita = paeseNascita;
	}
	
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getCodF() {
		return codF;
	}
	
	/*@
	 * requires: codF != null && checkCodF(codF) == true; 
	 @*/
	public void setCodF(String codF) {
		this.codF = codF;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean checkCodFisc(){
		char []codFisc = codF.toUpperCase().toCharArray();
        // Lunghezza non corretta
		
        if(codFisc.length != 16) return false;

        String cognomeLettere = cognome.replaceAll("[AEIOUaeiou,]", "").concat(cognome.replaceAll("[B-DF-HJ-NP-TV-Zb-df-hj-np-tv-z,]", "")).toUpperCase();
        String consonantiNome = nome.split(",")[0].replaceAll("[AEIOUaeiou,]", "");
        String vocaliNome = nome.split(",")[0].replaceAll("[B-DF-HJ-NP-TV-Zb-df-hj-np-tv-z,]", "");
        String nomeLettere = (consonantiNome.length() >= 4 ? consonantiNome.charAt(0) + consonantiNome.substring(2) : consonantiNome).concat(vocaliNome).toUpperCase();
        
        // Check primo secondo e terzo carattere
        if(codFisc[0] != (cognomeLettere.length() >= 1 ? cognomeLettere.charAt(0) : 'X')
        	|| codFisc[1] != (cognomeLettere.length() >= 2 ? cognomeLettere.charAt(1) : 'X') 
        		|| codFisc[2] != (cognomeLettere.length() >= 3 ? cognomeLettere.charAt(2) : 'X')) return false;
        
        // Check quarto quinto e sesto carattere
        if(codFisc[3] != (nomeLettere.length() >= 1 ? nomeLettere.charAt(0) : 'X')
            || codFisc[4] != (nomeLettere.length() >= 2 ? nomeLettere.charAt(1) : 'X') 
            	|| codFisc[5] != (nomeLettere.length() >= 3 ? nomeLettere.charAt(2) : 'X')) return false;
        
        // Check settimo e ottavo carattere
        if(dataNascita.getYear() % 100 != (codFisc[6] - '0') * 10 + (codFisc[7] - '0')) return false;
        
        // Check nono carattere
        if(dataNascita.getMonthValue() == 1 && codFisc[8] != 'A') return false;
        if(dataNascita.getMonthValue() == 2 && codFisc[8] != 'B') return false;
        if(dataNascita.getMonthValue() == 3 && codFisc[8] != 'C') return false;
        if(dataNascita.getMonthValue() == 4 && codFisc[8] != 'D') return false; 
        if(dataNascita.getMonthValue() == 5 && codFisc[8] != 'E') return false;
        if(dataNascita.getMonthValue() == 6 && codFisc[8] != 'H') return false;
        if(dataNascita.getMonthValue() == 7 && codFisc[8] != 'L') return false;
        if(dataNascita.getMonthValue() == 8 && codFisc[8] != 'M') return false;
        if(dataNascita.getMonthValue() == 9 && codFisc[8] != 'P') return false;
        if(dataNascita.getMonthValue() == 10 && codFisc[8] != 'R') return false;
        if(dataNascita.getMonthValue() == 11 && codFisc[8] != 'S') return false;
        if(dataNascita.getMonthValue() == 12 && codFisc[8] != 'T') return false;
        
        // Check decimo e undicesimo carattere
        int giorno = (codFisc[9] - '0') * 10 + (codFisc[10] - '0');
        if(giorno - (sesso == 'F' ? 40 : 0) != dataNascita.getDayOfMonth()) return false;
        /* Da qui il controllo è semplificato */
        
        // Check dodicesimo carattere
        if(Character.isDigit(codFisc[11]) || (!nazioneNascita.equals("Italia") && codFisc[11] != 'Z')) return false;
        
        // Check tredicesimo, quattordicesimo e quindicesimo carattere
        for(int i = 12; i < 15; i++) if(Character.isLetter(codFisc[i])) return false;
        
        // Check sedicesimo carattere
        if(Character.isDigit(codFisc[15])) return false;
        
        return true;
    }
}

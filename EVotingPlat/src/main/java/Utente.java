/**
 * Le istanze di questa classe sono utenti del sistema che possono votare
 */
public class Utente extends Persona{
	
	public Utente(String codFiscale){
		super(codFiscale);
	}	
	
	@Override
	public String mostraProfilo() {
		return "Utente " + super.mostraProfilo();
	}
}
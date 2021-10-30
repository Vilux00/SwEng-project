import java.sql.Timestamp;

/**
 * Le istanze di questa classe rappresentano log.
 * Ogni istanza sarà caratterizzata da: codice fiscale di chi ha votato e timestamp del voto.
 */
public class Log{
	
	/** Il codice fiscale della persona che ha votato */
    private final String codFiscale;
    /** Il timestamp del voto */
    private final Timestamp t;
    
    public Log(String codFiscale){
        this.codFiscale = codFiscale;
        t = new Timestamp(System.currentTimeMillis());;
    }

    /**
     * Inserisce il log nel database
     */
    public void insertLog(){
    	// TODO Connessione al database e inserimento del log
    }
}

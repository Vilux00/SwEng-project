import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Le istanze di questa classe rappresentano un partito, definito da un nome, un identificativo e da una lista di candidati.
 */
public class Partito{
	
    /** Nome del partito */
    private final String nome;  
    /** Identificativo univoco del partito */
    private final String id;
    /** Lista di candidati del partito */
    private List<String> candidati;

    public Partito(String nome, String id){
        this.nome = Objects.requireNonNull(nome);
        this.id = Objects.requireNonNull(id);
        candidati = new ArrayList<>();
    }

    /**
     * Aggiunge un nuovo candidato al partito
     * 
     * @param candidato il candidato da aggiungere al partito
     */
    public void aggiungiCandidato(String candidato){
        candidati.add(Objects.requireNonNull(candidato));
    }

    /**
     * Restituisce la lista di candidati del partito
     * 
     * @return la lista immutabile dei candidati del partito
     */
    public List<String> candidati(){
        return List.copyOf(candidati);
    }
    
}

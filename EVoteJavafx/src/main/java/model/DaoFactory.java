package model;

public class DaoFactory {
	
	private static DaoFactory df;
	
	public DaoFactory getInstance() {
		if(df == null) df = new DaoFactory();
		return df;
	}
	
	public DaoInterface getDao(String type) {
		switch(type) {
			case "Elettore":
				return new ElettoreDaoImpl();
			case "Scrutatore":
				return new ScrutatoreDaoImpl();
			case "SessioneDiVoto":
				return new SessioneDiVotoDaoImpl();
			case "Voto":
				return new VotoDaoImpl();
			case "Candidato":
				return new CandidatoDaoImpl();
			case "Partito":
				return new PartitoDaoImpl();
			case "NuovoUtente":
				return new NuovoUtenteDaoImpl();
			default:
				return null;
		}
	}
}

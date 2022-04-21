package barbiere;

public abstract class Sala {
	protected  int postiOccupati, capienza;
	
	public Sala(int dim) {
		capienza=dim;
		postiOccupati=0;
	}// costruttore
	
	public abstract void taglioCapelli() throws InterruptedException;
	public abstract boolean attendiTaglio() throws InterruptedException;
	
	public String toSting() {
		return ("Posti occupati: "+postiOccupati);

	}
	
	
}

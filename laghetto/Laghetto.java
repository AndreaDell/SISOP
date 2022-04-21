package laghetto;

public abstract class Laghetto {
	protected int MIN_PESCI, MAX_PESCI, pesciCorrenti;
	protected final int PESCA=0, RIPOPOLAMENTO=1;
	
	public Laghetto(int min, int max) {
		MIN_PESCI=min;
		MAX_PESCI=max;
		pesciCorrenti=min+((max-min)/2);
	}//costruttore
	
	abstract void inizia(int t) throws InterruptedException;
	abstract void finisci(int t);
	

}

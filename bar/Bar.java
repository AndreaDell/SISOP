package bar;

public abstract class Bar {
	protected int banconeDisponibile=4;
	protected enum Azione{
		BEVI , PAGA;
	}//Azione
	
	abstract Azione scegli() throws InterruptedException;
	abstract void inizia(Azione azione);
	abstract void finisci(Azione azione);

	
	
	
}//Bar

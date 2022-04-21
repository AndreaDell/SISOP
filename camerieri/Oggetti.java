package camerieri;

public abstract class Oggetti {
	protected final int CONTENITORE=0, SCOLAPIATTI=1;
	protected int [] contatori= new int[2];
	
	
	
	
	protected abstract void put(int tipo,int quantita);
	protected abstract void get(int tipo);
	
}

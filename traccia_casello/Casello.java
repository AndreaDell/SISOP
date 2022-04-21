package traccia_casello;

import java.util.LinkedList;
import java.util.TreeMap;

public abstract class Casello {
	protected int incasso, N, T;
	public Casello(int porte, int tariffa) {
		incasso=0;
		N=porte;
		T=tariffa;
	}//costruttore
	
	

	abstract void passaggio(int porta, Veicolo v) throws InterruptedException;
		

}

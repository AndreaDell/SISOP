package visualizzatore;

import java.util.LinkedList;

public abstract class App {
	protected LinkedList<String> coda;
	
	public App() {
		coda=new LinkedList<>();
	}//Costruttore
	
	abstract void inserimento() throws InterruptedException;
	
	abstract void preleva() throws InterruptedException;
	
	
}

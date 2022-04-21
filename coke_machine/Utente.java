package coke_machine;

public class Utente extends Thread {
	private CokeMachine c;
	
	public Utente(CokeMachine ck) {
		c=ck;
	}//Utente
	
	@Override
	public void run() {
		c.rimuovi();
	}//run
	
	public static void main(String[] args) {
		CokeMachine c= new CokeMachineLC(20);
		for (int i=0;i<100;i++) {
			Utente u= new Utente(c);
			u.start();
		}
		Fornitore f= new Fornitore(c);
		f.setDaemon(true);
		f.start();
	}
	
}//Utente

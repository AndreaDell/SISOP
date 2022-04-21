package computer;

import java.util.Random;

public class Utente  extends Thread{
	private Computer comp;
	private Random r;
	
	public Utente(Computer c) {
		comp=c;
		r=new Random();
	}//costruttore
	
	@Override
	public void run() {
		while (true) {
			try {
				comp.richiesta((r.nextInt(5-1)+1),r.nextInt(2));
				comp.pausa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//while
	}//run
	
	public static void main(String[] args) {
		Computer c= new ComputerLC();
		for (int i=0;i<20;i++) {
			Utente u= new Utente(c);
			u.start();
		}
	}
}//Utente

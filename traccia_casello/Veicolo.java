package traccia_casello;

import java.util.Random;

public class Veicolo extends Thread {
	public int km;
	private Casello casello;
	private Random r= new Random();
	
	public Veicolo(Casello casello) throws InterruptedException {
		this.casello=casello;
		km=(r.nextInt(100-50)+50);
		Thread.sleep(40*km);	
	}//Costruttore

	public void run() {
		try {
			casello.passaggio(r.nextInt(casello.N),this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Casello c = new CaselloLC(5,2);
		for (int i=0;i<15;i++) {
			new Veicolo(c).start();
		}
	}
}

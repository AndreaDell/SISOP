package produttoreconsumatore;

import java.util.Random;


public class Produttore extends Thread {
	
	private Buffer buffer;
	Random r= new Random();
	public Produttore(Buffer buffer) {
		this.buffer=buffer;
	}//Costruttore
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(r.nextInt(1500-500)+500);
				int i=(r.nextInt(10-1)+1);
				buffer.put(i);
				System.out.println("Dato prodotto: "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//while
	}//run
	
}//produttore

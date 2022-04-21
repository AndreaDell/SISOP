package visualizzatore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class AppSem extends App {
	private Semaphore postiDisponibili= new Semaphore(100);
	private Semaphore postiOccupati= new Semaphore(0);
	private Semaphore mutex= new Semaphore(1);
	private Random r= new Random();
	
	public AppSem() {
		super();		
	}

	@Override
	public void inserimento() throws InterruptedException {
		int X=(r.nextInt(5-1)+1);
		postiDisponibili.acquire(X);
		mutex.acquire();
		for (int i=1;i<=X;i++) {
			String s="Stringa:  "+r.nextInt(1000);
			coda.add(s);
			System.out.println("Un utente ha aggiunto: "+s);
		}
		mutex.release();			
		postiOccupati.release(X);
	}//inserimento

	@Override
	public void preleva() throws InterruptedException {
		postiOccupati.acquire();
		mutex.acquire();
		String s=coda.remove();
		System.out.println("Il Visualizzatore ha letto: "+s);
		mutex.release();
		postiDisponibili.release();
	}//preleva
	
	public static void main(String[] args) {
		App app= new AppLC();
		for (int i=0;i<10;i++)
			new Utente(i,app).start();
		new Visualizzatore("Antonino",app).start();
	}

}//Appsem

package laghetto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class LaghettoSem extends Laghetto {
	
	private Semaphore mutex= new Semaphore(1);
	private Semaphore pescatori;
	private Semaphore addetti;
	private Semaphore turno=new Semaphore(1);
	private Random r= new Random();
	public LaghettoSem(int min, int max) {
		super(min, max);
		pescatori=new Semaphore(pesciCorrenti-min);
		addetti= new Semaphore(max-pesciCorrenti);
	}//costruttore


	@Override
	void inizia(int t) throws InterruptedException {
		if (t==PESCA) {
			turno.acquire();
			pescatori.acquire();
			mutex.acquire();
			pesciCorrenti--;
			System.out.println("Il pescatore "+Thread.currentThread().getName()+" ha pescato un pesce. Pesci correnti: "+pesciCorrenti);
			Thread.sleep(r.nextInt(800-200)+200);
			mutex.release();
			turno.release();
			System.out.println("Il pescatore "+Thread.currentThread().getName()+" sta riposando..");
			Thread.sleep(1000);
			
			
				
			
			
		}
		else {
			turno.acquire();
			addetti.acquire(10);
			mutex.acquire();
			pesciCorrenti+=10;
			Thread.sleep(r.nextInt(600-300)+300);
			System.out.println("Un addetto "+Thread.currentThread().getName()+" ha aggiunto 10 pesci. Pesci correnti: "+pesciCorrenti);
			mutex.release();
			pescatori.release(10);
			turno.release();
			System.out.println("Un addetto "+Thread.currentThread().getName()+" sta riposanso.. ");
			Thread.sleep(3000);
			
			
		}
		
	}

	@Override
	void finisci(int t) {
		// TODO Auto-generated method stub
		
	}
	
	
}

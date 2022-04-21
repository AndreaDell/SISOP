package camerieri;

import java.util.concurrent.Semaphore;

public class OggettiSem extends Oggetti {
	private Semaphore mutex= new Semaphore(1);
	private Semaphore [] liberi;
	private Semaphore [] occupati= {new Semaphore(0), new Semaphore(0)};
	
	
	public OggettiSem() {
		liberi= new Semaphore[2];
		liberi[0]= new Semaphore(50);
		liberi[1]= new Semaphore(30);
		
	}

	@Override
	protected void put(int tipo, int quantita) {
		try {
		liberi[tipo].acquire(quantita);
		mutex.acquire();
		contatori[tipo]+=quantita;
		if (tipo==CONTENITORE)
			System.out.println(Thread.currentThread().getClass()+" ha aggiunto "+ quantita+" al contenitore");
		else 
			System.out.println(Thread.currentThread().getClass()+" ha aggiunto "+ quantita+" allo scolapiatti");
		mutex.release();
		occupati[tipo].release(quantita);
		} catch(Exception e) {
		}
	}

	@Override
	protected void get(int tipo) {
		try {
		occupati[tipo].acquire();
		mutex.acquire();
		contatori[tipo]--;
		if (tipo==CONTENITORE)
			System.out.println(Thread.currentThread().getClass()+" ha prelevato dal contenitore");
		else 
			System.out.println(Thread.currentThread().getClass()+" ha prelevato dallo scolapiatti");
		mutex.release();
		liberi[tipo].release();
		}catch(Exception e) {
			
		}
	}
	

	

}

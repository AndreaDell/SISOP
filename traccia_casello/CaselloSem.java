package traccia_casello;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CaselloSem extends Casello {
	private Semaphore mutex= new Semaphore(1);
	private Semaphore arraySem [];
	public CaselloSem(int N, int T) {
		super (N,T);
		arraySem=new Semaphore[N];
		for (int i=0;i<N;i++)
			arraySem[i]= new Semaphore(1,true);
	}

	
	@Override
	protected void passaggio(int porta, Veicolo v) throws InterruptedException {
		Thread t= Thread.currentThread();
		System.out.println("Arriva il veicolo "+t);
		System.out.println("Il veicolo "+t+" si e' posizionato alla porta: "+porta+".");
		Random r= new Random();
		arraySem[porta].acquire();
		mutex.acquire();
		incasso+=(T*v.km);
		System.out.println("Il veicolo "+v.getName()+ " ha pagato "+T*v.km);
		System.out.println("L'incasso totale al momento e':"+incasso);
		Thread.sleep(r.nextInt(6000-3000)+3000);
		mutex.release();
		arraySem[porta].release();
	}//passaggio

}

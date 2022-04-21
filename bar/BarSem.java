package bar;

import java.util.concurrent.Semaphore;

import bar.Bar.Azione;

public class BarSem  extends Bar{
	private Semaphore codaBancone= new Semaphore(4,true);
	private Semaphore codaCassa= new Semaphore(1,true);
	private Semaphore mutex=new Semaphore(1);
	private int numCodaBancone=0;
	private int numCodaCassa=0;
	

	@Override
	Azione scegli() throws InterruptedException {
		mutex.acquire();
		if (numCodaCassa==0)
			return Azione.PAGA;
		else if (banconeDisponibile>0)
			return Azione.BEVI;
		else {
			if (numCodaCassa>=numCodaBancone)
				return Azione.PAGA;
		}
		mutex.release();
		return Azione.BEVI;
		
		
	}//scegli

	@Override
	void inizia(Azione azione) {
		
		
	}//inizia

	@Override
	void finisci(Azione azione) {
		// TODO Auto-generated method stub
		
	}//finisci

}//BarSem

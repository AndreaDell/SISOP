package bar;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarLC extends Bar {
	private LinkedList<Thread> codaCassa= new LinkedList<>();
	private LinkedList<Thread> codaBancone= new LinkedList<>();
	Lock l= new ReentrantLock();
	Condition attesaCassa= l.newCondition();
	Condition attesaBancone= l.newCondition();
	
	@Override
	Azione scegli() {
		l.lock();
		try {
		System.out.println(codaCassa);
		if (codaCassa.size()==0)
			return Azione.PAGA;
		else if (banconeDisponibile>0)
			return Azione.BEVI;
		else {
			if (codaCassa.size()>=codaBancone.size())
				return Azione.PAGA;
		}
		}catch(Exception e) {
			
		}finally {
			l.unlock();
		}
		return Azione.BEVI;
	}//scegli

	@Override
	void inizia(Azione azione) {
		l.lock();
//		azione=scegli();
		System.out.println(banconeDisponibile);
		try {
		if (azione==Azione.PAGA) {
			codaCassa.add(Thread.currentThread());
			while (!possoPagare(codaCassa,Thread.currentThread())) {
				attesaCassa.await();
			}//while
			codaCassa.remove();
			System.out.println("Il cliente "+Thread.currentThread().getName()+" paga il suo caffe'.");
			Thread.sleep(new Random().nextInt(500-100)+100);
			codaBancone.add(Thread.currentThread());
			attesaCassa.signalAll();
			finisci(Azione.BEVI);
		}//if
		else {
			if (banconeDisponibile>0) {
				banconeDisponibile--;
				System.out.println("Il cliente "+Thread.currentThread().getName()+" beve il caffe'...");
				Thread.sleep(new Random().nextInt(10000-5000)+5000);
				banconeDisponibile++;
			}//if
			else {
				System.out.println(codaBancone);
				codaBancone.add(Thread.currentThread());
				banconeDisponibile--;
				while (!possoBere(codaBancone,Thread.currentThread())) {
					attesaBancone.await();
				}//while
				codaBancone.remove();
				//BEVO IL CAFFE
				System.out.println("Il cliente "+Thread.currentThread().getName()+" beve il caffe'...");
				Thread.sleep(new Random().nextInt(10000-5000)+5000);
				banconeDisponibile++;
				codaCassa.add(Thread.currentThread());
				attesaBancone.signalAll();
				finisci(Azione.PAGA);
			}//else

		}//else
		}catch(Exception e) {
			
		}finally {
			l.unlock();
		}
		
	}//inizia
	
	private boolean possoPagare(LinkedList<Thread> coda, Thread t) {
		return t==coda.getFirst();
	}//possoPagare
	
	private boolean possoBere(LinkedList<Thread> coda, Thread t) {
		return banconeDisponibile>0 && t==coda.getFirst();
	}//possoBere

	@Override
	void finisci(Azione azione) {
		l.lock();
		try {
		if (azione==Azione.PAGA) {
			while (!possoPagare(codaCassa,Thread.currentThread())) {
				attesaCassa.await();
			}//while
			codaCassa.remove();
			System.out.println("Il cliente "+Thread.currentThread().getName()+" paga il suo caffe'.");
			Thread.sleep(new Random().nextInt(500-100)+100);
			attesaCassa.signalAll();
		}//if
		else {
			if (banconeDisponibile>0) {
				banconeDisponibile--;
				System.out.println("Il cliente "+Thread.currentThread().getName()+" beve il caffe'...");
				Thread.sleep(new Random().nextInt(10000-5000)+5000);
				banconeDisponibile++;
			}//if
			else {
				banconeDisponibile--;
				while (!possoBere(codaBancone,Thread.currentThread())) {
					attesaBancone.await();
				}//while
				codaBancone.remove();
				//BEVO IL CAFFE
				System.out.println("Il cliente "+Thread.currentThread().getName()+" beve il caffe'...");
				Thread.sleep(new Random().nextInt(10000-5000)+5000);
				banconeDisponibile++;
				attesaBancone.signalAll();
				
			}//else

		}//else
		}catch(Exception e) {
			
		}finally {
			l.unlock();
		}//finally
		
	}//finisci

}//BarLC

package coke_machine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CokeMachineLC extends CokeMachine {
	Lock l= new ReentrantLock();
	Condition possoPrelevare= l.newCondition();
	Condition aggiungi= l.newCondition();
	
	public CokeMachineLC(int n) {
		super();
		count=n;
	}

	@Override
	protected void rimuovi() {
		l.lock();
		try {
			while(count<1) {
				possoPrelevare.await();
			}//while
			count--;
			System.out.println(Thread.currentThread().getName()+" ha prelevato una coca.");
			System.out.println("Disponibili: "+count);
			if (count==0)
				aggiungi.signal();
		}catch(Exception e ) {
			
		}finally {
			l.unlock();
		}
		
	}//rimuovi

	@Override
	protected void deposita() {
		l.lock();
		try {
			while (count>0) {
				aggiungi.await();
			}//while
			count+=20;
			System.out.println(Thread.currentThread().getName()+" ha AGGIUNTO 10 coche.");
			System.out.println("Disponibili: "+count);
			possoPrelevare.signalAll();
		} catch (Exception e) {
			
		}finally {
			l.unlock();
		}
		
	}//deposita

}

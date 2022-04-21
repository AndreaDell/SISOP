package trenino;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreninoLC extends Trenino {
	Lock l= new ReentrantLock();
	Condition [] arrayC= new Condition[10];
	Condition faiSalire= l.newCondition();
	public TreninoLC() {
		for (Condition c: arrayC) {
			c=l.newCondition();
		}
		array= new LinkedList[10];
		for (int i=0;i<array.length;i++) {
			array[i]=new LinkedList<>();
		}
	}//TreninoLC
	@Override
	void turSali() {
		l.lock();
		try {
			Thread t= Thread.currentThread();
			coda.add(t);
			while (array[turno].size()==0) {
				faiSalire.await();
			}
			while((array[turno].size()<10)){
				Thread z= coda.remove();
				array[turno].add(z);
				System.out.println("Sale nella cabina "+ t.getName()+" .");
			}
			
		}catch(Exception e) {
			
		}finally {
			l.unlock();
		}
		
	}

	@Override
	void turScendi() {
		l.lock();
		try {
			while ((array[turno].size()<10)){
				arrayC[turno].await();
			}
			for (Thread t: array[turno]) {
				System.out.println("Scende dalla cabina "+ t.getName()+" .");
				array[turno].remove(t);
			}//for
			
		}catch(Exception e ) {
			
		} finally {
			l.unlock();
		}
		
	}

	@Override
	void impFaiScendere() {
		if (array[turno].isEmpty()) {
			return;
		}
		for (Thread t: array[turno]) {
			System.out.println("Scende dalla cabina "+ t.getName()+" .");
			array[turno].remove(t);
		}
		arrayC[turno].signalAll();
		
	}//impFaiScendere

	@Override
	void impFaiSalire() {
		if (array[turno].size()<10)
			try {
				faiSalire.signalAll();
			}catch(Exception e) {
				
			}
			
	}//impFaiSalire

	@Override
	void impMuovi() {
		turno=(turno+1) % 10;
		
		
	}

}

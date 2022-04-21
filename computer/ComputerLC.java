package computer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ComputerLC extends Computer {
	Lock l= new ReentrantLock();
	Condition [] cond= {l.newCondition(), l.newCondition()};
	LinkedList<Thread> [] lista=new LinkedList[2];
	
	public ComputerLC() {
		for (int i=0;i<2;i++)
			lista[i]=new LinkedList<>();
	}
	@Override
	protected void richiesta(int core, int processore) throws InterruptedException {
		l.lock();
		try {
			lista[processore].add(Thread.currentThread());
			while(posso(processore,core,Thread.currentThread())) {
				System.out.println(disponibili[processore]);
				cond[processore].await();
			}
			lista[processore].remove();
			disponibili[processore]-=core;
			System.out.println(Thread.currentThread().getName()+" acquisisce "+ core+ " dal processore "+processore);
			System.out.println("Core disponibili proc0 = "+ disponibili[0]);
			System.out.println("Core disponibili proc1 = "+ disponibili[1]);
			disponibili[processore]+=core;
			cond[processore].signalAll();
			
			
			
		} catch (Exception e) {
		} finally {
			l.unlock();
		}//finally
		utilizza();
		
	}
	private boolean posso(int processore,int core, Thread th) {
		return disponibili[processore]>core && th==lista[processore].getFirst();
	}
	
	@Override
	protected void utilizza() throws InterruptedException {
		Thread.sleep(500);
		
	}

	@Override
	protected void pausa() throws InterruptedException {
		Thread.sleep(1000);
	}

}

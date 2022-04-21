package muratori;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaLC extends Casa {

	private Lock l= new ReentrantLock();
	private Condition attesaCemento= l.newCondition();
	private Condition attesaMattoni= l.newCondition();
	private LinkedList<Thread> codaCemento= new LinkedList<>();
	private LinkedList<Thread> codaMattoni= new LinkedList<>();
	public CasaLC(int n) {
		super(n);
		
	}

	@Override
	boolean inizia(int t) throws InterruptedException {
		l.lock();
		try {
		if (numFile==0) {
			termina();
			return false;
		}
		else {
		if (t==MUR_CEM) {
			Thread th=Thread.currentThread();
			l.lock();
			codaCemento.add(th);
			while (!possoCementare(codaCemento,Thread.currentThread())) {
				if (numFile<=0) return false;
				attesaCemento.await();   //ATTENDO ATTESACEMENTO
			}
			if (numFile<=0) return false;
			//INIZIO A CEMENTARE
			codaCemento.remove();
			System.out.println("Il muratore "+th.getName()+" sta preparando u cimiantu.");
			Thread.sleep(700);
			System.out.println("Il muratore "+th.getName()+" inizia a cementare...");
			Thread.sleep(1000);
			System.out.println("Il muratore "+th.getName()+" ha finito di cementare e si riposa..");
			Thread.sleep(5000);
			lavoro=1;
			codaCemento.addLast(th);
			attesaMattoni.signalAll();  //RILASCIO ATTESAMATTONI
		
			
			
		}//if
		else if(t==MUR_MAT) {
			Thread th=Thread.currentThread();
				if (numFile<=0) {
					termina();
					return false;
				}
			codaMattoni.add(th);
			while(!possoMattonare(codaMattoni, Thread.currentThread())) {
				if (numFile<=0) return false;
				attesaMattoni.await();       //ATTENDO ATTESAMATTONI
				
			}//while
			if (numFile<=0) return false;
			//INIZIO MATTONATURA
			codaMattoni.remove();
			System.out.println("Il muratore "+th.getName()+" sta preparando i mattoni");
			Thread.sleep(500);
			System.out.println("Il muratore "+th.getName()+" inizia a mattonare...");
			Thread.sleep(1000);
			numFile--;
			System.out.println("Il muratore "+th.getName()+" ha finito di mattonare e si riposa..");
			System.out.println("File RIMANENTI: "+ numFile);
			Thread.sleep(5000);
			lavoro=0;
			codaMattoni.addLast(th);
			if (numFile>0)
				attesaCemento.signalAll();    //RILASCIO ATTESA CEMENTO
			
			
		}//elseif
		}//else grande
		}catch(Exception e) {
		}finally {
			l.unlock();
		}
		return true;
	}//inizia

	private boolean possoMattonare(LinkedList<Thread> codaMattoni, Thread th1) {
		return (lavoro==1 && codaMattoni.getFirst()!=th1);
	}

	private boolean possoCementare(LinkedList<Thread> coda, Thread th) {
		return  (lavoro==0 && codaCemento.getFirst()!=th);
	}

	@Override
	void termina() {
		System.out.println("I muratori hanno finito la casa e se ne vanno.");
	}//termina

}

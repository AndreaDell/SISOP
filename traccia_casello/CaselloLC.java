package traccia_casello;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloLC extends Casello{

	private Lock l= new ReentrantLock();
	private Condition[] portaC;
	private LinkedList<Thread> [] fila;
	public CaselloLC(int porte, int tariffa) {
		super(porte, tariffa);
		portaC= new Condition[porte];
		fila= new LinkedList[porte];
		
		for (int i=0;i<porte;i++) {
			portaC[i]= l.newCondition();
			fila[i]= new LinkedList<>();
		}
		
		
	}

	@Override
	void passaggio(int porta, Veicolo v) throws InterruptedException {
		Thread t= Thread.currentThread();
		Random r= new Random();
		System.out.println("Il veicolo "+t+" si e' posizionato alla porta: "+porta+".");
		l.lock();
		try {
			fila[porta].add(t);
			while(!mioTurno(porta,t)) {
				portaC[porta].await();
			}
			incasso+=T*v.km;
			fila[porta].remove();
			System.out.println("Il veicolo "+v.getName()+ " ha pagato "+T*v.km);
			System.out.println("L'incasso totale al momento e':"+incasso);
			Thread.sleep(r.nextInt(6000-3000)+3000);
			
		} catch (Exception e) {
		}finally {
			l.unlock();
		}
	}//passaggio
	private boolean mioTurno(int porta, Thread t) {
		return fila[porta].getFirst()==t;
	}

}

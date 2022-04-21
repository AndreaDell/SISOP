package azienda_agricola;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLC extends AziendaAgricola{
	private LinkedList<Thread> codaCassa;
	private LinkedList<Thread> codaMagazzino;
	private Lock l= new ReentrantLock();
	private Lock l2= new ReentrantLock();
	private Condition ricaricaMagazzino= l2.newCondition();
	private Condition attesaCassa=l.newCondition();
	private Condition attesaMagazzino= l.newCondition();

	public AziendaAgricolaLC() {
		super();
		codaCassa=new LinkedList<>();
		codaMagazzino=new LinkedList<>();

	}
	@Override
	void pagamento(int nSacchi) throws InterruptedException {
		Thread t=Thread.currentThread();
		l.lock();
		try {
			codaCassa.add(t);
			while(!possoPagare(codaCassa,t)) {
				attesaCassa.await();
			}//while
			incasso+=costoSacchi*nSacchi;
			sacchiVenduti+=nSacchi;
			System.out.println("Sono stati venduti "+sacchiVenduti+" sacchi.");
			System.out.println("L'incasso attuale e': "+incasso);
			codaCassa.remove();
			attesaCassa.signalAll();
			
		} catch (Exception e) {
		}finally {
			l.unlock();
		}//finally
		
	}//pagamento

	private boolean possoPagare(LinkedList<Thread> coda, Thread t) {
		return t==coda.getFirst();
	}
	@Override
	void ritiro(int nSacchi) throws InterruptedException {
		Thread t=Thread.currentThread();
		l.lock();
		try {
			codaMagazzino.add(t);
			while(!possoRitirare(codaMagazzino,t)) {
				attesaMagazzino.await();
			}//while
			for (int i=1;i<=nSacchi;i++) {
				if (sacchiDisponibili<=0) {
					ricaricaMagazzino.signalAll();
//					Thread.sleep(10000);
				}//if
				sacchiDisponibili--;
				System.out.println("E' stato ritirato il sacco n°: "+i+" Lo sto portando in macchina...");
				System.out.println("I sacchi attualmente disponibili in magazzino sono: "+sacchiDisponibili);
				Thread.sleep(500);
				attesaMagazzino.signalAll();
				}//for
			codaMagazzino.remove();
			
		} catch (Exception e) {
		}finally {
			l.unlock();
		}//finally
		
	}//ritiro
	
	private boolean ciSonoSacchi(int sacchiDisponibili) {
		return sacchiDisponibili>0;
	}

	private boolean possoRitirare(LinkedList<Thread> coda, Thread t) {
		return t==coda.getFirst();
	}//possoRitirare
	
	@Override
	void ricarica() throws InterruptedException {
		l.lock();
		try {
		while (sacchiDisponibili>0) {
			ricaricaMagazzino.await();
		}//while
		System.out.println("Il magazziniere sta ricaricandp i sacchi...");
		sacchiDisponibili+=20;
		//Thread.sleep(10000);
		System.out.println("Il magazziniere ha ricaricato i sacchi.");
		attesaMagazzino.signalAll();
		}catch(Exception e) {
			
		}finally {
			l.unlock();
		}
	}//ricarica
	

}//AziendaAgricolaLC

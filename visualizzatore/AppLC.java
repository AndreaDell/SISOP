package visualizzatore;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppLC extends App{
	private Random r= new Random();
	Lock l=new ReentrantLock();
	Condition postiDisponibili= l.newCondition();
	Condition postiOccupati=l.newCondition();
	
	public AppLC() {
		super();
	}//Costruttore
	
	@Override
	void inserimento() throws InterruptedException {
		int X=(r.nextInt(5-1)+1);
		l.lock();
		try {
			while (!cePosto(coda,X)) {
				postiDisponibili.await();
			}
			for (int i=1;i<=X;i++) {
				String s="Stringa:  "+r.nextInt(1000);
				coda.add(s);
				System.out.println("Un utente ha aggiunto: "+s);
			}
			postiOccupati.signal();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			l.unlock();
		}//finally
		
	}//inserimento
	
	private boolean cePosto(LinkedList<String> coda, int X) {
		return coda.size()<=(100-X);
	}//ceposto

	@Override
	void preleva() throws InterruptedException {
		l.lock();
		try {
			while(!codaVuota(coda)) {
				postiOccupati.await();
			}
			String s=coda.remove();
			System.out.println("Il Visualizzatore ha letto: "+s);
			postiDisponibili.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			l.unlock();
		}//finally
		
	}//preleva

	private boolean codaVuota(LinkedList<String> coda) {
		return coda.size()>0;
	}
}

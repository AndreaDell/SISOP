package azienda_agricola;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSem extends AziendaAgricola {
	
	private Semaphore cassa= new Semaphore(1,true);
	private Semaphore magazzino= new Semaphore(1,true);
	private Semaphore magRic= new Semaphore(0);
	private Semaphore mutex= new Semaphore(1);
	public AziendaAgricolaSem() {
		super();
	}//Costruttore
	

	@Override
	void pagamento(int nSacchi) throws InterruptedException {
		cassa.acquire();
		incasso+=costoSacchi*nSacchi;
		sacchiVenduti+=nSacchi;
		System.out.println("Sono stati venduti "+sacchiVenduti+" sacchi.");
		System.out.println("L'incasso attuale e': "+incasso);
		cassa.release();
	}//pagamento

	@Override
	void ritiro(int nSacchi) throws InterruptedException {
		magazzino.acquire();
		for (int i=1;i<=nSacchi;i++) {
			if (sacchiDisponibili==0) {
				magRic.release();
				Thread.sleep(10000);
			}
			mutex.acquire();
			sacchiDisponibili--;
			System.out.println("E' stato ritirato il sacco n°: "+i+" Lo sto portando in macchina...");
			System.out.println("I sacchi attualmente disponibili in magazzino sono: "+sacchiDisponibili);
			mutex.release();
			Thread.sleep(1000);
			
		}//for
		magazzino.release();
		
	}//ritiro

	@Override
	void ricarica() throws InterruptedException {
		magRic.acquire();
		mutex.acquire();
		System.out.println("Il magazziniere sta ricaricandp i sacchi...");
		Thread.sleep(10000);
		sacchiDisponibili=200;
		System.out.println("Il magazziniere ha ricaricato i sacchi.");
		mutex.release();
		
	}//ricarica
	
	
	

}

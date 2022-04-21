package azienda_agricola;

public class Magazziniere extends Thread {
	private AziendaAgricola azienda;
	
	public Magazziniere(AziendaAgricola azienda) {
		this.azienda=azienda;
	}//Costruttore
	
	public void run() {
		try {
			azienda.ricarica();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//run

}//Magazziniere

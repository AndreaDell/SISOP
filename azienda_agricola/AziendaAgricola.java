package azienda_agricola;

import java.util.Random;

public abstract class AziendaAgricola {
	protected int sacchiDisponibili;
	protected int incasso;
	protected final int costoSacchi;
	protected int sacchiVenduti;
	
	public AziendaAgricola() {
		sacchiDisponibili=20;
		incasso=0;
		costoSacchi=3;
		sacchiVenduti=0;
	}
	
	public int decisione() {
		return (new Random().nextInt(10-1)+1);
	}
	
	abstract void pagamento(int nSacchi) throws InterruptedException;
	
	abstract void ritiro(int nSacchi) throws InterruptedException;
	
	abstract void ricarica() throws InterruptedException;
	
	public String toString() {
		StringBuilder st= new StringBuilder(30);
		st.append("Oggi sono stati venduti "+sacchiVenduti+" sacchi." +"/n");
		st.append("L'incasso totale è stato di: "+incasso);
		return st.toString();
		
	}

}

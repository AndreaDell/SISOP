package azienda_agricola;



public class Clienti extends Thread{
	private int id;
	private AziendaAgricola azienda;
	
	public Clienti(int id, AziendaAgricola azienda) {
		this.id=id;
		this.azienda=azienda;
	}//costruttore
	
	public void run() {
		int nSacchi= azienda.decisione();
		try {
			azienda.pagamento(nSacchi);
			azienda.ritiro(nSacchi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//catch
		
	}//run
	
	public static void main(String[] args) {
		AziendaAgricola azienda= new AziendaAgricolaLC();
		new Magazziniere(azienda).start();
		for (int i=0;i<10;i++)
			new Clienti(i,azienda).start();
		

	}//main
	
}//Clienti

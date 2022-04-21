package esercizio_2_2;



public class ProdottoScalare extends Thread{
	private int a[]; 
	private int b[];
	private int inizio, fine;
	private int prodottoScalare=0;
	
	public ProdottoScalare(int [] a , int [] b , int inizio , int fine) {
		this.a=a;
		this.b=b;
		this.inizio=inizio;
		this.fine=fine;
	}//costruttore
	
	public void run() {
		for (int i=inizio;i<=fine;i++) 
			prodottoScalare+=a[i]*b[i];
		
	}//run 
	
	public int getProdotto() {
		try {
			this.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return prodottoScalare;
	}
}//ProdottoScalare

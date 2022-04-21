package trenino;

public class Impiegato extends Thread {
	private Trenino t;
	public Impiegato(Trenino tt) {
		t=tt;
	}//costruttore
	
	@Override
	public void run() {
		while (true) {
			t.impFaiScendere();
			t.impFaiSalire();
			t.impMuovi();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}//run 
	
}//Impiegato

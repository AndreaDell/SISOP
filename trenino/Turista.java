package trenino;

public class Turista extends Thread {
	private Trenino t;
	public Turista(Trenino tt) {
		t=tt;
	}//costruttore
	
	@Override
	public void run() {
		t.turSali();
		t.turScendi();
	}
	
	
	public static void main(String[] args) {
		Trenino t= new TreninoLC();
		for (int i=0;i<30;i++) {
			Turista a= new Turista(t);
			a.start();
		}
		Impiegato i= new Impiegato(t);
		i.start();
	}//main 
}//Turista

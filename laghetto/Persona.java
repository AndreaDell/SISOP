package laghetto;

public class Persona extends Thread {
	private Laghetto laghetto;
	private int tipo;
	public Persona (Laghetto l, int t) {
		laghetto=l;
		tipo=t;
	}//costruttore
	
	@Override
	public void run() {
		try {
			laghetto.inizia(tipo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Laghetto l= new LaghettoSem(50,200);
		for (int i=0;i<40;i++) {
			new Persona(l,0).start();
			if (i<5) 
				new Persona(l,1).start();
		}//for
	}//main
		
	}//Persona
package funivia;

public class Turista extends Thread{
	 private Funivia funivia;
	 private int tipo;
	 
	 public Turista(Funivia funivia, int tipo) {
		 this.funivia=funivia;
		 this.tipo=tipo;
	 }//costruttore
	 
	 @Override
	public void run() {
		funivia.turistaSali(tipo);
		funivia.turistaScendi(tipo);
	}//run
	 
	 public static void main(String[] args) {
		 Funivia f= new FuniviaLC();
		 Pilota p= new Pilota(f);
		 p.setDaemon(true);
		 p.start();
		 for (int i=0;i<18;i++)
			 new Turista(f,0).start();
		 for (int i=0;i<9;i++)
			 new Turista(f,1).start();
	}
}//Turista

package funivia;

public class Pilota extends Thread {
	private Funivia funivia;
	
	public Pilota(Funivia funivia) {
		this.funivia=funivia;
	}//costruttore
	
	@Override
	public void run() {
		while (true) {
			funivia.pilotaStart();
			funivia.pilotaEnd();
		}//while
	}//run

}//Pilota

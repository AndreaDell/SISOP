package visualizzatore;

public class Utente extends Thread {
	private int id;
	private App app;
	
	public Utente(int id, App app) {
		this.id=id;
		this.app=app;
	}//Costruttore
	
	public void run() {
		while (true) {
			try {
				app.inserimento();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//while
	}//run
	
}//Utente

package visualizzatore;

public class Visualizzatore extends Thread {
	private String name;
	private App app;
	public Visualizzatore(String name, App app) {
		this.name=name;
		this.app=app;
	}
	
	public void run() {
		while (true) {
			try {
				app.preleva();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//while
	}//run

}//Visualizzatore

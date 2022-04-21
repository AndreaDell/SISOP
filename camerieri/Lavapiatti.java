package camerieri;

public class Lavapiatti extends Thread{
private Oggetti o;
	
	public Lavapiatti(Oggetti og) {
		o=og;
	}//costruttore
	
	@Override
	public void run() {
		while (true) {
			try {
			o.get(o.CONTENITORE);
			System.out.println("Lavaggio piatto in corso");
			Thread.sleep(1500);
			o.put(o.SCOLAPIATTI, 1);
			}catch (Exception e) {
			}
		}//while
	}//run

}//lavapiatti

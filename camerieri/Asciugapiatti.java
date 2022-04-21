package camerieri;

public class Asciugapiatti extends Thread {
	private Oggetti o;
	
	public Asciugapiatti(Oggetti og) {
		o=og;
	}//costruttore
	
	@Override
	public void run() {
		while (true) {
			try {
			o.get(o.SCOLAPIATTI);
			System.out.println("Piatto pulito");
			Thread.sleep(1000);
			}catch (Exception e) {
			}
		}//while
	}//run
}

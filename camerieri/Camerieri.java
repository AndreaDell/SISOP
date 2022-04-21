package camerieri;

public class Camerieri extends Thread{
	private Oggetti o;
	
	public Camerieri(Oggetti og) {
		o=og;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
			o.put(o.CONTENITORE, 4);
			Thread.sleep(2000);
			}catch (Exception e) {
			}
		}//while
	}//run
	
	public static void main(String[] args) {
		Oggetti o = new OggettiSem();
		for (int i=0;i<10;i++) {
			Camerieri c= new Camerieri(o);
			c.start();
		}
		for (int i=0;i<6;i++) {
			Lavapiatti l= new Lavapiatti(o);
			l.start();
		}
		for (int i=0;i<3;i++) {
			Asciugapiatti a= new Asciugapiatti(o);
			a.start();
		}
	}//main 
}//Camerieri

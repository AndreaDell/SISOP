package bar;

public class Persona extends Thread {
	private Bar bar;
	
	public Persona(Bar bar) {
		this.bar=bar;
	}
	
	
	public void run() {
		bar.inizia(bar.scegli());
	}//run
	
	public static void main(String[] args) {
		Bar bar=new BarLC();
		for (int i=0;i<100;i++)
			new Persona(bar).start();
	}//main
	
}//Persona

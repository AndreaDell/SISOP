package coke_machine;

public class Fornitore extends Thread {
	private CokeMachine c;
	
	public Fornitore(CokeMachine ck) {
		c=ck;
	}//Costruttore 
	
	@Override
	public void run() {
		while(true) {
			try {
				c.deposita();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//while
	}//run
}//Fornitore

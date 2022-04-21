package muratori;

public class Muratore extends Thread {
	private int id;
	private Casa casa;
	private int tipo;
	
	public Muratore(int id, int t, Casa c) {
		this.id=id;
		tipo=t;
		casa=c;
	}//muratore
	
	public void run() {
		
			try {
				while(casa.inizia(tipo)) {
				}
				System.out.println("SS");
//				casa.inizia(tipo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
	}//run
	
	public static void main(String[] args) {
		Casa c= new CasaLC(1);
		for (int i=0;i<5;i++) 
			new Muratore(i,c.MUR_CEM,c).start(); ;
		for (int i=0;i<7;i++) 
			new Muratore(i,c.MUR_MAT,c).start();;			
	}//main
}//muratore

package produttoreconsumatore;

import java.util.Random;

public class Consumatore extends Thread{
	private Buffer buffer;
	Random r= new Random();
	
	public Consumatore(Buffer buffer) {
		this.buffer=buffer;
	}
	
	public void run() {
		long l=System.currentTimeMillis();
		while (true) {
			try {
				Thread.sleep(r.nextInt(1500-500)+500);
				int i=buffer.get();
				System.out.println("Dato preso: "+i);
				if (System.currentTimeMillis()-l>2000)
					System.out.println(buffer.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}

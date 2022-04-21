package cinque_filosofi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Semaphore;



public class Tavolo {

	private static Semaphore [] bacchette= new Semaphore[5];
	private static boolean [] mangianti= new boolean[5];
	public Tavolo() throws InterruptedException {
		for (int i=0;i<5;i++) {
			new Filosofo(i).start();
			bacchette[i]= new Semaphore(1);
			
		}
		int cnt=0;
		HashSet<boolean[]> set= new HashSet<>();
		while (true) {
			cnt=0;
			Thread.sleep(1000);
			set.add(mangianti);
			for (int i=0;i<5;i++) {
				if (mangianti[i]) {
					System.out.print(" 1 ");
					cnt++;
				}
					else 
						System.out.print(" 0 ");		
			}
			System.out.println("Filosofi che stanno mangiando:"+cnt);
		}
	}
	
	private static class Filosofo extends Thread{
		private int pos;
		public  Filosofo (int pos) {
			this.pos=pos;
		}
		public void run() {
			Random r= new Random();
			while (true) {
				try {
					bacchette[pos].acquire();
					bacchette[(pos+1)%5].acquire();
//					System.out.println("Il Filosofo "+pos+" sta MANGIANDO.");
					mangianti[pos]=true;
					Thread.sleep(r.nextInt(1500-500)+500);
					bacchette[pos].release();
					bacchette[(pos+1)%5].release();
					mangianti[pos]=false;
					
//					System.out.println("Il Filosofo "+pos+" sta pensando.");
					Thread.sleep(r.nextInt(1500-500)+500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//catch
			}//while
		}//run
	}//Filosofo
	
	public static void main(String[] args) throws InterruptedException {
		new Tavolo();
	}
	
}//Tavolo

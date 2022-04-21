package esercizio_4_9;

import java.util.concurrent.Semaphore;

public class NThread {
	private static Semaphore [] mutexArray;
	private static int N;
	
	public NThread(int n) {
		N=n;
		mutexArray= new Semaphore[N];
		mutexArray[0]=new Semaphore(1);
		for (int i=1;i<N;i++) {
			mutexArray[i]=new Semaphore(0);
		}
		for (int i=0;i<N;i++) {
			new TH(i).start();
		}
	}//Costruttore
	
	private static class TH extends Thread{
		private int id;
		public TH(int i) {
			id=i;
		}//costruttore TH
		
		
		public void run() {
			try {
				mutexArray[id].acquire();
				System.out.print(" "+id+" ");
				if (id!=mutexArray.length-1) 
					mutexArray[id+1].release();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}//run
	}

	public static void main(String[] args) {
		new NThread(10);
	}//main
}//NThread

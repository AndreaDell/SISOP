package esercizi_comp_codice;

import java.util.concurrent.Semaphore;

public class Prova {
	public static Semaphore sem= new Semaphore(1);
	
	public static void main(String[] args) throws InterruptedException {
		MyThread[] threads=new MyThread[8];
		for (int i=0;i<threads.length;i++) {
			threads[i]=new MyThread(i,threads);
			threads[i].start();
		}
	}//main
	
	static class MyThread extends Thread{
		private int myId;
		private MyThread[] threads;
		
		public MyThread(int id, MyThread[]  t) {
			myId=id;
			threads=t;
		}//costruttore
		
		@Override
		public void run() {
			try {
				if (myId%2 ==0)
					sem.acquire();
				System.out.println("T"+myId+" "+getState());
			}catch(Exception e) {
				
			}
		}//run
		
	}//MyThread

}//Prova

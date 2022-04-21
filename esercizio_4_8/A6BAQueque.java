package esercizio_4_8;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class A6BAQueque {
	private static Semaphore semA= new Semaphore(1);
	private static Semaphore semB= new Semaphore(0);
	private static Semaphore semC= new Semaphore(0); 
	private static int cnt=0;
	
	
	static class A extends Thread {
		public void run() {
			try {
			semA.acquire();
			System.out.print(" A ");
			if (semB.getQueueLength() - semC.getQueueLength()> 0)
				semB.release(6);
			else if (semC.getQueueLength() - semB.getQueueLength()> 0) 
				semC.release(6);
			else {
				Random r= new Random(1);
				if (r.nextDouble()>0.5) {
					System.out.print("Cazzo");
					semB.release(6);
				}
				else 
					semC.release(6);
			}
			
			}catch(InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//run
	}//A
	
	static class B extends Thread{
		public void run() {
			try {
			semB.acquire();
			System.out.print("B");
			cnt++;
			if(cnt==6)  {
				cnt=0;
				semA.release();
			}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//run
	}//B
	
	static class C extends Thread{
		public void run() {
			try {
				semC.acquire();
				System.out.print("C");
				cnt++;
				if (cnt==6) {
					cnt=0;
					semA.release();
				}
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}//run
	}//C 
	
	public static void main(String [] Args) {
		while (true) {
			new A().start();
			new B().start();
			new C().start();
			try {
				Thread.sleep(200);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}//while
	}//main
}

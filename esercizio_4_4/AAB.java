package esercizio_4_4;

import java.util.concurrent.Semaphore;

public class AAB {
	private static Semaphore semA= new Semaphore(2);
	private static Semaphore semB= new Semaphore(0);
	private static int cont=0;
	
	
	static class A extends Thread {
		public void run() {
			try {
			semA.acquire();
			System.out.print("A");
			semB.release();
		
			
			}catch(InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//run
	}//A
	
	static class B extends Thread{
		
		public void run() {
			try {
			semB.acquire(2);
			System.out.print("B ");
			semA.release(2);
		
			}catch(InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//run
	}//B
	
	public static void main(String [] Args) {
		while (true) {
			new A().start();
			new B().start();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}//while
	}//main
	
}//AB 
package esercizio_4_8;

import java.util.concurrent.Semaphore;


public class A6BA {
	private static Semaphore semA= new Semaphore(1);
	private static Semaphore semB= new Semaphore(0);
	private static Semaphore semC= new Semaphore(0);
	private static boolean bool=true; 
	private static int cnt=0;
	
	
	static class A extends Thread {
		public void run() {
			try {
			semA.acquire();
			System.out.print(" A ");
			if (bool)
				semB.release(6);
			else 
				semC.release(6);
			
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
				bool=false;
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
					bool=true;
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

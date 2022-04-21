package esercizio_4_4;

import java.util.concurrent.Semaphore;

import esercizio_4_4.AAB.A;
import esercizio_4_4.AAB.B;

// AAAAB AAAB AAB AB 
public class AAAAB {
	static int n=4;
	static Semaphore semA= new Semaphore(n);
	static Semaphore semB= new Semaphore(0);
	static int cnt=0;
	static class A extends Thread {
		public void run() {
			try {
			semA.acquire();
			System.out.print("A");
			if (semA.availablePermits()==0) {
				semB.release();
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
			System.out.print("B ");
			cnt++;
			
			if (n-cnt==0) {
				cnt=0;
				semA.release(n);
			}
			else
				semA.release(n-cnt);
		
			}catch(InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//run
	}//B
	
	
	
	
	public static void main(String[] args) {
		while (true) {
			new A().start();
			new B().start();
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}//while

	}

}

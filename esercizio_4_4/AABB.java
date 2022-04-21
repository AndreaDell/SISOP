package esercizio_4_4;

import java.util.concurrent.Semaphore;

import esercizio_4_4.AAB.A;
import esercizio_4_4.AAB.B;

public class AABB {
	
	static Semaphore semA= new Semaphore(2);
	static Semaphore semB= new Semaphore(0);
	static int cnt=0;
	
	static class A extends Thread {
		
		public void run() {
			try {
			semA.acquire();
			System.out.print("A");
			cnt++;
			if (cnt>1) {
				cnt=0;
				System.out.print("---Azzera cnt A---");
				semB.release(2);
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
			if (cnt>1) {
				cnt=0;
				System.out.println("---Azzera cnt B---");
				System.out.println();
				semA.release(2);
			}
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
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}//AABB

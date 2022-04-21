package esercizio_4_1;

import java.util.concurrent.Semaphore;

public class ABTest {
	static Semaphore mutexA = new Semaphore(1);
	static Semaphore mutexB = new Semaphore(0);
	
	static class A extends Thread{
		public void run() {
			try {
			mutexA.acquire();
			System.out.print("A");
			mutexB.release();
			} catch(Exception e) {
				e.printStackTrace();
			}//catch
		}//run
	}//A
	
	
	static class B extends Thread{
		public void run() {
			try {
				mutexB.acquire();
				System.out.print("B ");
				mutexA.release();
				} catch(Exception e) {
					e.printStackTrace();
				}//catch
		}//run
	}//B
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			
			new A().start();
			new B().start();
			Thread.sleep(100);
		}
	}

}

package esercizi_comp_codice;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
		private int myId;
		private MyThread[] t;
		
		public MyThread(int id, MyThread[] m) {
			myId=id;
			t=m;
		
		}//costruttore
		
		public void run() {
			try {
				int s= myId+1;
				if (s<t.length)
					t[s].join();
				TimeUnit.SECONDS.sleep(myId);
				System.out.println("T"+myId+" "+this.getState());
			} catch (Exception e) {
				// TODO: handle exception
			}//catch
		}//run
		

	
	public static void main(String[] args) throws InterruptedException {
		MyThread[] t= new MyThread[6];
		for (int i=0;i<t.length;i++)
			t[i]= new MyThread(i,t);
		for (int i=0;i<t.length;i++)
			t[i].start();
		
	}//main
}//prova

package esercizio_4_3;

import java.util.Arrays;
import java.util.concurrent.Semaphore;



public class TS {
		private int [][] matrix;
		static Semaphore mutex= new Semaphore(1);
		
		public TS(int N, int M, int X) {
			matrix=new int[N][M];
			for (int x=0;x<X;x++) {
			for (int i=0;i<N;i++) {
				new Incr(matrix[i]).start();
				new Decr(matrix[i]).start();
			}//N
			}//X
			System.out.println(Arrays.deepToString(matrix));	
		}
			
			private static class Incr extends Thread{
				int [] riga;
				public Incr(int [] r) {
					riga=r;
				}
				public void run() {
					for (int i=0;i<riga.length;i++)
						try {
						mutex.acquire();
						riga[i]++;
						mutex.release();
						}catch(Exception e) {
							e.printStackTrace();
						}//catch
				
				}
			}//Incr
				
		private static class Decr extends Thread{
			int [] riga;
			public Decr(int [] c) {
					riga=c;
			}//Costruttore
			public void run() {
				for (int i=0;i<riga.length;i++)
					try {
						mutex.acquire();
						riga[i]--;
						mutex.release();
					}catch(Exception e) {
						e.printStackTrace();
					}//catch
			}//run		
		}//Decr
	
	
	
	

	public static void main(String[] args) {
		new TS(4,8,2000);

	}//Main

}//TS

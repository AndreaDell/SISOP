package esercizio_4_3;

import java.util.Arrays;

public class NoTS {
	private int [][] matrix;
	
	public NoTS(int N, int M, int X) {
		matrix=new int[N][M];
		for (int x=0;x<X;x++) {
		for (int i=0;i<N;i++) {
			new Incr(matrix[i]).start();
			new Decr(matrix[i]).start();
		}//N
		}//X
		System.out.println(Arrays.deepToString(matrix));	
				
	}//Costruttore
	
	private static class Incr extends Thread{
		int [] riga;
		public Incr(int [] r) {
			riga=r;
			
		}
		public void run() {
			for (int i=0;i<riga.length;i++)
				riga[i]++;
		}
	}
		
		private static class Decr extends Thread{
			int [] riga;
			public Decr(int [] c) {
				riga=c;
			}//Costruttore
			public void run() {
				for (int i=0;i<riga.length;i++)
					riga[i]--;
			}//run
	}
	
    public static void main(String[] args) {
		new NoTS(5,6,1000);
	}
		
	}//NoTs


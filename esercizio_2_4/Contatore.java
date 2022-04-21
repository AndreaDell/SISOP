package esercizio_2_4;

public class Contatore extends Thread {
	private int [][] matrice;
	private int X, Y;
	private int contX, contY, riga;
	
	public Contatore(int [][] matrice, int riga, int X, int Y) {
		this.matrice=matrice;
		this.riga=riga;
		this.X=X;
		this.Y=Y;
	}//costruttore
	
	public void run() {
		for (int i=0;i<matrice[0].length;i++) {
			if (matrice[riga][i]==X)
				contX++;
			else if (matrice[riga][i]==Y)
				contY++;
		}
	}//run	

	public int getX() {
		try {
			this.join();
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
		return X;
	}//getX
	
	public int getY() {
		try {
			this.join();
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
		return Y;
	}//getY

}

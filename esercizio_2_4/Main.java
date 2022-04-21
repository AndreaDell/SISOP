package esercizio_2_4;

public class Main {

	public static void main(String[] args) {
		int [][] a= { 	{5,8,6,2,1},
						{5,7,5,2,5},
						{8,4,5,1,0},
						{0,5,8,4,5} };
		
		int k=a.length;
		int X=5;
		int Y=1;
		//creazione k Thread
		Contatore [] cc= new Contatore[k];
		
		//for che fa partire thread
		for (int i=0;i<k;i++) {
			cc[i]= new Contatore(a,i,X,Y);
			cc[i].start();
		}
		
		//for che conta tutti i contatori
		int contaX=0;
		int contaY=0;
		for (int i=0;i<k;i++) {
			contaX+=cc[i].getX();
			contaY+=cc[i].getY();
		}
		System.out.println(X+": "+contaX);
		System.out.println(Y+": "+contaY);
		System.out.println(contaX>contaY);
		}//main
	
	}//Main (Classe)



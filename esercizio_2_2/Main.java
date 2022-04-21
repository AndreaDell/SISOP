package esercizio_2_2;

public class Main {

	public static void main (String [] args) {
		int a[] = { 1, 5, 3, 6,/**/ 1 , 3, 4, 2,/**/ 56, 7, 7, 2 };
		int b[] = { 2, 3, 5, 1,/**/ 32, 4, 3, 4,/**/ 3 , 4, 3, 21 };
		
		if (a.length!=b.length) 
			throw new RuntimeException("Lunghezze differenti!");
		
		int m=3;
		int porzione=a.length/m;
		ProdottoScalare [] ps = new ProdottoScalare[m];
		
		
		//si creano 3 thread che lavorano su porzioni diverse degli array
		for (int i=0;i<m;i++) {
			int inizio=i*porzione;
			int fine=inizio+porzione-1;
			ps[i]= new ProdottoScalare(a,b,inizio,fine);
			ps[i].start();
		}
		
		int pscalare=0;
		for (int i=0;i<ps.length;i++) {
			pscalare+=ps[i].getProdotto();
			System.out.println("Il prodotto scalare del thread "+i+" = " +ps[i].getProdotto());
		}//for
		
		System.out.println("Il prodotto scalare finale tra i due array e': "+pscalare);
		
		
	}//main 		
}//Esecuzione 

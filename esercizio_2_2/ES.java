package esercizio_2_2;

public class ES {
	public static void main(String[] args) {
		int a[] = { 1, 5, 3, 6,/**/ 1 , 3, 4, 2,/**/ 56, 7, 7, 2 };
		int b[] = { 2, 3, 5, 1,/**/ 32, 4, 3, 4,/**/ 3 , 4, 3, 21 };
		if(a.length!=b.length) {
			throw new RuntimeException("I due vettori non hanno la stessa dimensione");
		}		
		
		int n = a.length;//12
		int m = 3;
		
		ProdottoScalare p[] = new ProdottoScalare[m];
		int porzione = n / m;
		for (int i = 0; i < p.length; i++) {
			int inizio = i * porzione;
			int fine = inizio + porzione - 1;
			p[i] = new ProdottoScalare(a, b, inizio, fine);
			p[i].start();
		}
		
//		//il main ha creato 3 thread che lavorano in parallelo sui dati (4 flussi: Main+3Thread)
//		
//		for (int i = 0; i < p.length; i++) {
//			try {
//				//Il main attende p[0], poi p[1], poi p[2]
//				p[i].join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		int ps = 0;
		for (int i = 0; i < p.length; i++) {
			ps += p[i].getProdotto();
			System.out.println("Prodotto scalare del thread " + i + "="
					+ p[i].getProdotto());
		}
		System.out.println("Prodotto scalare = " + ps);
	}
}

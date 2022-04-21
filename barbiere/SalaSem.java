package barbiere;

import java.util.concurrent.Semaphore;

public class SalaSem extends Sala {
	private Semaphore barbiereSem= new Semaphore(1);
	private Semaphore dispAlTaglio= new Semaphore(0);
	
	public SalaSem(int dim) {
		super(dim);
	}
	

	@Override
	public void taglioCapelli() throws InterruptedException {
		
		barbiereSem.acquire();
		System.out.println("Taglio in corso..");
		Thread.sleep(2000);
		postiOccupati--;
		barbiereSem.release();
	}

	@Override
	public boolean attendiTaglio() throws InterruptedException {
		Thread.sleep(3000);
		return true;
	}
	

}

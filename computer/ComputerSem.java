package computer;

import java.util.concurrent.Semaphore;

public class ComputerSem extends Computer {
	private Semaphore [] sem= {new Semaphore(4,true), new Semaphore(4,true)};
	private Semaphore [] mutex= {new Semaphore(1), new Semaphore(1)};
	@Override
	protected void richiesta(int core, int processore) throws InterruptedException {
		sem[processore].acquire(core);
		mutex[processore].acquire();
		disponibili[processore]-=core;
		mutex[processore].release();
		System.out.println(Thread.currentThread().getName()+" acquisisce "+ core+ " dal processore "+processore);
		System.out.println("Core disponibili proc0 = "+ disponibili[0]);
		System.out.println("Core disponibili proc1 = "+ disponibili[1]);
		utilizza();
		sem[processore].release(core);
		mutex[processore].acquire();
		disponibili[processore]+=core;
		mutex[processore].release();
		
	}//richiesta

	@Override
	protected void utilizza() throws InterruptedException {
		Thread.sleep(2000);
		
	}

	@Override
	protected void pausa() throws InterruptedException {
		Thread.sleep(5000);
		
	}
	
}//ComputerSem

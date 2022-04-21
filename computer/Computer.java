package computer;

public abstract class Computer {
	protected final int [] core= {0,1};
	protected int[] disponibili= {4,4};
	
	abstract protected void richiesta(int core, int processore) throws InterruptedException;
	abstract protected void utilizza() throws InterruptedException;
	abstract protected void pausa() throws InterruptedException;
}//Computer

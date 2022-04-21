package muratori;

public abstract class Casa {
	protected final int NUM_PARETI=4, MUR_CEM=0, MUR_MAT=1;
	protected int lavoro=0;//0=cemento   ---     1=mattoni
	protected int numFile;
	
	
	
	public Casa(int n) {
		numFile=n*NUM_PARETI;
	}//Costruttore
	
	abstract boolean inizia(int t) throws InterruptedException;
	abstract void termina();
	
	
}

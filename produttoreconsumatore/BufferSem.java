package produttoreconsumatore;

import java.util.concurrent.Semaphore;

public class BufferSem extends Buffer {
	private Semaphore postiOccupati= new Semaphore(0);
	private Semaphore postiLiberi;
	private Semaphore mutex= new Semaphore(1);
	
	

	public BufferSem(int dim) {
		super(dim);
		postiLiberi= new Semaphore(dim);
	}



	@Override
	public int get() throws InterruptedException {
		postiOccupati.acquire();
		mutex.acquire();
		int ret=buffer[out];
		buffer[out]=0;
		out= (out+1)%buffer.length;
		mutex.release();
		postiLiberi.release();
		return ret;
		
	}
	


	@Override
	public void put(int dato) throws InterruptedException {
		postiLiberi.acquire();
		mutex.acquire();
		buffer[in]=dato;
		in= (in+1)%buffer.length;
		mutex.release();
		postiOccupati.release();
	}
	
}

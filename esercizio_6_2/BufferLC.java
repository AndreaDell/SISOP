package esercizio_6_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLC extends Buffer {

	protected int numElementi = 0;

	protected Lock l = new ReentrantLock();
	protected Condition bufferPieno = l.newCondition();
	protected Condition bufferVuoto = l.newCondition();

	public BufferLC(int limite) {
		super(limite);
	}

	public void put(Elemento el) throws InterruptedException {
		l.lock();
		try {
			while (numElementi == limite) {
				bufferPieno.await();
			}
	
			lista.add(el);
			in = (in + 1) % limite;
			numElementi++;
			System.out.println(this);
			bufferVuoto.signal();
		} finally {
			l.unlock();
		}

	}

	@Override
	public Elemento get() throws InterruptedException {
		Elemento el;
		l.lock();
		try {
			while (numElementi == 0) {
				bufferVuoto.await();
			}
			el = lista.;
			lista.remove(out);
			out = (out + 1) % limite;
			numElementi--;
			System.out.println(this);
			bufferPieno.signal();
		} finally {
			l.unlock();
		}
		return el;
	}
	
	public static void main(String[] args) {
		try{
			int dimensione = 10;
			Buffer buffer = new BufferLC(dimensione);
			int numProduttori = 10;
			int numConsumatori = 10;
			buffer.test(numProduttori, numConsumatori);
		} catch (Exception e) {
			System.err.println("Errore!");
			System.exit(-1);
		}
	}

}

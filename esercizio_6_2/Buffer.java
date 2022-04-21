package esercizio_6_2;

import java.util.LinkedList;
import java.util.List;

public abstract class Buffer {
	protected List<Elemento> lista;
	protected int in = 0;
	protected int out = 0;
	protected int limite;
	
	public Buffer(int limite) {
		lista= new LinkedList<Elemento>();
		this.limite=limite;
	}

	public abstract void put(Elemento i) throws InterruptedException;

	public abstract Elemento get() throws InterruptedException;
	
	public String toString() {
		String ret = "[";
		for (Elemento elemento : lista) {
			ret += (elemento == null)? "*":elemento.getI();
		}
		ret+="]";
		return ret;
	}

	public void test(int numProduttori, int numConsumatori) {
		for (int i = 0; i < numProduttori; i++) {
			new Thread(new Produttore(this)).start();
		}
		for (int i = 0; i < numConsumatori; i++) {
			new Thread(new Consumatore(this)).start();
		}
	}
}

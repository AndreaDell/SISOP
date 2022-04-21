package produttoreconsumatore;

import java.util.Arrays;

public abstract class Buffer {
	protected int [] buffer;
	protected int in, out;
	
	public Buffer(int dim) {
		buffer= new int [dim];
		in=0;
		out=0;
	}
	
	abstract public int get() throws InterruptedException;
	abstract public void put(int dato)throws InterruptedException;
	
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder(30);
		for (int i=0;i<buffer.length;i++) {
			st.append(buffer[i]+"-");
		}
		return st.toString();
	}

}

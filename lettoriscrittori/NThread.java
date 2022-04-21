package lettoriscrittori;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NThread {

	private static Lock l= new ReentrantLock();
	private static Condition c= l.newCondition();
	private static LinkedList<Long> lista= new LinkedList<>();
	private int max;
	
	public NThread(int max) {
		this.max=max;
		for (int i=0;i<max;i++)
			new TH(i).start();
	}
	private  class TH extends Thread{
		private int id;
		
		public TH(int i) {
			id=i;
		}
		
		public void run() {
			l.lock();
			try {
			lista.add(id);
			Collections.sort(lista);
//			Collections.sort(lista, new Comparator<Thread>() {
//
//				@Override
//				public int compare(Thread o1, Thread o2) {
//					if (o1.getId()>o2.getId())
//						return 1;
//					else if (o1.getId()<o2.getId())
//						return -1;
//					else 
//						return 0;
//				}
//				
//			});
			while (!cond()) {
				c.await();
			}
			
			lista.remove();
			System.out.println(lista);
			System.out.println("Id: "+lista.getFirst() );
			c.signal();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				l.unlock();
			}
		}//run
		private boolean cond() {
			return id<max && id!=lista.getFirst();
		}
		
	}//TH



	public static void main(String[] args) {
	new NThread(10);
}
}

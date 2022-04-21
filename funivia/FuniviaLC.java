package funivia;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FuniviaLC extends Funivia {
	Lock l= new ReentrantLock();
	Condition attesaValle= l.newCondition();
	Condition attesaCima= l.newCondition();
	Condition attesaPilota= l.newCondition();
	private boolean arrivato=false;
	private LinkedList<Thread> cabina= new LinkedList<>();
	
	@Override
	void pilotaStart() {
		l.lock();
		try {
		System.out.println("Funivia a valle.");
		while(postiDisponibili!=0) {
			attesaValle.signalAll();
			attesaPilota.await(1000,TimeUnit.MILLISECONDS);
		}
		attesaValle.signalAll();
		while (postiDisponibili>0) {
			attesaPilota.await();
		}
		System.out.println("Il pilota parte dalla valle");
		Thread.sleep(5000);
		}catch(Exception e ) {
			
		} finally {
			l.unlock();
		}//finlly
		
	}//pilotaStart

	@Override
	void pilotaEnd() {
		l.lock();
		try {
		System.out.println("Funivia in CIMA");
		arrivato=true;
		attesaCima.signalAll();
		if (tipoCorrente==T_PIEDI)
			System.out.println("I turisti a PIEDI scendono dalla cabina");
		else if(tipoCorrente==T_BICI)
			System.out.println("I turisti in BICI scendono dalla cabina");
		for (int i=0;i<cabina.size();i++)
			System.out.print(cabina.get(i).getName()+"  ");
		while (postiDisponibili!=6) {
			System.out.println(postiDisponibili);
			attesaPilota.await();
		}
		tipoCorrente=1-tipoCorrente;
		System.out.println("Il pilota ritorna a valle..");
		Thread.sleep(5000);
		}catch(Exception e) {
			
		} finally {
			l.unlock();
		}
		
	}//pilotaEnd

	@Override
	void turistaSali(int t) {
		l.lock();
		try {
			while (!possoSalire(t)) {
				attesaValle.await();
			}
		System.out.println("Il turista "+Thread.currentThread().getName()+" entra nella cabina.");
		cabina.add(Thread.currentThread());
		postiDisponibili -= (1+t);
		System.out.println(postiDisponibili);
		//if (postiDisponibili==0) {
			attesaPilota.signal();
		//}
		
		} catch (Exception e) {
			
		} finally {
			l.unlock();
		}//finally
		
	}//turistaSali

	private boolean possoSalire(int t) {
		return postiDisponibili>0 && tipoCorrente==t;
	}
	@Override
	void turistaScendi(int t) {
		l.lock();
		try {
			while(arrivato==false) {
				attesaCima.await();
			}//while
			cabina.remove();
			postiDisponibili+=(1+t);
			if (postiDisponibili==6) {
				arrivato=false;
				attesaPilota.signal();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			l.unlock();
		}
		
	}//turistaScendi

}

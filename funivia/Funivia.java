package funivia;

public abstract class Funivia {
	protected final int T_PIEDI=0 , T_BICI=1;
	protected int tipoCorrente=0;
	protected int postiDisponibili=6;
	abstract void pilotaStart();
	abstract void pilotaEnd();
	abstract void turistaSali(int t);
	abstract void turistaScendi(int t);

}

package snowboard;

public abstract class Gara {

	abstract void partenza( Snowboarder s);
//	sospende lo snowboarder s fin quando non � il suo turno (or-dine FIFO di arrivo) e la pista non � libera.
	
	abstract int arrivo( Snowboarder s);
	// permette allo snowboarder s di tagliare il traguardo;
}

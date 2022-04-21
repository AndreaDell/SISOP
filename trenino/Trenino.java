package trenino;

import java.util.LinkedList;

public abstract class Trenino {
	protected LinkedList<Thread> [] array;
	protected int corrente;
	int turno=0;
	boolean movimento=false;
	LinkedList<Thread> coda= new LinkedList<>();
	
	abstract void turSali();
//	il turista vuole salire sul trenino. Si tratta di un metodo che blocca il turista 
//	sul punto di accesso fin quando non sale su una delle cabine del trenino.
	abstract void turScendi();
//	il turista scende dal trenino. Si tratta di un metodo che blocca il turista fin quando la cabina non ritorna al punto di accesso.
	abstract void impFaiScendere();
//	se nella cabina che è appena arrivata al punto di accesso sono presenti dei turisti l’impiegato fa scendere i turisti.
	abstract void impFaiSalire();
//	l’impiegato fa salire un gruppo di 10 turisti nella cabina che è arrivata al punto di accesso.
	abstract void impMuovi();
//	l’impiegato da il comando per far fare uno scatto alle cabine del trenino
	
	
	
}

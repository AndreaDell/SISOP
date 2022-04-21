package produttoreconsumatore;

public class Test {
	
	
		
	public static void main(String[] args) {
		Buffer b= new BufferSem(5);
	
		for (int i=0;i<3;i++) {
			new Produttore(b).start();
	}
	}

}


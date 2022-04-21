package esercizio_2_6;

public class CryptoException extends Exception {
	 public CryptoException() {
	    }
	 
	    public CryptoException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
}

package Atm;

// Exception class built to print Errors and exceptions without stopping code
public class AtmException extends Exception{
    public AtmException(String message) {
        super(message);
    }
}

package exceptions;

public class UndefinedLogTypeException extends Exception {

    public UndefinedLogTypeException() {
        super();
    }

    public UndefinedLogTypeException(String message) {
        super(message);
    }
}

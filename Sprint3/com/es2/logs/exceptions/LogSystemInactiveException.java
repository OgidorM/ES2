package exceptions;

public class LogSystemInactiveException extends Exception {

    public LogSystemInactiveException() {
        super();
    }

    public LogSystemInactiveException(String message) {
        super(message);
    }
}

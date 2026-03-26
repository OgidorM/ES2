package exceptions;

public class PoolExhaustedException extends Exception {

    public PoolExhaustedException() {
        super("O pool de objetos está esgotado. Não há objetos disponíveis.");
    }

    public PoolExhaustedException(String message) {
        super(message);
    }
}

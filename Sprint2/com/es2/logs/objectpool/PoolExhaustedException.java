package objectpool;

public class PoolExhaustedException extends Exception {
    public PoolExhaustedException() {
        super("No more objects available in the pool.");
    }
}

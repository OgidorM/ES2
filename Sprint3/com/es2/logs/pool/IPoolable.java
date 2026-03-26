package pool;

public interface IPoolable {
    void reset();
    boolean isInUse();
    void markInUse(boolean v);
}

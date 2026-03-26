package pool;

import java.util.HashMap;
import java.util.Map;

public class PoolManager {

    private static volatile PoolManager instance;

    private final Map<Class<?>, ObjectPool<?>> pools;

    private PoolManager() {
        this.pools = new HashMap<>();
        setupDefaultPools();
    }

    public static PoolManager getInstance() {
        if (instance == null) {
            synchronized (PoolManager.class) {
                if (instance == null) {
                    instance = new PoolManager();
                }
            }
        }
        return instance;
    }

    private void setupDefaultPools() {
        pools.put(LogFormatter.class, new ObjectPool<>(LogFormatter::new, 5));
        pools.put(DestinationConnection.class, new ObjectPool<>(DestinationConnection::new, 5));
    }

    @SuppressWarnings("unchecked")
    public <T extends IPoolable> ObjectPool<T> getPool(Class<T> type) {
        return (ObjectPool<T>) pools.get(type);
    }
}

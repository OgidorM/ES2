package pool;

import exceptions.PoolExhaustedException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;

public class ObjectPool<T extends IPoolable> {

    private final LinkedBlockingDeque<T> available;
    private final List<T> inUse;
    private final int maxSize;
    private final Supplier<T> factory;
    private final Semaphore slots;

    public ObjectPool(Supplier<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.available = new LinkedBlockingDeque<>(maxSize);
        this.inUse = new CopyOnWriteArrayList<>();
        this.slots = new Semaphore(maxSize);
    }

    public T acquire() throws PoolExhaustedException {
        // Tenta reservar um slot sem bloquear
        if (!slots.tryAcquire()) {
            throw new PoolExhaustedException(
                "Pool esgotado: limite de " + maxSize + " objetos atingido."
            );
        }

        // Reutiliza um objeto disponível ou cria um novo
        T obj = available.pollFirst();
        if (obj == null) {
            obj = createNew();
        }
        obj.markInUse(true);
        inUse.add(obj);
        return obj;
    }

    public void release(T obj) {
        if (obj == null) return;
        inUse.remove(obj);
        obj.reset();
        available.addLast(obj);
        slots.release();
    }

    public int getAvailableCount() {
        return available.size();
    }

    public int getInUseCount() {
        return inUse.size();
    }

    private T createNew() {
        return factory.get();
    }
}

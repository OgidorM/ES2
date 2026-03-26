package com.es2.objectpool;

import core.LogRecord;
import factory.LogFactory;
import exceptions.UndefinedLogTypeException;

import java.util.ArrayList;
import java.util.List;

public class ReusablePool {

    private static ReusablePool instance;
    private int maxPoolSize = 10;
    
    // Lista de LogRecords livres
    private final List<LogRecord> free = new ArrayList<>();
    // Lista de LogRecords ocupados
    private final List<LogRecord> used = new ArrayList<>();

    private ReusablePool() {
    }

    public static synchronized ReusablePool getInstance() {
        if (instance == null) {
            instance = new ReusablePool();
        }
        return instance;
    }

    public synchronized LogRecord acquire(String type) throws PoolExhaustedException, UndefinedLogTypeException {
        // Tenta encontrar um registo igual já disponível
        for (LogRecord record : free) {
            if (record.getName() != null && record.getName().equalsIgnoreCase(type)) {
                free.remove(record);
                used.add(record);
                return record;
            }
        }

        // Caso esgote o limite
        if (free.size() + used.size() >= maxPoolSize) {
            throw new PoolExhaustedException();
        }

        // Criar um novo pela fábrica porque ainda não existia e temos limite
        LogRecord newRecord = LogFactory.makeLogRecord(type);
        used.add(newRecord);
        return newRecord;
    }

    public synchronized void release(LogRecord record) throws ObjectNotFoundException {
        if (used.remove(record)) {
            free.add(record);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    public synchronized void resetPool() {
        free.clear();
        used.clear();
    }

    public synchronized void setMaxPoolSize(int size) {
        this.maxPoolSize = size;
    }
}

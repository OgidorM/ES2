package com.es2.objectpool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ReusablePool {

    private static final int DEFAULT_MAX_POOL_SIZE = 10;
    private static final String IPV_URL = "https://www.ipv.pt/";

    private static ReusablePool instance;

    private final Deque<HttpURLConnection> free = new ArrayDeque<>();
    private final Set<HttpURLConnection> inUse = new HashSet<>();

    private int total = 0;
    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;

    private ReusablePool() {
        // Singleton
    }

    public static synchronized ReusablePool getInstance() {
        if (instance == null) {
            instance = new ReusablePool();
        }
        return instance;
    }

    public synchronized HttpURLConnection acquire() throws IOException, PoolExhaustedException {
        // Se tem uma conexao livre reutiliza
        if (!free.isEmpty()) {
            HttpURLConnection conn = free.removeFirst();
            inUse.add(conn);
            return conn;
        }

        // Se ainda nao atingiu o maximo, cria uma nova
        if (total < maxPoolSize) {
            HttpURLConnection conn = createConnection();
            total++;
            inUse.add(conn);
            return conn;
        }

        // Pool cheia
        throw new PoolExhaustedException();
    }

    public synchronized void release(HttpURLConnection conn) throws ObjectNotFoundException {
        if (conn == null) {
            throw new ObjectNotFoundException();
        }

        // aceita devolver se foi adquirido daqui e ta em uso
        if (!inUse.remove(conn)) {
            throw new ObjectNotFoundException();
        }

        free.addLast(conn);
    }

    public synchronized void resetPool() {
        // Desconecta tudo e reseta estado
        for (HttpURLConnection c : free) {
            safeDisconnect(c);
        }
        for (HttpURLConnection c : inUse) {
            safeDisconnect(c);
        }

        free.clear();
        inUse.clear();
        total = 0;
        maxPoolSize = DEFAULT_MAX_POOL_SIZE;
    }

    public synchronized void setMaxPoolSize(int size) {
        if (size < 1) size = 1;

        maxPoolSize = size;

        // Se diminuiu o max , joga fora excedentes que estiverem livres
        while (total > maxPoolSize && !free.isEmpty()) {
            HttpURLConnection c = free.removeFirst();
            safeDisconnect(c);
            total--;
        }
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL(IPV_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setInstanceFollowRedirects(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        return conn;
    }

    private void safeDisconnect(HttpURLConnection conn) {
        try {
            if (conn != null) conn.disconnect();
        } catch (Exception ignored) {
        }
    }
}
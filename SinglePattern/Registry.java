package com.es2.singleton;

public class Registry {

    private static Registry instancia;

    private String path;
    private String connectionString;

    private Registry() {
    }
    public static synchronized Registry getInstance() {
        if (instancia == null) {
            instancia = new Registry();
        }
        return instancia;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
package com.es2.objectpool;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("Object not found in the pool.");
    }
}

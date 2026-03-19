package com.es2.bridge;

// Quando serviço não existe
public class ServiceNotFoundException extends Exception {

    public ServiceNotFoundException() {
        super("Service not found");
    }
}


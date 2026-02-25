package com.es2.factorymethod;

public class Computer implements Product {
    private String brand;

    protected Computer() {
    } // Construtor protegido para evitar 'new' direto [cite: 26, 64]

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
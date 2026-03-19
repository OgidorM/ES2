package com.es2.factorymethod;

public abstract class FactoryProduct {

    public FactoryProduct() {

    }

    public static Product makeProduct(String type) throws UndefinedProductException {
        if (type == null) {
            throw new UndefinedProductException("Type cannot be null");
        }

        String t = type.trim();

        if ("Computer".equals(t) || "computer".equalsIgnoreCase(t)) {
            return new Computer();
        }

        if ("Software".equals(t) || "software".equalsIgnoreCase(t)) {
            return new Software();
        }

        throw new UndefinedProductException("Undefined product type: " + type);
    }
}
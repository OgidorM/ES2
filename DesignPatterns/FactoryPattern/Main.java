package com.es2.factorymethod;

public class Main {

    public static void main(String[] args) {

        try {
            // Criando Computer
            Product computer = FactoryProduct.makeProduct("Computer");
            computer.setBrand("Dell");
            System.out.println("Computer brand: " + computer.getBrand());

            // Criando Software
            Product software = FactoryProduct.makeProduct("Software");
            software.setBrand("Adobe");
            System.out.println("Software brand: " + software.getBrand());

            // Teste de erro
            Product unknown = FactoryProduct.makeProduct("Phone");

        } catch (UndefinedProductException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
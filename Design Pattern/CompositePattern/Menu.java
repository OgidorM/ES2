package com.es2.composite;

// COMPONENT - abstração comum para Leaf (Link) e Composite (SubMenu)
// Permite tratar ambos uniformemente através de polimorfismo
// O cliente trabalha com Menu sem saber o tipo concreto
public abstract class Menu {

    // Atributo comum a todos os elementos
    private String label;

    public Menu() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // Operação comum - implementada de forma diferente por cada subclasse
    public abstract void showOptions();
}

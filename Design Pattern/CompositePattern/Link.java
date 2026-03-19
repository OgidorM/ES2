package com.es2.composite;

// LEAF (Folha) - elemento primitivo, não pode conter filhos
// Não tem addChild/removeChild (safe design)
public class Link extends Menu {

    // Atributo específico da Leaf
    private String URL;

    public Link() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    // Caso base da recursão - imprime label e URL
    @Override
    public void showOptions() {
        System.out.println(getLabel());
        System.out.println(URL);
    }
}

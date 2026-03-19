package com.es2.composite;

import java.util.ArrayList;
import java.util.List;

// Composite - pode conter outros elementos Menu (Links ou SubMenus)
// Implementa gestão de filhos: addChild(), removeChild()
public class SubMenu extends Menu {

    // Coleção de filhos do tipo Menu (abstração) - permite polimorfismo
    private List<Menu> children;

    public SubMenu() {
        this.children = new ArrayList<>();
    }

    // Adiciona filho (Link ou SubMenu) - tratamento uniforme
    public void addChild(Menu child) {
        children.add(child);
    }


    public void removeChild(Menu child) {
        children.remove(child);
    }

    // Caso recursivo: imprime label e delega para filhos
    // Não usa instanceof - cada filho "sabe" como se mostrar (polimorfismo)
    // Recursão para quando chega aos Links (folhas sem filhos)
    @Override
    public void showOptions() {
        System.out.println(getLabel());

        for (Menu child : children) {
            child.showOptions();
        }
    }
}

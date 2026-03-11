package com.es2.memento;

import java.util.ArrayList;
// Memento

public class Memento {

    private final ArrayList<String> studentNames;

    public Memento(ArrayList<String> studentNames) {
        // Alterações futuras na lista original não afetam o estado guardado neste Memento
        this.studentNames = new ArrayList<>(studentNames);
    }

    public ArrayList<String> getState() {
        // Devolvemos uma nova cópia para proteger o estado do Memento.
        // Mesmo que o Originator altere a lista devolvida, o estado original fica intacto.
        return new ArrayList<>(studentNames);
    }
}


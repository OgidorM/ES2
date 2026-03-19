package com.es2.memento;

import java.util.ArrayList;
// Originator
 // 1) Um método para criar uma snapshot — cria um Memento com o estado atual.
 // 2) Um método para restaurar um snapshot — repõe o estado a partir de um Memento.


public class Server {

    private ArrayList<String> studentNames;


    public Server() {
        this.studentNames = new ArrayList<>();
    }

    public void addStudent(String studentName) throws ExistingStudentException {
        if (studentNames.contains(studentName)) {
            throw new ExistingStudentException();
        }
        studentNames.add(studentName);
    }

    public Memento backup() {
        return new Memento(studentNames);
    }


     // Como se restaura o estado de forma segura?
     // O Originator substitui o seu estado interno pelo estado guardado no Memento.
     // Usamos getState() que devolve uma cópia, assim o Memento original
     //permanece intacto mesmo após a restauração.


    public void restore(Memento state) {
        this.studentNames = state.getState();
    }


    public ArrayList<String> getStudentNames() {
        return studentNames;
    }
}


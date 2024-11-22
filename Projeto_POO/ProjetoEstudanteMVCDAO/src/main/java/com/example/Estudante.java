// Essa é classe conceitual de Estudantes
package com.example;

// Imports do JavaFX
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Estudante {
    private StringProperty nome;
    private StringProperty RGA;
    private StringProperty curso;

    public Estudante(String nome, String RGA, String curso) {
        this.nome = new SimpleStringProperty(nome);
        this.RGA = new SimpleStringProperty(RGA);
        this.curso = new SimpleStringProperty(curso);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getRGA() {
        return RGA.get();
    }

    public void setRGA(String RGA) {
        this.RGA.set(RGA);
    }

    public StringProperty RGAProperty() {
        return RGA;
    }

    public String getCurso() {
        return curso.get();
    }

    public void setCurso(String curso) {
        this.curso.set(curso);
    }

    public StringProperty cursoProperty() {
        return curso;
    }
}
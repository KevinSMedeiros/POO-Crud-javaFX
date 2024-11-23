// Essa é classe conceitual de Carros
package com.example;

// Imports do JavaFX
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Carro {
    private StringProperty NomeDono;
    private StringProperty Placa;
    private StringProperty Modelo;

    public Carro(String NomeDono, String Placa, String Modelo) {
        this.NomeDono = new SimpleStringProperty(NomeDono);
        this.Placa = new SimpleStringProperty(Placa);
        this.Modelo = new SimpleStringProperty(Modelo);
    }

    public String getNomeDono() {
        return NomeDono.get();
    }

    public void setNomeDono(String NomeDono) {
        this.NomeDono.set(NomeDono);
    }

    public StringProperty NomeDonoProperty() {
        return NomeDono;
    }

    public String getPlaca() {
        return Placa.get();
    }

    public void setPlaca(String Placa) {
        this.Placa.set(Placa);
    }

    public StringProperty PlacaProperty() {
        return Placa;
    }

    public String getModelo() {
        return Modelo.get();
    }

    public void setModelo(String Modelo) {
        this.Modelo.set(Modelo);
    }

    public StringProperty ModeloProperty() {
        return Modelo;
    }
}
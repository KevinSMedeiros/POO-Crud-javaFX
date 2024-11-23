package com.example;

import com.example.view.CarroCRUDView;

import java.io.IOException;

// Imports JavaFX
import javafx.application.Application;
import javafx.stage.Stage;


import com.example.controller.CarroController;


public class Main extends Application { // A classe principal deve estender a classe Application

    @Override
    public void start(Stage stage) throws IOException {

            CarroController CarroController = new CarroController(); // Instância do controlador
            CarroCRUDView ec = new CarroCRUDView(CarroController);
             
            // Propriedades do palco onde a aplicação acontece
            stage.setTitle("Projeto CRUD Carro com JavaFX"); // Título do palco
            stage.setScene(ec.getScene()); // Tela/cena a ser carregada ao iniciar o palco
            stage.show(); // Inicia o palco
            
    }

    public static void main(String[] args) {
       
        launch(); // carrega a aplicação
    }

}
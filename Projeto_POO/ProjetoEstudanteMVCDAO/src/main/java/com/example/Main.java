package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.example.view.EstudanteCRUDView;

import java.io.IOException;

// Imports JavaFX
import javafx.application.Application;
import javafx.stage.Stage;


import com.example.controller.EstudanteController;


public class Main extends Application { // A classe principal deve estender a classe Application

    @Override
    public void start(Stage stage) throws IOException {

       

         System.out.println("Hello, Meu projeto MVC + DAO via JDBC!");

        // organizar seus dados de conexão em strings é uma boa ideia!
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o nome do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";


            EstudanteController estudanteController = new EstudanteController(); // inicialização da controladora com a conexão do banco
            EstudanteCRUDView ec = new EstudanteCRUDView(estudanteController);
             
            // Propriedades do palco onde a aplicação acontece
            stage.setTitle("Projeto CRUD Estudante com JavaFX"); // Título do palco
            stage.setScene(ec.getScene()); // Tela/cena a ser carregada ao iniciar o palco
            stage.show(); // Inicia o palco
            
    }

    public static void main(String[] args) {
       
        
        launch(); // carrega a aplicação
    }

}
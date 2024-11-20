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
        String mySQLURL = "jdbc:mysql://localhost:3306"; // informar o nome do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }

            EstudanteController estudanteController = new EstudanteController(conexao, stage); // inicialização da controladora com a conexão do banco

            conexao.close(); // fecha a conexão com o banco - sempre fechar após o uso!

        } catch (SQLException ex) {
            System.out.println("Houve um problema com a conexão.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
       
        
        launch(); // carrega a aplicação
    }

}
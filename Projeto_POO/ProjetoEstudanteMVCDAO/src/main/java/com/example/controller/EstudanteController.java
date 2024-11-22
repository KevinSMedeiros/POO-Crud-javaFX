package com.example.controller;
// Esta é a classe de Controle de Estudante que deve gerenciar a comunicação entre Model e View de Estudante

import com.example.Estudante;
import com.example.view.EstudanteCRUDView;
import com.example.model.*; // import do Modelo

import javafx.stage.Stage;

//imports Java
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class EstudanteController {

    EstudanteDAOImpl estudanteModel;
    
    public EstudanteController() {

        this.estudanteModel = new EstudanteDAOImpl(); // inicia o Model de Estudante
    }

    
     public ArrayList<Estudante> mostrarEstudantes() {
        ArrayList<Estudante> listaEstudantes = this.estudanteModel.buscarTodos(); // busca no modelo
        return listaEstudantes;
    }

    public void inserirEstudante(Estudante estudante) {
       // organizar seus dados de conexão em strings é uma boa ideia!
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o nome do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
        this.estudanteModel.inserir(estudante, conexao); // grava no modelo
        conexao.close(); // fecha a conexão com o banco - sempre fechar após o uso!
        } catch (Exception e) {
            System.out.println("Houve um problema com a conexão.");
            e.printStackTrace();
        }
    }

    public String excluirEstudantePorRGA(String RGA) {
        
        
        if (this.estudanteModel.buscarPorRGA(RGA) != null) { // verifica no modelo
            this.estudanteModel.excluir(RGA); // grava no modelo
            return "Estudante excluído com sucesso!";
        }
        return "Estudante não encontrado!";
    }

    public String atualizarEstudante(Estudante estudante) {
        
        
        if (this.estudanteModel.buscarPorRGA(estudante.getRGA()) != null) { // verifica no modelo
        
            this.estudanteModel.atualizar(this.estudanteModel.buscarPorRGA(estudante.getRGA()), estudante.getNome());
            return "Estudante atualizado com sucesso!";
        }
        return "Estudante não encontrado!";
    }

    public Estudante pesquisarEstudantePorRGA(String RGA) {
        
        Estudante estudante = this.estudanteModel.buscarPorRGA(RGA); // busca no modelo
        if ( estudante != null) { // verifica no modelo
            return estudante;  
        }
        return null;
    }
}
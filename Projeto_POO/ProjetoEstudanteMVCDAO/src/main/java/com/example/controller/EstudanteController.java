package com.example.controller;
// Esta é a classe de Controle de Estudante que deve gerenciar a comunicação entre Model e View de Estudante

import com.example.Estudante;
import com.example.view.EstudanteCRUDView;
import com.example.model.*; // import do Modelo

import javafx.stage.Stage;

//imports Java
import java.sql.Connection;
import java.util.ArrayList;

public class EstudanteController {

    EstudanteDAOImpl estudanteModel;
    EstudanteCRUDView estudanteView;

    public EstudanteController(Connection conexao, Stage stage) {

         // Cria instância da tela/cena da interface de CRUD de Estudante
        EstudanteCRUDView ec = new EstudanteCRUDView();
        
        // Propriedades do palco onde a aplicação acontece
        stage.setTitle("Projeto CRUD Estudante com JavaFX"); // Título do palco
        stage.setScene(ec.getScene()); // Tela/cena a ser carregada ao iniciar o palco
        stage.show(); // Inicia o palco

        this.estudanteModel = new EstudanteDAOImpl(conexao); // inicia o Model de Estudante
        this.iniciarControle();
    }

    public void iniciarControle() { // controle de funcionalidades CRUD Estudante
        
    }
    
     public void mostrarEstudantes() {
        ArrayList<Estudante> listaEstudantes = this.estudanteModel.buscarTodos(); // busca no modelo
        //this.estudanteView.mostrarEstudantes(listaEstudantes); // mostra na visão
    }

    public void inserirEstudante() {
        //this.estudanteView.inserirEstudante(); // mostra na visão
        //Estudante estudante = new Estudante(this.estudanteView.getNomeEstudante(), this.estudanteView.getRGAEstudante()); // recebe na visão
        //this.estudanteModel.inserir(estudante); // grava no modelo
        //this.estudanteView.confirmarInsercaoEstudante(estudante.getRGA()); // mostra na visão
    }

    public void excluirEstudantePorRGA() {
        //this.estudanteView.excluirEstudante(); // mostra na visão
        //String RGA = this.estudanteView.getRGAEstudante(); // recebe na visão
        //if (this.estudanteModel.buscarPorRGA(RGA) != null) { // verifica no modelo
        //    this.estudanteModel.excluir(RGA); // grava no modelo
        //    this.estudanteView.confirmarExclusaoEstudante(RGA); // mostra na visão
        //}
    }

    public void atualizarEstudante() {
        //this.estudanteView.atualizarEstudante(); // mostra na visão
        //String RGA = this.estudanteView.getRGAEstudante(); // recebe na visão
        //if (this.estudanteModel.buscarPorRGA(RGA) != null) { // verifica no modelo
        //    String nome = this.estudanteView.getNomeEstudante(); // recebe na visão
        //    this.estudanteModel.atualizar(this.estudanteModel.buscarPorRGA(RGA), nome); // grava no modelo
        //    this.estudanteView.confirmarAtualizacaoEstudante(RGA); // mostra na visão
        //}
    }

    public void pesquisarEstudantePorRGA() {
        //this.estudanteView.pesquisarEstudante(); // mostra na visão
        //String RGA = this.estudanteView.getRGAEstudante(); // recebe na visão
        //if (this.estudanteModel.buscarPorRGA(RGA) != null) { // verifica no modelo
        //    this.estudanteView.mostrarEstudante(this.estudanteModel.buscarPorRGA(RGA));  // busca no modelo e mostra na visão
        //}
    }
}
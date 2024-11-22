package com.example.model;
// Esta é a interface DAO da classe conceitual de Estudante

import java.sql.Connection;
import java.util.ArrayList;

import com.example.Estudante;

public interface EstudanteDAO {
    void inserir(Estudante estudante, Connection conexao);

    void atualizar(Estudante estudante, String nome);

    void excluir(String RGA);

    Estudante buscarPorRGA(String RGA);

    ArrayList<Estudante> buscarTodos();
}
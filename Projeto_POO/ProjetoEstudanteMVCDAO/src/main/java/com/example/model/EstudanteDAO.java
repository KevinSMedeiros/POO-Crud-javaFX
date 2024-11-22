package com.example.model;
// Esta é a interface DAO da classe conceitual de Estudante

import java.sql.Connection;
import java.util.ArrayList;

import com.example.Estudante;

public interface EstudanteDAO {
    void inserir(Estudante estudante, Connection conexao);

    void atualizar(Estudante estudante, String nome, Connection conexao);

    void excluir(String RGA, Connection conexao);

    Estudante buscarPorRGA(String RGA, Connection conexao);

    ArrayList<Estudante> buscarTodos(Connection conexao);
}
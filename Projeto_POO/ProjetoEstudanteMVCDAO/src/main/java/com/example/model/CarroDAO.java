package com.example.model;
// Esta é a interface DAO da classe conceitual de Carro

import java.sql.Connection;
import java.util.ArrayList;

import com.example.Carro;

public interface CarroDAO {
    void inserir(Carro Carro, Connection conexao);

    void atualizar(Carro Carro, String NomeDono, Connection conexao);

    void excluir(String Placa, Connection conexao);

    Carro buscarPorPlaca(String Placa, Connection conexao);

    ArrayList<Carro> buscarTodos(Connection conexao);
}
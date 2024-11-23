package com.example.model;
// Esta é a interface DAO da classe conceitual de Carro

import java.sql.Connection;
import java.util.ArrayList;

import com.example.Carro;

public interface CarroDAO {
    Carro inserir(Carro Carro, Connection conexao);

    Carro atualizar(Carro Carro, String NomeDono, Connection conexao);

    String excluir(String Placa, Connection conexao);

    Carro buscarPorPlaca(String Placa, Connection conexao);

    ArrayList<Carro> buscarTodos(Connection conexao);
}
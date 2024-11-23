package com.example.controller;
// Esta é a classe de Controle de Carro que deve gerenciar a comunicação entre Model e View de Carro

import com.example.Carro;
import com.example.view.CarroCRUDView;
import com.example.model.*; // import do Modelo

import javafx.stage.Stage;

//imports Java
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class CarroController {

    CarroDAOImpl CarroModel;

    public CarroController() {

        this.CarroModel = new CarroDAOImpl(); // inicia o Model de Carro
    }

    public ArrayList<Carro> mostrarCarros() {
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o NomeDono do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
            ArrayList<Carro> listaCarros = this.CarroModel.buscarTodos(conexao); // busca no modelo
            conexao.close(); // fecha a conexão com o banco - sempre fechar após o uso!
            return listaCarros;
        } catch (Exception e) {
            System.out.println("Houve um problema com a conexão.");
            e.printStackTrace();
            return null;
        }

    }

    public void inserirCarro(Carro Carro) {
        // oPlacanizar seus dados de conexão em strings é uma boa ideia!
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o NomeDono do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
            this.CarroModel.inserir(Carro, conexao); // grava no modelo
            conexao.close(); // fecha a conexão com o banco - sempre fechar após o uso!
        } catch (Exception e) {
            System.out.println("Houve um problema com a conexão.");
            e.printStackTrace();
        }
    }

    public String excluirCarroPorPlaca(String Placa) {

        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o NomeDono do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
            if (this.CarroModel.buscarPorPlaca(Placa, conexao) != null) { // verifica no modelo
                this.CarroModel.excluir(Placa, conexao); // grava no modelo
                conexao.close();
                return "Carro excluído com sucesso!";
            }
            conexao.close();
            return "Carro não encontrado!";
            // fecha a conexão com o banco - sempre fechar após o uso!
        } catch (Exception e) {
            e.printStackTrace();
            return "Houve um problema com a conexão.";
        }

    }

    public String atualizarCarro(Carro Carro) {
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o NomeDono do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
            if (this.CarroModel.buscarPorPlaca(Carro.getPlaca(), conexao) != null) { // verifica no modelo

                this.CarroModel.atualizar(this.CarroModel.buscarPorPlaca(Carro.getPlaca(), conexao),
                        Carro.getNomeDono(), conexao);
                conexao.close();
                return "Carro atualizado com sucesso!";
            }
            conexao.close();
            return "Carro não encontrado!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Houve um problema com a conexão.";
        }
    }

    public Carro pesquisarCarroPorPlaca(String Placa) {
        String mySQLURL = "jdbc:mysql://localhost:3306/bdalg3"; // informar o NomeDono do banco no final da URL é opcional
        String usuario = "root";
        String senha = "";

        // realiza a conexão com o banco
        try (Connection conexao = DriverManager.getConnection(mySQLURL, usuario, senha)) {

            if (conexao != null) {
                System.out.println("Conectado com sucesso à instância MySQL!");
            }
            Carro Carro = this.CarroModel.buscarPorPlaca(Placa, conexao); // busca no modelo
            if (Carro != null) { // verifica no modelo
                return Carro;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
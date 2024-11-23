package com.example.model;
// Esta é a classe que implementa a DAO de Carro para JDBC 

// imports Java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import com.example.Carro;

public class CarroDAOImpl implements CarroDAO {

    public Connection conexao;

    public CarroDAOImpl() {
         // conexão com Banco
    }

    // implementação dos métodos da interface CarroDAO para JDBC/SQL
    @Override
    public void inserir(Carro Carro, Connection conexao) {
        String inserir = "INSERT INTO Carros (CarroNomeDono, CarroPlaca, CarroModelo) VALUES (?, ?, ?)";;
        try {
            Carro existe = this.buscarPorPlaca(Carro.getPlaca(), conexao);
            if(existe != null){
                System.out.println("Carro com este Placa já existe!");
                return;
            }
            PreparedStatement statementInserir = conexao.prepareStatement(inserir);

            statementInserir.setString(1, Carro.getNomeDono());
            statementInserir.setString(2, Carro.getPlaca());
            statementInserir.setString(3, Carro.getModelo());
            statementInserir.executeUpdate();

            System.out.println(Carro.getNomeDono().toUpperCase()+" registro de Carro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Carro Carro, String NomeDono, Connection conexao) {
        String atualizar = "UPDATE Carros SET CarroNomeDono=?, CarroModelo=? WHERE CarroPlaca=?";
        try {
            PreparedStatement statementAtualizar = conexao.prepareStatement(atualizar);
            statementAtualizar.setString(1, NomeDono);
            statementAtualizar.setString(2, Carro.getModelo());
            statementAtualizar.setString(3, Carro.getPlaca());
            
            statementAtualizar.executeUpdate();
            System.out.println("Carro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(String Placa, Connection conexao) {
        Carro Carro = this.buscarPorPlaca(Placa, conexao);
        if (Carro != null) {
            System.out.println("Excluindo Carro...");
            String excluir = "DELETE FROM Carros WHERE CarroPlaca=" + Placa;
            try {
                PreparedStatement statementExcluir = conexao.prepareStatement(excluir);
                statementExcluir.executeUpdate();
                System.out.println("Carro excluído com sucesso! " + Carro.getPlaca() + ": " + Carro.getNomeDono().toUpperCase());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Carro buscarPorPlaca(String Placa, Connection conexao) {
        // Use o placeholder "?" para o parâmetro Placa
        String buscarPorPlaca = "SELECT * FROM Carros WHERE CarroPlaca = ?";
        Carro Carro = null;
        try {
            // Prepara a consulta com o placeholder
            PreparedStatement statementBuscarPorPlaca = conexao.prepareStatement(buscarPorPlaca);
            
            // Define o valor do parâmetro Placa
            statementBuscarPorPlaca.setString(1, Placa);
            
            // Executa a consulta
            ResultSet registros = statementBuscarPorPlaca.executeQuery();
            
            if (registros.next()) {
                Carro = new Carro(
                    registros.getString("CarroNomeDono"),
                    registros.getString("CarroPlaca"),
                    registros.getString("CarroModelo") // Obtém o Modelo
                );
                System.out.println("Registro de Carro encontrado com sucesso!");
            } else {
                System.out.println("Carro não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Carro;
    }

    @Override
    public ArrayList<Carro> buscarTodos(Connection conexao) {
        String buscarTodos = "SELECT * FROM Carros";
        ArrayList<Carro> listaCarros = new ArrayList<>();
        try {
            Statement statementBuscarTodos = conexao.createStatement();
            ResultSet registros = statementBuscarTodos.executeQuery(buscarTodos);
            while (registros.next()) {
                Carro Carro = new Carro(
                    registros.getString("CarroNomeDono"),
                    registros.getString("CarroPlaca"),
                    registros.getString("CarroModelo") // Adiciona o Modelo
                );
                listaCarros.add(Carro);
            }
            System.out.println("Lista de Carros retornada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCarros;
    }

}
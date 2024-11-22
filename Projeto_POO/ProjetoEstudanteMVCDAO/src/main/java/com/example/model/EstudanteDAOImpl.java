package com.example.model;
// Esta é a classe que implementa a DAO de Estudante para JDBC 

// imports Java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import com.example.Estudante;

public class EstudanteDAOImpl implements EstudanteDAO {

    public Connection conexao;

    public EstudanteDAOImpl() {
         // conexão com Banco
    }

    // implementação dos métodos da interface EstudanteDAO para JDBC/SQL
    @Override
    public void inserir(Estudante estudante, Connection conexao) {
        String inserir = "INSERT INTO estudantes (estudanteNome, estudanteRGA) values (?,?)";
        try {
            Estudante existe = this.buscarPorRGA(estudante.getRGA(), conexao);
            if(existe != null){
                System.out.println("Estudante com este RGA já existe!");
                return;
            }
            PreparedStatement statementInserir = conexao.prepareStatement(inserir);

            statementInserir.setString(1, estudante.getNome());
            statementInserir.setString(2, estudante.getRGA());
            statementInserir.executeUpdate();

            System.out.println(estudante.getNome().toUpperCase()+" registro de estudante inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Estudante estudante, String nome, Connection conexao) {
        String atualizar = "UPDATE estudantes SET estudanteNome=? WHERE estudanteRGA=?";
        try {
            PreparedStatement statementAtualizar = conexao.prepareStatement(atualizar);
            statementAtualizar.setString(1, nome);
            statementAtualizar.setString(2, estudante.getRGA());
            
            statementAtualizar.executeUpdate();
            System.out.println("Estudante atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(String RGA, Connection conexao) {
        Estudante estudante = this.buscarPorRGA(RGA, conexao);
        if (estudante != null) {
            System.out.println("Excluindo estudante...");
            String excluir = "DELETE FROM estudantes WHERE estudanteRGA=" + RGA;
            try {
                PreparedStatement statementExcluir = conexao.prepareStatement(excluir);
                statementExcluir.executeUpdate();
                System.out.println("Estudante excluído com sucesso! " + estudante.getRGA() + ": " + estudante.getNome().toUpperCase());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Estudante buscarPorRGA(String RGA, Connection conexao) {
        String buscarPorRGA = "SELECT * FROM estudantes WHERE estudanteRGA=" + RGA;
        Estudante estudante = null;
        try {
            Statement statementBuscarPorRGA = conexao.createStatement();
            ResultSet registros = statementBuscarPorRGA.executeQuery(buscarPorRGA);
            if (registros.next()) {
                estudante = new Estudante(registros.getString("estudanteNome"), registros.getString("estudanteRGA"));
                System.out.println("Registro de estudante encontrado com sucesso!");
            } else {
                System.out.println("Estudante não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudante;
    }

    @Override
    public ArrayList<Estudante> buscarTodos(Connection conexao) {
        String buscarTodos = "SELECT * FROM estudantes";
        ArrayList<Estudante> listaEstudantes = new ArrayList<Estudante>();
        try {
            Statement statementBuscarTodos = conexao.createStatement();
            ResultSet registros = statementBuscarTodos.executeQuery(buscarTodos);
            while (registros.next()) {
                Estudante estudante = new Estudante(registros.getString("estudanteNome"),
                        registros.getString("estudanteRGA"));
                listaEstudantes.add(estudante);
            }
            System.out.println("Lista de estudantes retornada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEstudantes;
    }

}
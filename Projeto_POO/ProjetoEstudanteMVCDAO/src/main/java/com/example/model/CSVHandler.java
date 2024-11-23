package com.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Carro;

public class CSVHandler {

    private String arquivoCSV = "C:\\Users\\kevin\\Documents\\POO\\POO-Crud-javaFX\\Projeto_POO\\ProjetoEstudanteMVCDAO\\src\\main\\java\\com\\example\\model\\carros.csv"; // Nome do arquivo CSV
    private List<Carro> listaDadosCarros = new ArrayList<>(); // Inicializa a lista de objetos Carro

    public List<Carro> carregarDadosCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(","); // Divide a linha em campos separados por vírgula

                if (dados.length >= 2) { // Garante que a linha tem pelo menos dois campos
                    String modelo = dados[0];
                    String marca = dados[1];
                    String ano = dados.length > 2 ? dados[2] : "Desconhecido"; // Verifica se o terceiro campo existe

                    this.listaDadosCarros.add(new Carro(modelo, marca, ano)); // Cria e adiciona um objeto Carro
                }
            }
        } catch (IOException e) { // Trata exceções de entrada/saída
            e.printStackTrace();
        }
        return listaDadosCarros; // Retorna a lista de objetos Carro
    }
  
  
  
    public void salvarDadosCSV(List<Carro> listaDadosCarros) { // Método para salvar os dados do arquivo CSV
  
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoCSV))) {
  
        for (Carro carro : listaDadosCarros) { // Percorre a lista de objetos Estudante
            System.out.println(carro.getPlaca() + "," + carro.getModelo() + "," + carro.getNomeDono());
            bw.write(carro.getPlaca() + "," + carro.getModelo() + "," + carro.getNomeDono()); // Escreve os dados do objeto Estudante no arquivo CSV

            bw.newLine(); // Adiciona uma nova linha no arquivo CSV
  
        }
  
         bw.close(); // Fecha o arquivo CSV
  
      } catch (IOException e) { // Trata exceções de entrada/saída se houver
  
          e.printStackTrace();
  
      }
  
    }
  
  }

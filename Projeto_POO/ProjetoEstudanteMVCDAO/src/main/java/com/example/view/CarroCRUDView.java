// Esta é a classe com a tela/cena da interface das operações do CRUD de Carro
package com.example.view;

// Imports JavaFX
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.example.Carro;
import com.example.controller.CarroController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CarroCRUDView {

    Label labelPlaca, labelNomeDono, labelModelo, labelTopo;
    TextField textFieldPlaca, textFieldNomeDono, textFieldModelo;
    Button botaoCadastrar, botaoAtualizar, botaoExcluir;
    VBox vbox;
    HBox barraBotoes;
    Scene scene;
    TableView<Carro> tabelaCarros;
    TableColumn<Carro, String> colunaPlaca, colunaNomeDono, colunaModelo;
    ObservableList<Carro> listaDadosCarros;
    Alert mensagemAlerta;
    TextField textFieldPesquisa;
    Button botaoPesquisar;

    private void atualizarTabela(CarroController carroController) { // Método para atualizar a tabela com os dados do arquivo CSV
        ObservableList<Carro> listaDadosEstudantes = carroController.getListaDadosEstudantes();

        tabelaCarros.setItems(listaDadosEstudantes);
    }
    // Método construtor que irá inicializar todos os elementos de interface da tela
    public CarroCRUDView(CarroController CarroController) {

        // NomeDono Carros
        labelTopo = new Label("Alunos: Leandro Moreira de Carvalho e Kevin da Silva Medeiros");
        labelTopo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // TexFields - pesquisa de Carros
        textFieldPesquisa = new TextField();
        textFieldPesquisa.setPromptText("Pesquisar Carro por Placa ou pelo nome do dono");
        textFieldPesquisa.setMaxWidth(400);

        // Botão para realizar a pesquisa
        botaoPesquisar = new Button("Pesquisar");
    
        // HBox para a barra de pesquisa
        HBox barraPesquisa = new HBox(textFieldPesquisa, botaoPesquisar);
        barraPesquisa.setSpacing(10);
        barraPesquisa.setAlignment(Pos.CENTER);

        // Mensagem de Alerta do tipo WARNING e suas propriedades
        mensagemAlerta = new Alert(Alert.AlertType.WARNING);
        mensagemAlerta.setTitle("Mensagem de Alerta");
        mensagemAlerta.setHeaderText(null);
        
        // Labels - rótulos de texto
        labelPlaca = new Label("Placa:");
        labelNomeDono = new Label("Nome do dono do Carro:");
        labelModelo = new Label("Modelo:");

        // TextFields - campos de entrada de texto
        textFieldPlaca = new TextField();
        textFieldPlaca.setMaxWidth(100);
        textFieldNomeDono = new TextField();
        textFieldNomeDono.setMaxWidth(400);
        textFieldModelo = new TextField();
        textFieldModelo.setMaxWidth(400);

        // Buttons - botões
        botaoCadastrar = new Button("Cadastrar Novo Carro");
        botaoAtualizar = new Button("Atualizar Carro");
        botaoExcluir = new Button("Excluir Carro");
        
        // HBox - caixa vertical contendo os botões CRUD
        barraBotoes = new HBox(botaoCadastrar, botaoAtualizar, botaoExcluir);
        barraBotoes.setSpacing(10);
        barraBotoes.setAlignment(Pos.CENTER);        

        // TableView - tabela contendo os dados de Carro
        tabelaCarros = new TableView<>();
        listaDadosCarros = FXCollections.observableArrayList(); // Lista de dados observáveis a ser carregada para a tabela
        tabelaCarros.setItems(listaDadosCarros);
        for (Carro Carro : CarroController.getListaDadosEstudantes()) { // Carrega os dados do banco de dados para a lista observável
            listaDadosCarros.add(Carro);
        }
        
        //TableColumns - colunas da tabela e suas propriedades
        colunaPlaca = new TableColumn<>("Placa");
        colunaPlaca.setMinWidth(150.0);
        colunaPlaca.setMaxWidth(200.0);
        colunaPlaca.setPrefWidth(175.0);
        colunaPlaca.setCellValueFactory(cellData -> cellData.getValue().PlacaProperty());

        colunaNomeDono = new TableColumn<>("NomeDono");
        colunaNomeDono.setMinWidth(200.0);
        colunaNomeDono.setMaxWidth(350.0);
        colunaNomeDono.setPrefWidth(275.0);
        colunaNomeDono.setCellValueFactory(cellData -> cellData.getValue().NomeDonoProperty());

        colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setMinWidth(150.0);
        colunaModelo.setMaxWidth(250.0);
        colunaModelo.setPrefWidth(200.0);
        colunaModelo.setCellValueFactory(cellData -> cellData.getValue().ModeloProperty());
        

        // Carrega as colunas na tabela
        tabelaCarros.getColumns().addAll(colunaPlaca, colunaNomeDono, colunaModelo);
        atualizarTabela(CarroController); // Chama o método que carrega a tabela com os dados do arquivo CSV

        // Listener para carregar as informações da linha selecionada na tabela para as caixas de texto
        tabelaCarros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textFieldNomeDono.setText(newSelection.getNomeDono());
                textFieldPlaca.setText(newSelection.getPlaca());
                textFieldModelo.setText(newSelection.getModelo());
            }
        });

        // Ação do botão Pesquisar
        botaoPesquisar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String filtro = textFieldPesquisa.getText().trim().toLowerCase();
                if (!filtro.isEmpty()) {
                    ObservableList<Carro> CarrosFiltrados = FXCollections.observableArrayList();
                    for (Carro Carro : CarroController.getListaDadosEstudantes()) {
                        if (Carro.getPlaca().toLowerCase().contains(filtro) ||
                            Carro.getNomeDono().toLowerCase().contains(filtro)) {
                            CarrosFiltrados.add(Carro);
                        }
                    }
                    tabelaCarros.setItems(CarrosFiltrados); // Atualiza a tabela com os resultados filtrados
                } else {
                    tabelaCarros.setItems(listaDadosCarros); // Restaura a tabela com todos os Carros
                }
            }
        });
        
        // Ações dos botões das operações de CRUD de Carro na interface
        botaoCadastrar.setOnAction(new EventHandler<ActionEvent>() { // Cadastrar Carro
            @Override
            public void handle(ActionEvent e) {
                Carro Carro = new Carro(textFieldNomeDono.getText(), textFieldPlaca.getText(), textFieldModelo.getText()); // Cria um Carro a partir dos dados das caixas de texto
                listaDadosCarros.add(Carro); // Insere o Carro na lista observável
                //CarroController.inserirCarro(Carro); // Insere o Carro no banco de dados
                CarroController.cadastrarEstudante(Carro); // Chama o método de cadastro da controladora de Estudante
                atualizarTabela(CarroController); // atualiza a tabela com os dados do arquivo CSV
                limparTextFields(); // Limpa caixas de texto
            }
        });

        botaoAtualizar.setOnAction(new EventHandler<ActionEvent>() { // Atualizar Carro
            @Override
            public void handle(ActionEvent e) {
                int i = tabelaCarros.getSelectionModel().getSelectedIndex(); // Pega a posição da linha selecionada da tabela
                Carro Carro = new Carro(textFieldNomeDono.getText(), textFieldPlaca.getText(), textFieldModelo.getText()); // Cria um Carro a partir dos dados das caixas de texto
                listaDadosCarros.set(i, Carro); // Atualiza o Carro na lista observável para a posição obtida
                //CarroController.atualizarCarro(Carro); // Atualiza o Carro no banco de dados
                CarroController.atualizarEstudante(i, Carro); // Chama o método de atualização da controladora de Estudante para a posição i da tabela
                atualizarTabela(CarroController); // atualiza a tabela com os dados do arquivo CSV
                limparTextFields(); // Limpa caixas de texto
            }
        });

        botaoExcluir.setOnAction(new EventHandler<ActionEvent>() { // Excluir Carro
            @Override
            public void handle(ActionEvent e) {
                if (textFieldNomeDono.getText().isBlank() || textFieldPlaca.getText().isBlank()) { 
                    mensagemAlerta.setContentText("Carro não encontrado!"); // Exemplo de mensagem de alerta - você pode criar outras!
                    mensagemAlerta.showAndWait();
                } else {
                    Carro Carro = tabelaCarros.getSelectionModel().getSelectedItem();  // Cria um Carro a partir dos dados linha selecionada na tabela
                    //String response = CarroController.excluirCarroPorPlaca(Carro.getPlaca()); // Exclui o Carro no banco de dados
                    //  if(response.equals("Carro não encontrado!")){ // Verifica se o Carro não existe
                    //    mensagemAlerta.setContentText(response); // Exemplo de mensagem de alerta - você pode criar outras!
                    //    mensagemAlerta.showAndWait();
                    //    return;
                    //}
                    CarroController.excluirEstudante(Carro); // Chama o método de exclusão da controladora de Estudante
                    listaDadosCarros.remove(Carro); // Remove o Carro da lista observável
                    atualizarTabela(CarroController); // atualiza a tabela com os dados do arquivo CSV
                    limparTextFields(); // Limpa caixas de texto
                }
            }
        });

        
        // VBox - caixa vertical que contém todos os elementos da tela/cena e suas propriedades 
        vbox = new VBox(labelTopo, labelPlaca, textFieldPlaca, labelNomeDono, textFieldNomeDono, labelModelo, textFieldModelo, barraBotoes, barraPesquisa, tabelaCarros);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 650, 500); // Neste caso a VBox está funcionando como o layout da telan/cena
    }

    public Scene getScene(){ // Método que retorna a tela/cena para ser usada no palco
        return this.scene;
    }
    
    public void limparTextFields() {
        textFieldPlaca.clear();
        textFieldNomeDono.clear();
        textFieldModelo.clear(); // Limpa o campo do Modelo
    }
    
}

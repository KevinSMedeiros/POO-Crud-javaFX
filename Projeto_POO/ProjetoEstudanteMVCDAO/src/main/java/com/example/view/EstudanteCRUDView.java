// Esta é a classe com a tela/cena da interface das operações do CRUD de Estudante
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

import com.example.Estudante;
import com.example.controller.EstudanteController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EstudanteCRUDView {

    Label labelRGA, labelNome, labelCurso;
    TextField textFieldRGA, textFieldNome, textFieldCurso;
    Button botaoCadastrar, botaoAtualizar, botaoExcluir;
    VBox vbox;
    HBox barraBotoes;
    Scene scene;
    TableView<Estudante> tabelaEstudantes;
    TableColumn<Estudante, String> colunaRGA, colunaNome, colunaCurso;
    ObservableList<Estudante> listaDadosEstudantes;
    Alert mensagemAlerta;
    TextField textFieldPesquisa;
    Button botaoPesquisar;

    // Método construtor que irá inicializar todos os elementos de interface da tela
    public EstudanteCRUDView(EstudanteController estudanteController) {

        // TexFields - pesquisa de estudantes
        textFieldPesquisa = new TextField();
        textFieldPesquisa.setPromptText("Pesquisar estudante por RGA ou Nome");
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
        labelRGA = new Label("RGA:");
        labelNome = new Label("Nome do(a) Estudante:");
        labelCurso = new Label("Curso:");

        // TextFields - campos de entrada de texto
        textFieldRGA = new TextField();
        textFieldRGA.setMaxWidth(100);
        textFieldNome = new TextField();
        textFieldNome.setMaxWidth(400);
        textFieldCurso = new TextField();
        textFieldCurso.setMaxWidth(400);

        // Buttons - botões
        botaoCadastrar = new Button("Cadastrar Novo Estudante");
        botaoAtualizar = new Button("Atualizar Estudante");
        botaoExcluir = new Button("Excluir Estudante");
        
        // HBox - caixa vertical contendo os botões CRUD
        barraBotoes = new HBox(botaoCadastrar, botaoAtualizar, botaoExcluir);
        barraBotoes.setSpacing(10);
        barraBotoes.setAlignment(Pos.CENTER);        

        // TableView - tabela contendo os dados de Estudante
        tabelaEstudantes = new TableView<>();
        listaDadosEstudantes = FXCollections.observableArrayList(); // Lista de dados observáveis a ser carregada para a tabela
        tabelaEstudantes.setItems(listaDadosEstudantes);
        for (Estudante estudante : estudanteController.mostrarEstudantes()) { // Carrega os dados do banco de dados para a lista observável
            listaDadosEstudantes.add(estudante);
        }
        
        //TableColumns - colunas da tabela e suas propriedades
        colunaRGA = new TableColumn<>("RGA");
        colunaRGA.setMinWidth(150.0);
        colunaRGA.setMaxWidth(200.0);
        colunaRGA.setPrefWidth(175.0);
        colunaRGA.setCellValueFactory(cellData -> cellData.getValue().RGAProperty());

        colunaNome = new TableColumn<>("Nome");
        colunaNome.setMinWidth(200.0);
        colunaNome.setMaxWidth(350.0);
        colunaNome.setPrefWidth(275.0);
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

        colunaCurso = new TableColumn<>("Curso");
        colunaCurso.setMinWidth(150.0);
        colunaCurso.setMaxWidth(250.0);
        colunaCurso.setPrefWidth(200.0);
        colunaCurso.setCellValueFactory(cellData -> cellData.getValue().cursoProperty());
        

        // Carrega as colunas na tabela
        tabelaEstudantes.getColumns().addAll(colunaRGA, colunaNome, colunaCurso);

        // Listener para carregar as informações da linha selecionada na tabela para as caixas de texto
        tabelaEstudantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textFieldNome.setText(newSelection.getNome());
                textFieldRGA.setText(newSelection.getRGA());
            }
        });

        // Ação do botão Pesquisar
        botaoPesquisar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String filtro = textFieldPesquisa.getText().trim().toLowerCase();
                if (!filtro.isEmpty()) {
                    ObservableList<Estudante> estudantesFiltrados = FXCollections.observableArrayList();
                    for (Estudante estudante : estudanteController.mostrarEstudantes()) {
                        if (estudante.getRGA().toLowerCase().contains(filtro) ||
                            estudante.getNome().toLowerCase().contains(filtro)) {
                            estudantesFiltrados.add(estudante);
                        }
                    }
                    tabelaEstudantes.setItems(estudantesFiltrados); // Atualiza a tabela com os resultados filtrados
                } else {
                    tabelaEstudantes.setItems(listaDadosEstudantes); // Restaura a tabela com todos os estudantes
                }
            }
        });
        
        // Ações dos botões das operações de CRUD de Estudante na interface
        botaoCadastrar.setOnAction(new EventHandler<ActionEvent>() { // Cadastrar Estudante
            @Override
            public void handle(ActionEvent e) {
                Estudante estudante = new Estudante(textFieldNome.getText(), textFieldRGA.getText(), textFieldCurso.getText()); // Cria um estudante a partir dos dados das caixas de texto
                listaDadosEstudantes.add(estudante); // Insere o estudante na lista observável
                estudanteController.inserirEstudante(estudante); // Insere o estudante no banco de dados
                limparTextFields(); // Limpa caixas de texto
            }
        });

        botaoAtualizar.setOnAction(new EventHandler<ActionEvent>() { // Atualizar Estudante
            @Override
            public void handle(ActionEvent e) {
                int i = tabelaEstudantes.getSelectionModel().getSelectedIndex(); // Pega a posição da linha selecionada da tabela
                Estudante estudante = new Estudante(textFieldNome.getText(), textFieldRGA.getText(), textFieldCurso.getText()); // Cria um estudante a partir dos dados das caixas de texto
                listaDadosEstudantes.set(i, estudante); // Atualiza o estudante na lista observável para a posição obtida
                estudanteController.atualizarEstudante(estudante); // Atualiza o estudante no banco de dados
                limparTextFields(); // Limpa caixas de texto
            }
        });

        botaoExcluir.setOnAction(new EventHandler<ActionEvent>() { // Excluir Estudante
            @Override
            public void handle(ActionEvent e) {
                if (textFieldNome.getText().isBlank() || textFieldRGA.getText().isBlank()) { 
                    mensagemAlerta.setContentText("Estudante não encontrado(a)!"); // Exemplo de mensagem de alerta - você pode criar outras!
                    mensagemAlerta.showAndWait();
                } else {
                    Estudante estudante = tabelaEstudantes.getSelectionModel().getSelectedItem();  // Cria um estudante a partir dos dados linha selecionada na tabela
                    listaDadosEstudantes.remove(estudante); // Remove o estudante da lista observável
                    estudanteController.excluirEstudantePorRGA(estudante.getRGA()); // Exclui o estudante no banco de dados
                    limparTextFields(); // Limpa caixas de texto
                }
            }
        });

        
        // VBox - caixa vertical que contém todos os elementos da tela/cena e suas propriedades 
        vbox = new VBox(labelRGA, textFieldRGA, labelNome, textFieldNome, labelCurso, textFieldCurso, barraBotoes, barraPesquisa, tabelaEstudantes);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 650, 500); // Neste caso a VBox está funcionando como o layout da telan/cena
    }

    public Scene getScene(){ // Método que retorna a tela/cena para ser usada no palco
        return this.scene;
    }
    
    public void limparTextFields() {
        textFieldRGA.clear();
        textFieldNome.clear();
        textFieldCurso.clear(); // Limpa o campo do curso
    }
    
}

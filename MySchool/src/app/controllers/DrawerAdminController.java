package app.controllers;

import java.io.IOException;

import app.models.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DrawerAdminController {
	private VBox content = null;
	private Label topBarLb = null;
	private Funcionario user = null;
	
	@FXML
    private Button cadastrarCursoBt;

    @FXML
    private Button cadastrarProfBt;

    @FXML
    private Button cadastrarAlunoBt;
    
    @FXML
    private Button cadastrarDisciplinaBt;

    @FXML
    private Button cadastrarTurmaBt;
    
    /**
     * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para cadastrar aluno e
     * mudando o título para Cadastrar Aluno
     * @param event - evento de clique do botão
     */
    @FXML
    void changePageAluno(ActionEvent event) {
    	CadastrarAlunoController alunoController = new CadastrarAlunoController();
    	topBarLb.setText("Cadastrar Aluno");
		changePage("cadastrarAluno.fxml", alunoController);
    }
    
    /**
     * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para cadastrar curso e
     * mudando o título para Cadastrar Curso
     * @param event - evento de clique do botão
     */
    @FXML
    void changePageCurso(ActionEvent event) {
    	CadastrarCursoController cursoController = new CadastrarCursoController();
    	topBarLb.setText("Cadastrar Curso");
    	changePage("cadastrarCurso.fxml", cursoController);
    }

    /**
     * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para cadastrar professor e
     * mudando o título para Cadastrar Professor
     * @param event - evento de clique do botão
     */
    @FXML
    void changePageProf(ActionEvent event) {
    	CadastrarProfessorController profController = new CadastrarProfessorController();
    	topBarLb.setText("Cadastrar Professor");
    	changePage("cadastrarProf.fxml", profController);
    }

    /**
     * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para cadastrar turma e
     * mudando o título para Cadastrar Turma
     * @param event - evento de clique do botão
     */
    @FXML
    void changePageTurma(ActionEvent event) {
    	CadastrarTurmaController turmaController = new CadastrarTurmaController();
    	topBarLb.setText("Cadastrar Turma");
    	changePage("cadastrarTurma.fxml", turmaController);
    }
    
    /**
     * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para cadastrar disciplina e
     * mudando o título para Cadastrar Disciplina
     * @param event - evento de clique do botão
     */
    @FXML
    void changePageDisciplina(ActionEvent event) {
    	CadastrarDisciplinaController disciplinaController = new CadastrarDisciplinaController();
    	topBarLb.setText("Cadastrar Disciplina");
    	changePage("cadastrarDisciplina.fxml", disciplinaController);
    }
    
    /**
     * Irá mudar o conteúdo para uma nova página
     * @param pageName - nome do arquivo fxml
     * @param controller - controlador da tela a ser mudada
     */
    public void changePage(String pageName, Object controller) {
    	FXMLLoader loader;
		try{
			loader = new FXMLLoader(getClass().getResource("/resources/views/" + pageName));
			loader.setController(controller);
			content.getChildren().clear();
			content.getChildren().add(loader.load());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Irá puxar o usuário logado
     * @param f - Funcionário que logou
     */
    public void initData(Funcionario f) {
    	user = f;
    }
	
    /**
     * Irá receber os componentes conteúdo e o título,
     * para mudá-los depois
     * @param content - conteúdo onde irá ser mostrado as telas
     * @param topBarLb - título da tela
     */
	public DrawerAdminController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}
	
	
}

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
    
    @FXML
    void changePageAluno(ActionEvent event) {
    	CadastrarAlunoController alunoController = new CadastrarAlunoController();
    	topBarLb.setText("Cadastrar Aluno");
		changePage("cadastrarAluno.fxml", alunoController);
    }

    @FXML
    void changePageCurso(ActionEvent event) {
    	CadastrarCursoController cursoController = new CadastrarCursoController();
    	topBarLb.setText("Cadastrar Curso");
    	changePage("cadastrarCurso.fxml", cursoController);
    }

    @FXML
    void changePageProf(ActionEvent event) {
    	CadastrarProfessorController profController = new CadastrarProfessorController();
    	topBarLb.setText("Cadastrar Professor");
    	changePage("cadastrarProf.fxml", profController);
    }

    @FXML
    void changePageTurma(ActionEvent event) {
    	CadastrarTurmaController turmaController = new CadastrarTurmaController();
    	topBarLb.setText("Cadastrar Turma");
    	changePage("cadastrarTurma.fxml", turmaController);
    }
    
    @FXML
    void changePageDisciplina(ActionEvent event) {
    	CadastrarDisciplinaController disciplinaController = new CadastrarDisciplinaController();
    	topBarLb.setText("Cadastrar Disciplina");
    	changePage("cadastrarDisciplina.fxml", disciplinaController);
    }
    
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
    
    public void initData(Funcionario f) {
    	user = f;
    }
	
	public DrawerAdminController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}
	
	
}

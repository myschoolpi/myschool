package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DrawerAdminController {
	private VBox content = null;
	private Label topBarLb = null;
	
	@FXML
    private Button cadastrarCursoBt;

    @FXML
    private Button cadastrarProfBt;

    @FXML
    private Button cadastrarAlunoBt;

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
//    	changePage("cadastrarCurso.fxml");
    }

    @FXML
    void changePageProf(ActionEvent event) {
    	CadastrarProfessorController profController = new CadastrarProfessorController();
    	topBarLb.setText("Cadastrar Professor");
    	changePage("cadastrarProf.fxml", profController);
    }

    @FXML
    void changePageTurma(ActionEvent event) {
//    	changePage("cadastrarTurma.fxml");
    }
    
    public void changePage(String pageName, Object controller) {
    	FXMLLoader loader;
		try{
			loader = new FXMLLoader(getClass().getResource("/resources/views/" + pageName));
			loader.setController(controller);
			content.getChildren().clear();
			content.getChildren().add(loader.load());
		} catch(IOException e) {
			System.out.println("ERRO DE CARREGAMENTO DA PÁGINA" + e.toString());
		}
	}
	
	public DrawerAdminController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}
	
	
}

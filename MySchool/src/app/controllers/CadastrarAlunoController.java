package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import app.models.Aluno;
import app.services.*;

public class CadastrarAlunoController {
	private Aluno aluno = null;
	private AlunoService as = null;

    @FXML
    private TextField nomeTF;
    
    @FXML
    private TextField enderecoTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField cpfTF;

    @FXML
    private TextField rgTF;

    @FXML
    void cadastrarAluno(ActionEvent event) {
    	aluno = new Aluno();
    	as = new AlunoService();
    	
    	aluno.setNome(nomeTF.getText());
    	aluno.setEndereco(enderecoTF.getText());
    	aluno.setEmail(emailTF.getText());
    	aluno.setCpf(cpfTF.getText());
    	aluno.setRg(rgTF.getText());
    	aluno.setSenha(cpfTF.getText());
    	
    	as.create(aluno);
    }

}

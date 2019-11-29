package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.JOptionPane;

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
    
    /**
     * irá pegar todos os dados inseridos, criar um novo aluno
     * e chamar a função que insere o aluno no banco de dados,
     * mostrando a resposta num diálogo
     * @param event - evento de quando clicar no botão cadastrar
     */
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
    	
    	JOptionPane.showMessageDialog(null, as.create(aluno));
    }

}

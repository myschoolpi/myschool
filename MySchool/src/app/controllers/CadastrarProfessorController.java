package app.controllers;

import javax.swing.JOptionPane;

import app.models.Professor;
import app.services.ProfessorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CadastrarProfessorController {
	private Professor prof = null;
	private ProfessorService ps = null;
	
	@FXML
	private TextField nomeTF;

	@FXML
	private TextField enderecoTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField phoneTF;

	@FXML
	private TextField cpfTF;

	@FXML
	private TextField rgTF;

	@FXML
	void cadastrarProfessor(ActionEvent event) {
		prof = new Professor();
		ps = new ProfessorService();
		
		prof.setNome(nomeTF.getText());
		prof.setEndereco(enderecoTF.getText());
		prof.setEmail(emailTF.getText());
		prof.setTelefone(phoneTF.getText());
		prof.setCpf(cpfTF.getText());
		prof.setRg(rgTF.getText());
		prof.setSenha(cpfTF.getText());
		
		JOptionPane.showMessageDialog(null, ps.create(prof));
	}
}

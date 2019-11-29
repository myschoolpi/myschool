package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.JOptionPane;

import app.models.*;
import app.services.*;

public class LoginController {
	private final String ALUNO = "Aluno";
	private final String PROFESSOR = "Professor";
	private final String FUNC = "Funcionario";
	private final int CARGO_ADMIN = 1;
	
	private Aluno aluno = null;
	private AlunoService alunoService = null;
	private Professor prof = null;
	private ProfessorService professorService= null;
	private Funcionario func = null;
	private FuncionarioService funcService = null;
	private SceneController sc = null;
	
	@FXML
	private TextField emailTF;
	
	@FXML
	private TextField passwordTF;
	
	@FXML
	private Button loginBt;
	
	@FXML
    private ToggleGroup role;
	
	@FXML
	private RadioButton alunoRB;
	
	@FXML
	private RadioButton professorRB;
	
	@FXML
	private RadioButton funcRB;
	
	/**
	 * Irá pegar usuário, senha e a opção selecionada
	 * e chamar a função que verifica se aquele usuário existe
	 * @param event - evento de clique do botão
	 */
	@FXML
	void login(ActionEvent event){
		sc = new SceneController();
		
		String email = emailTF.getText();
		String senha = passwordTF.getText();
		
		RadioButton radio = (RadioButton) role.getSelectedToggle();
		String role = radio.getText();
		
		if (role.equals(ALUNO)) {
			alunoService = new AlunoService();
			aluno = alunoService.alunoLogin(email, senha);
			
			if(aluno !=null){
				HomeAlunoController ac = new HomeAlunoController();
				ac.initData(aluno);
				sc.changeScreen(event, "homeAluno.fxml", ac);
			} else {
				JOptionPane.showMessageDialog(null, "Usuário/Senha incorretos");
			}
		} else if (role.equals(PROFESSOR)) {
			professorService = new ProfessorService();
			prof = professorService.professorLogin(email, senha);
			
			if(prof !=null){
				HomeProfessorController pc = new HomeProfessorController();
				pc.initData(prof);
				sc.changeScreen(event, "homeProfessor.fxml", pc);
			} else {
				JOptionPane.showMessageDialog(null, "Usuário/Senha incorretos");
			}
		} else if (role.equals(FUNC)) {
			funcService = new FuncionarioService();
			func = funcService.funcionarioLogin(email, senha);
			
			if(func !=null ){
				if(func.getCargo().id == CARGO_ADMIN) {
					HomeAdminController ac = new HomeAdminController();
					sc.changeScreen(event, "homeAdmin.fxml", ac);
				} else {
					JOptionPane.showMessageDialog(null, "Você não tem permissão");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuário/Senha incorretos");
			}
			
		} else {
			System.out.println("Selecione uma opção");
		}
	}
	
	
}

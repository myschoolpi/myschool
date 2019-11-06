package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
				sc.changeScreen(event, "homeAluno.fxml");
			} else {
				System.out.println("N EXISTE");
			}
		} else if (role.equals(PROFESSOR)) {
			professorService = new ProfessorService();
			prof = professorService.professorLogin(email, senha);
			
			if(prof !=null){
				sc.changeScreen(event, "homeProfessor.fxml");
			} else {
				System.out.println("N EXISTE");
			}
		} else if (role.equals(FUNC)) {
			funcService = new FuncionarioService();
			func = funcService.funcionarioLogin(email, senha);
			
			if(func !=null ){
				if(func.getCargo().id == CARGO_ADMIN) {
					sc.changeScreen(event, "homeAdmin.fxml");
				} else {
					System.out.println("ACESSO NEGADO");
				}
			} else {
				System.out.println("N EXISTE");
			}
			
		} else {
			System.out.println("Selecione uma opção");
		}
	}
	
	
}

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
	
	private Aluno aluno = null;
	private AlunoService alunoService = null;
	private Professor prof = null;
	private ProfessorService professorService= null;
	private Funcionario func = null;
	private FuncionarioService funcService = null;
	
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
		String email = emailTF.getText();
		String senha = passwordTF.getText();
		
		RadioButton radio = (RadioButton) role.getSelectedToggle();
		String role = radio.getText();
		
		if (role.equals(ALUNO)) {
			alunoService = new AlunoService();
			aluno = alunoService.alunoLogin(email, senha);
			
			if(aluno !=null){
				System.out.println("LOGADO");
			} else {
				System.out.println("N EXISTE");
			}
		} else if (role.equals(PROFESSOR)) {
			professorService = new ProfessorService();
			prof = professorService.professorLogin(email, senha);
			
			if(prof !=null){
				System.out.println("LOGADO");
			} else {
				System.out.println("N EXISTE");
			}
		} else if (role.equals(FUNC)) {
			funcService = new FuncionarioService();
			func = funcService.funcionarioLogin(email, senha);
			if(func !=null){
				System.out.println("LOGADO");
			} else {
				System.out.println("N EXISTE");
			}
		} else {
			System.out.println("Selecione uma opção");
		}
	}
	
	
}

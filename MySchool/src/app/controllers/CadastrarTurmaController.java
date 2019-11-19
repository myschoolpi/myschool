package app.controllers;

import java.util.ArrayList;

import app.models.Turma;
import app.models.Aluno;
import app.models.Curso;
import app.models.Professor;
import app.services.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class CadastrarTurmaController {
	private ArrayList<Object> listaProfessores;
	private ArrayList<Object> listaCursos;
	private ArrayList<Object> listaAlunos;
	private ObservableList<Aluno> listaTableAlunos = FXCollections.observableArrayList();
	private Turma t = null;

	private AlunoService as = null;
	private ProfessorService ps = null;
	private CursoService cs = null;

	@FXML
	private ChoiceBox<Curso> cursoSelect;

	@FXML
	private ChoiceBox<Professor> profSelect;

	@FXML
	private ChoiceBox<String> periodSelect;

	@FXML
	private void initialize() {
		as = new AlunoService();
		ps = new ProfessorService();
		cs = new CursoService();
		
		listaProfessores = ps.getMany();
		
		profSelect.getItems().addAll((Professor[]) listaProfessores.toArray());
		
		
		
		listaCursos = cs.getMany();
		
		listaAlunos = as.getMany();
		
		

	}

}

package app.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Turma;
import app.models.Aluno;
import app.models.Curso;
import app.models.Professor;
import app.services.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

public class CadastrarTurmaController {
	private ArrayList<Object> listaProfessores;
	private ArrayList<Object> listaCursos;
	private ArrayList<Object> listaAlunos;
	private ObservableList<Aluno> listaTableAlunos = FXCollections.observableArrayList();
	
	private ArrayList<Aluno> alunosTurma = new ArrayList<Aluno>();
	private Turma t = null;

	private AlunoService as = null;
	private ProfessorService ps = null;
	private CursoService cs = null;
	private TurmaService ts = null;

	@FXML
	private ChoiceBox<Curso> cursoSelect;

	@FXML
	private ChoiceBox<Professor> profSelect;

	@FXML
	private ChoiceBox<String> periodSelect;
	
	@FXML
    private TableView<Aluno> alunoTable;

	@FXML
	private TableColumn<Aluno, String> nomeAlunoCol;

	@FXML
	private TableColumn<Aluno, String> cpfAlunoCol;
	
	@FXML
    private TextField diasTF;

	@FXML
	private void initialize() {
		as = new AlunoService();
		ps = new ProfessorService();
		cs = new CursoService();
		
		periodSelect.getItems().addAll("Manhã","Tarde","Noite");
		
		nomeAlunoCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpfAlunoCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		listaProfessores = ps.getMany();

		for (int i = 0; i < listaProfessores.size(); i++) {
			profSelect.getItems().add(((Professor) listaProfessores.get(i)));
		}

		listaCursos = cs.getMany();
		for (int i = 0; i < listaCursos.size(); i++) {
			cursoSelect.getItems().add(((Curso) listaCursos.get(i)));
		}

		listaAlunos = as.getMany();
		
		for(int i = 0; i< listaAlunos.size(); i++) {
			listaTableAlunos.add((Aluno) listaAlunos.get(i));
		}
		
		alunoTable.setItems(listaTableAlunos);
		alunoTable.setEditable(true);

	}
	
	@FXML
    void addAluno(ActionEvent event) {
		Aluno a = alunoTable.getSelectionModel().getSelectedItem();

		boolean isAdded = false;
		for (int i = 0; i < alunosTurma.size(); i++) {
			if (a.id == alunosTurma.get(i).id) {
				System.out.println(a.id);
				isAdded = true;
				break;
			}
		}

		if (!isAdded) {
			alunosTurma.add(a);
			JOptionPane.showMessageDialog(null, "Aluno Adicionado");
		} else
			JOptionPane.showMessageDialog(null, "Você já selecionou este aluno");
    }

    @FXML
    void cadastrarTurma(ActionEvent event) {
    	t = new Turma();
    	ts = new TurmaService();
    	
    	t.setAlunosTurma(alunosTurma);
    	t.setCursoTurma(cursoSelect.getValue());
    	t.setProfTurma(profSelect.getValue());
    	t.setDiasSemana(diasTF.getText());
    	t.setPeriodo(periodSelect.getValue());
    	
    	JOptionPane.showMessageDialog(null, ts.createFullTurma(t));
    }

    @FXML
    void removeAluno(ActionEvent event) {
    	Aluno a = alunoTable.getSelectionModel().getSelectedItem();

		if(alunosTurma.contains(a)) {
			alunosTurma.remove(a);
			JOptionPane.showMessageDialog(null, "Aluno removido da lista");
		} else {
			JOptionPane.showMessageDialog(null, "Aluno não foi adicionado");
		}
    }

    @FXML
    void verAlunos(ActionEvent event) {
    	String alunos = "";

		for (int i = 0; i < alunosTurma.size(); i++) {
			alunos += alunosTurma.get(i).getNome() + "\n";
		}

		JOptionPane.showMessageDialog(null, alunos);
    }

}

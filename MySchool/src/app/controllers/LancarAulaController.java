package app.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Aluno;
import app.models.Aula;
import app.models.Professor;
import app.models.Turma;
import app.services.AlunoService;
import app.services.AulaService;
import app.services.TurmaService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class LancarAulaController {
	private Professor user = null;

	private ArrayList<Aluno> listaAlunos;
	private ArrayList<Turma> listaTurmas;
	private ObservableList<Aluno> listaTableAlunos = FXCollections.observableArrayList();
	private ArrayList<Aluno> alunosAula = new ArrayList<Aluno>();
	private ArrayList<Aluno> oldAlunosAula = new ArrayList<Aluno>();

	private Turma t = null;
	private Aula a = null;
	private Aula aula = null;

	private AlunoService as = null;
	private TurmaService ts = null;
	private AulaService aulaS = null;

	@FXML
	private ChoiceBox<Turma> turmaSelect;

	@FXML
	private DatePicker dataAula;

	@FXML
	private TextArea descAulaTA;

	@FXML
	private TableView<Aluno> alunoTable;

	@FXML
	private TableColumn<Aluno, String> nomeAlunoCol;

	@FXML
	private Button saveButton;

	@FXML
	private void initialize() {
		as = new AlunoService();
		ts = new TurmaService();

		listaTurmas = ts.getTurmasProfessor(user.id);
		nomeAlunoCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

		for (int i = 0; i < listaTurmas.size(); i++) {
			turmaSelect.getItems().add(listaTurmas.get(i));
		}

		turmaSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Turma>() {
			@Override
			public void changed(ObservableValue<? extends Turma> observable, Turma oldValue, Turma newValue) {
				listaAlunos = as.getAlunosTurma(newValue.id);
				listaTableAlunos.addAll(listaAlunos);

				alunoTable.setItems(listaTableAlunos);
				alunoTable.setEditable(true);
			}

		});

	}

	public void initData(Professor prof) {
		user = prof;
	}

	@FXML
	void getAula(ActionEvent event) {
		aulaS = new AulaService();
		Date d = Date.valueOf(dataAula.getValue());
		int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;

		aula = aulaS.getAulaByDate(d, listaAlunos, idTurma);

		if (aula != null) {
			a = aula;
			descAulaTA.setText(a.getDescricao());

			oldAlunosAula.addAll(alunosAula);
			alunosAula.clear();
			alunosAula.addAll(a.getAlunosAula());

			dataAula.setEditable(false);

			saveButton.setText("ATUALIZAR");
			saveButton.setOnAction((ActionEvent e) -> {
				atualizarAula(e);
			});
		}
	}

	@FXML
	void addAluno(ActionEvent event) {
		Aluno aluno = alunoTable.getSelectionModel().getSelectedItem();

		boolean isAdded = false;
		for (int i = 0; i < alunosAula.size(); i++) {
			if (aluno.id == alunosAula.get(i).id) {
				System.out.println(aluno.id);
				isAdded = true;
				break;
			}
		}

		if (!isAdded) {
			alunosAula.add(aluno);
			JOptionPane.showMessageDialog(null, "Aluno Adicionado");
		} else
			JOptionPane.showMessageDialog(null, "Voc� j� selecionou este aluno");
	}

	void atualizarAula(ActionEvent event) {
		aulaS = new AulaService();

		a.setDescricao(descAulaTA.getText());
		a.setAlunosAula(alunosAula);

		JOptionPane.showMessageDialog(null, aulaS.updateAulaPresenca(a, oldAlunosAula, a.id));
	}

	@FXML
	void lancarAula(ActionEvent event) {
		a = new Aula();
		aulaS = new AulaService();
		Date d = Date.valueOf(dataAula.getValue());
		int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;

		a.setData(d);
		a.setDescricao(descAulaTA.getText());
		a.setAlunosAula(alunosAula);
		a.setIdTurma(idTurma);

		JOptionPane.showMessageDialog(null, aulaS.create(a));
	}

	@FXML
	void removeAluno(ActionEvent event) {
		int i = alunoTable.getSelectionModel().getSelectedIndex();

		try {
			alunosAula.remove(i);
			JOptionPane.showMessageDialog(null, "Aluno removido da lista");
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Aluno n�o est� la lista");
		}

	}

	@FXML
	void verAlunos(ActionEvent event) {
		String alunos = "";

		for (int i = 0; i < alunosAula.size(); i++) {
			alunos += alunosAula.get(i).getNome() + "\n";
		}

		JOptionPane.showMessageDialog(null, alunos);
	}
}

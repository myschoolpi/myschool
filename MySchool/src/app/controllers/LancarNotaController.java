package app.controllers;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Aluno;
import app.models.AlunoNota;
import app.models.Avaliacao;
import app.models.Professor;
import app.models.Turma;
import app.services.AlunoService;
import app.services.AvaliacaoService;
import app.services.TurmaService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class LancarNotaController {
	private Professor user;
	private Avaliacao a;
	private AlunoNota al = null;

	private ArrayList<Aluno> listaAlunos;
	private ArrayList<Turma> listaTurmas;
	private ObservableList<AlunoNota> listaTableAlunos = FXCollections.observableArrayList();
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private ArrayList<AlunoNota> alunoNotas = new ArrayList<AlunoNota>();

	private AlunoService as;
	private TurmaService ts;
	private AvaliacaoService avaliacaoService;

	@FXML
	private ChoiceBox<Turma> turmaSelect;

	@FXML
	private ChoiceBox<String> avalicaoSelect;

	@FXML
	private TableView<AlunoNota> alunoTable;

	@FXML
	private TableColumn<AlunoNota, String> nomeAlunoCol;

	@FXML
	private TableColumn<AlunoNota, Double> notaCol;

	@FXML
	private Button saveButton;

	/**
	 * Chamada ao inicializar
	 * ir� baixar todas as turmas do professor logado e adicion�-las numa caixa de sele��o
	 */
	@FXML
	private void initialize() {
		as = new AlunoService();
		ts = new TurmaService();

		listaTurmas = ts.getTurmasProfessor(user.id);

		alunoTable.setEditable(true);

		nomeAlunoCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		notaCol.setCellValueFactory(new PropertyValueFactory<>("nota"));
		notaCol.setEditable(true);

		for (int i = 0; i < listaTurmas.size(); i++) {
			turmaSelect.getItems().add(listaTurmas.get(i));
		}

		turmaSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Turma>() {
			/**
			 *Ir� baixar os alunos da turma selecionada
			 */
			@Override
			public void changed(ObservableValue<? extends Turma> observable, Turma oldValue, Turma newValue) {
				listaAlunos = as.getAlunosTurma(newValue.id);

				for (int i = 0; i < listaAlunos.size(); i++) {
					Aluno a = listaAlunos.get(i);

					listaTableAlunos.add(new AlunoNota(a.id, a.getNome(), 0));
				}

				alunoTable.getItems().addAll(listaTableAlunos);
			}
		});

		avalicaoSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			/**
			 * Ir� baixar a avalia��o selecionada
			 * e mudar a estrutura da tela para atualiza��o de nota
			 */
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				avaliacaoService = new AvaliacaoService();
				String avaliacao = avalicaoSelect.getSelectionModel().getSelectedItem();
				int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;

				alunoNotas = avaliacaoService.getAvaliacaoNotas(avaliacao, idTurma);

				if (alunoNotas != null && alunoNotas.size() > 0) {
					listaTableAlunos.clear();
					listaTableAlunos.addAll(alunoNotas);
					
					alunoTable.getItems().clear();
					alunoTable.getItems().addAll(listaTableAlunos);
					alunoTable.setEditable(true);
					
					saveButton.setText("Atualizar Notas");

					saveButton.setOnAction((ActionEvent e) -> {
						atualizarNotas(e);
					});

				}

			}

		});

		avalicaoSelect.getItems().addAll("P1", "P2", "Trabalho");

	}

	/**
	 * Chama a fun��o que atualiza as notas dos alunos no banco de dados
	 * @param e - evento de clique do bot�o
	 */
	void atualizarNotas(ActionEvent e) {
		avaliacaoService = new AvaliacaoService();

		JOptionPane.showMessageDialog(null, avaliacaoService.updateAlunoNotas(alunoNotas));
	}

	/**
	 * Ir� adicionar uma nota para o aluno selecionado
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void addNotaAluno(ActionEvent event) {
		String avaliacao = avalicaoSelect.getSelectionModel().getSelectedItem();
		AlunoNota alunoNota = alunoTable.getSelectionModel().getSelectedItem();
		int index = alunoTable.getSelectionModel().getSelectedIndex();

		try {

			double notaAluno = Double.parseDouble(
					JOptionPane.showInputDialog("Insira a nota " + avaliacao + " para o Aluno " + alunoNota.getNome()));

			alunoNota.setNota(notaAluno);

			listaTableAlunos.set(index, alunoNota);
			alunoTable.getItems().set(index, alunoNota);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite um n�mero v�lido");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Selecione um aluno");
		}
	}

	/**Pega todos os dados inseridos e
	 * Chama a fun��o que adiciona as notas dos alunos no banco de dados
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void lancarNotas(ActionEvent event) {
		a = new Avaliacao();
		avaliacaoService = new AvaliacaoService();
		String descricao = avalicaoSelect.getSelectionModel().getSelectedItem();
		int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;
		
		alunoNotas = new ArrayList<AlunoNota>();
		alunoNotas.addAll(listaTableAlunos);

		a.setDescricao(descricao);

		JOptionPane.showMessageDialog(null, avaliacaoService.lancarNotas(a, alunoNotas, idTurma));

	}

	/**
	 * Ir� puxar o usu�rio logado
	 * @param prof - Professor logado
	 */
	public void initData(Professor prof) {
		user = prof;
	}
}

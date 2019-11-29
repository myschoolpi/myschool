package app.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Curso;
import app.models.Disciplina;
import app.services.CursoService;
import app.services.DisciplinaService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastrarCursoController {
	private DisciplinaService ds = null;
	private ArrayList<Object> listaDiscliplinas;
	private ArrayList<Disciplina> disciplinasCurso = new ArrayList<Disciplina>();
	private ObservableList<Disciplina> listaTableDisciplinas = FXCollections.observableArrayList();
	private Curso c = null;
	private CursoService cs = null;

	@FXML
	private TextField nomeTF;

	@FXML
	private TextArea descTF;

	@FXML
	private TableView<Disciplina> disciplinaTable;

	@FXML
	private TableColumn<Disciplina, String> nomeColumn;

	@FXML
	private TextField duracaoTF;
	
	/**
	 * Irá pegar todos os dados inseridos e criar um novo curso
	 * e chamar a função que insere o curso no banco de dados,
	 * mostrando a resposta num diálogo
	 * @param event - evento clique do botão
	 */
	@FXML
	public void cadastrarCurso(ActionEvent event) {
		c = new Curso();
		cs = new CursoService();

		c.setNome(nomeTF.getText());
		c.setDescricao(descTF.getText());
		c.setDuracao(duracaoTF.getText());

		c.setDisciplinas(disciplinasCurso);

		JOptionPane.showMessageDialog(null, cs.createFullCurso(c));
	}

	/**
	 * Chamada ao inicializar a tela,
	 * irá baixar todas as disciplinas e adicioná-las em uma tabela
	 */
	@FXML
	private void initialize() {
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		ds = new DisciplinaService();
		listaDiscliplinas = ds.getMany();

		for (int i = 0; i < listaDiscliplinas.size(); i++) {
			listaTableDisciplinas.add((Disciplina) listaDiscliplinas.get(i));
		}

		disciplinaTable.setItems(listaTableDisciplinas);
		disciplinaTable.setEditable(true);
	}

	/**
	 * Irá adicionar uma disciplina na lista de disciplinas do curso a ser criado,
	 * verificando se a disciplina já foi adicionada ou não
	 * @param event - evento de clique do botão
	 */
	@FXML
	public void addDisciplina(ActionEvent event) {
		Disciplina d = disciplinaTable.getSelectionModel().getSelectedItem();

		boolean isAdded = false;
		for (int i = 0; i < disciplinasCurso.size(); i++) {
			if (d.id == disciplinasCurso.get(i).id) {
				isAdded = true;
				break;
			}
		}

		if (!isAdded) {
			disciplinasCurso.add(d);
			JOptionPane.showMessageDialog(null, "Disciplina Adicionada");
		} else
			JOptionPane.showMessageDialog(null, "Você já selecionou esta disciplina");

	}

	/**
	 * Irá remover a disciplina da lista de disciplinas do curso a ser criado,
	 * verificando se ela já foi adicionada anteriormente
	 * @param event - evento de clique do botão
	 */
	@FXML
	void removeDisciplina(ActionEvent event) {
		Disciplina d = disciplinaTable.getSelectionModel().getSelectedItem();

		if(disciplinasCurso.contains(d)) {
			disciplinasCurso.remove(d);
			JOptionPane.showMessageDialog(null, "Disciplina removida da lista");
		} else {
			JOptionPane.showMessageDialog(null, "Disciplina não foi adicionada");
		}
	}

	/**
	 * Irá mostrar as disciplinas adicionadas, num diálogo
	 * @param event - evento de clique do botão
	 */
	@FXML
	void verDisciplinas(ActionEvent event) {
		String disciplinas = "";

		for (int i = 0; i < disciplinasCurso.size(); i++) {
			disciplinas += disciplinasCurso.get(i).getNome() + "\n";
		}

		JOptionPane.showMessageDialog(null, disciplinas);
	}

}

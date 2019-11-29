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
	 * Ir� pegar todos os dados inseridos e criar um novo curso
	 * e chamar a fun��o que insere o curso no banco de dados,
	 * mostrando a resposta num di�logo
	 * @param event - evento clique do bot�o
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
	 * ir� baixar todas as disciplinas e adicion�-las em uma tabela
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
	 * Ir� adicionar uma disciplina na lista de disciplinas do curso a ser criado,
	 * verificando se a disciplina j� foi adicionada ou n�o
	 * @param event - evento de clique do bot�o
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
			JOptionPane.showMessageDialog(null, "Voc� j� selecionou esta disciplina");

	}

	/**
	 * Ir� remover a disciplina da lista de disciplinas do curso a ser criado,
	 * verificando se ela j� foi adicionada anteriormente
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void removeDisciplina(ActionEvent event) {
		Disciplina d = disciplinaTable.getSelectionModel().getSelectedItem();

		if(disciplinasCurso.contains(d)) {
			disciplinasCurso.remove(d);
			JOptionPane.showMessageDialog(null, "Disciplina removida da lista");
		} else {
			JOptionPane.showMessageDialog(null, "Disciplina n�o foi adicionada");
		}
	}

	/**
	 * Ir� mostrar as disciplinas adicionadas, num di�logo
	 * @param event - evento de clique do bot�o
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

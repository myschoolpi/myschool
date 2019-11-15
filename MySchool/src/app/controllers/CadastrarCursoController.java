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

	@FXML
	public void cadastrarCurso(ActionEvent event) {
		c = new Curso();
		cs = new CursoService();
				
		c.setNome(nomeTF.getText());
		c.setDescricao(descTF.getText());
		c.setDuracao(duracaoTF.getText());

		c.setDisciplinas(disciplinasCurso);
		
		JOptionPane.showMessageDialog(null, cs.createCursoComDisciplina(c));
	}

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
		}
		else
			JOptionPane.showMessageDialog(null, "Você já selecionou esta disciplina");

	}

	@FXML
	void verDisciplinas(ActionEvent event) {
		String disciplinas = "";
		
		for(int i = 0; i< disciplinasCurso.size(); i++) {
			disciplinas += disciplinasCurso.get(i).getNome() + "\n";
		}
		
		JOptionPane.showMessageDialog(null, disciplinas);
	}

	private ObservableList<Disciplina> listaTableDisciplinas = FXCollections.observableArrayList();

}

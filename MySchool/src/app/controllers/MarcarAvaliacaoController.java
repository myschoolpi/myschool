package app.controllers;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Avaliacao;
import app.models.Professor;
import app.models.Turma;
import app.services.AvaliacaoService;
import app.services.TurmaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MarcarAvaliacaoController {
	private Professor user = null;
	private ArrayList<Turma> listaTurmas;

	private Avaliacao a = null;

	private TurmaService ts = null;
	private AvaliacaoService as = null;

	@FXML
	private ChoiceBox<Turma> turmaSelect;

	@FXML
	private ChoiceBox<String> avalicaoSelect;

	@FXML
	private DatePicker dataAvaliacao;

	@FXML
	private Button saveButton;

	/**
	 * Ir� baixar todas as turmas do professor logado e colic�-las numa caixa de sele��o
	 */
	@FXML
	private void initialize() {
		ts = new TurmaService();

		listaTurmas = ts.getTurmasProfessor(user.id);

		for (int i = 0; i < listaTurmas.size(); i++) {
			turmaSelect.getItems().add(listaTurmas.get(i));
		}
		
		avalicaoSelect.getItems().addAll("P1", "P2", "Trabalho");
	}

	/**
	 * Ir� puxar o usu�rio logado
	 * @param prof - Professor logado
	 */
	public void initData(Professor prof) {
		user = prof;
	}

	@FXML
	void getAvaliacao(ActionEvent event) {

	}

	/**
	 * Ir� criar uma nova avalia��o e 
	 * chamar a fun��o que a insere no banco de dados
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void marcarAvaliacao(ActionEvent event) {
		a = new Avaliacao();
		as = new AvaliacaoService();

		Date d = Date.valueOf(dataAvaliacao.getValue());
		int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;
		System.out.println("id turma" + idTurma);
		String descAvaliacao = avalicaoSelect.getSelectionModel().getSelectedItem();

		a.setDescricao(descAvaliacao);
		a.setData(d);

		JOptionPane.showMessageDialog(null, as.marcarAvaliacao(a, idTurma));
	}
}

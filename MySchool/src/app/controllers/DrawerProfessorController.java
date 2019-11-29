package app.controllers;

import java.io.IOException;

import app.models.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DrawerProfessorController {
	private VBox content = null;
	private Label topBarLb = null;
	private Professor user = null;

	/**
	 * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para lançar aula e
     * mudando o título para Lançar Aula
	 * @param event - evento de clique do botão
	 */
	@FXML
	void changePageAula(ActionEvent event) {
		LancarAulaController ac = new LancarAulaController();
		ac.initData(user);
		topBarLb.setText("Lançamento de Aula");
		changePage("lancarAula.fxml", ac);
	}

	/**
	 * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para Marcar Avaliacação e
     * mudando o título para Marcar Avaliacação
	 * @param event - evento de clique do botão
	 */
	@FXML
	void changePageAvaliacao(ActionEvent event) {
		MarcarAvaliacaoController ac = new MarcarAvaliacaoController();
		ac.initData(user);
		topBarLb.setText("Marcar Avaliação");
		changePage("marcarAvaliacao.fxml", ac);
	}

	/**
	 * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para lançar nota e
     * mudando o título para Lançar Nota
	 * @param event
	 */
	@FXML
	void changePageNota(ActionEvent event) {
		LancarNotaController nc = new LancarNotaController();
		nc.initData(user);
		topBarLb.setText("Lançar Nota");
		changePage("lancarNota.fxml", nc);
	}
	
	/**
	 * irá puxar o usuário logado
	 * @param prof - professor que se logou
	 */
	public void initData(Professor prof) {
		user = prof;
	}

	/**
	 * Irá mudar o conteúdo para uma nova página
	 * @param pageName - nome do arquivo fxml
	 * @param controller - controlador da tela a ser mudada
	 */
	public void changePage(String pageName, Object controller) {
		FXMLLoader loader;
		try {
			loader = new FXMLLoader(getClass().getResource("/resources/views/" + pageName));
			loader.setController(controller);
			content.getChildren().clear();
			content.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Irá receber os componentes conteúdo e o título,
     * para mudá-los depois
	 * @param content - conteúdo onde será mostrado as telas
	 * @param topBarLb - título da tela
	 */
	public DrawerProfessorController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}

}

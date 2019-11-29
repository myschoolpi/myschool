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
     * mudando a tela para lan�ar aula e
     * mudando o t�tulo para Lan�ar Aula
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void changePageAula(ActionEvent event) {
		LancarAulaController ac = new LancarAulaController();
		ac.initData(user);
		topBarLb.setText("Lan�amento de Aula");
		changePage("lancarAula.fxml", ac);
	}

	/**
	 * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para Marcar Avaliaca��o e
     * mudando o t�tulo para Marcar Avaliaca��o
	 * @param event - evento de clique do bot�o
	 */
	@FXML
	void changePageAvaliacao(ActionEvent event) {
		MarcarAvaliacaoController ac = new MarcarAvaliacaoController();
		ac.initData(user);
		topBarLb.setText("Marcar Avalia��o");
		changePage("marcarAvaliacao.fxml", ac);
	}

	/**
	 * Puxa o arquivo fxml e configura um controlador para ele,
     * mudando a tela para lan�ar nota e
     * mudando o t�tulo para Lan�ar Nota
	 * @param event
	 */
	@FXML
	void changePageNota(ActionEvent event) {
		LancarNotaController nc = new LancarNotaController();
		nc.initData(user);
		topBarLb.setText("Lan�ar Nota");
		changePage("lancarNota.fxml", nc);
	}
	
	/**
	 * ir� puxar o usu�rio logado
	 * @param prof - professor que se logou
	 */
	public void initData(Professor prof) {
		user = prof;
	}

	/**
	 * Ir� mudar o conte�do para uma nova p�gina
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
	 * Ir� receber os componentes conte�do e o t�tulo,
     * para mud�-los depois
	 * @param content - conte�do onde ser� mostrado as telas
	 * @param topBarLb - t�tulo da tela
	 */
	public DrawerProfessorController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}

}

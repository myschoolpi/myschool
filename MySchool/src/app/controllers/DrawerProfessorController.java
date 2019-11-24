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

	@FXML
	void changePageAula(ActionEvent event) {
		LancarAulaController ac = new LancarAulaController();
		ac.initData(user);
		topBarLb.setText("Cadastrar Aluno");
		changePage("lancarAula.fxml", ac);
	}

	@FXML
	void changePageAvaliacao(ActionEvent event) {

	}

	@FXML
	void changePageNota(ActionEvent event) {

	}
	
	public void initData(Professor prof) {
		user = prof;
	}

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

	public DrawerProfessorController(VBox content, Label topBarLb) {
		this.topBarLb = topBarLb;
		this.content = content;
	}

}

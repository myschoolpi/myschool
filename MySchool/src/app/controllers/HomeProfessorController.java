package app.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;

import app.models.Professor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class HomeProfessorController {
	private Professor user = null;
	
	@FXML
	private Label topBarLb;

	@FXML
	private HBox content;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private VBox page;
	
	/**
	 * Chamada quando carregar o arquivo FXML,
	 * ir� carregar a aba de navega��o
	 */
	@FXML
	private void initialize() {
		FXMLLoader loader;
		VBox box;
		try {
			loader = new FXMLLoader(getClass().getResource("/resources/views/drawerProfessor.fxml"));
			DrawerProfessorController drawerController = new DrawerProfessorController(page, topBarLb);
			drawerController.initData(user);
			loader.setController(drawerController);
			box = loader.load();
			drawer.setSidePane(box);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ir� puxar o usu�rio logado
	 * @param prof - Professor que se logou
	 */
	public void initData(Professor prof) {
		user = prof;
	}

	/**
	 * Chamada Quando clicado no hamburger(bot�o com 3 riscos) abre ou fecha o
	 * menu de navega��o
	 * 
	 * @param event
	 *            - Evento de mouse
	 */
	public void toggleDrawer(MouseEvent event) {
		if (drawer.isOpened())
			drawer.close();
		else
			drawer.open();
	}

}

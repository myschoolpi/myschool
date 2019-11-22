package app.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class HomeProfessorController {
	@FXML
	private Label topBarLb;

	@FXML
	private HBox content;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private VBox page;
	
	/**
	 * Chamada quando carregar o arquivo FXML
	 */
	@FXML
	private void initialize() {
		FXMLLoader loader;
		VBox box;
		try {
			loader = new FXMLLoader(getClass().getResource("/resources/views/drawerProfessor.fxml"));
			DrawerProfessorController drawerController = new DrawerProfessorController(page, topBarLb);
			loader.setController(drawerController);
			box = loader.load();
			drawer.setSidePane(box);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Chamada Quando clicado no hamburger(botão com 3 riscos) abre ou fecha o
	 * menu de navegação
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

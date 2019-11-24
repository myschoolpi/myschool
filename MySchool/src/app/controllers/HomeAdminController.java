package app.controllers;

import java.io.IOException;

import com.jfoenix.controls.*;

import app.models.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class HomeAdminController {
	private Funcionario user = null;

	@FXML
	private HBox content;
	
	@FXML
    private VBox page;

	@FXML
	private Label topBarLb;

	@FXML
	public JFXHamburger hamb;

	@FXML
	public JFXDrawer drawer;

	/**
	 * Chamada quando carregar o arquivo FXML
	 */
	@FXML
	private void initialize() {
		FXMLLoader loader;
		VBox box;
		try {
			loader = new FXMLLoader(getClass().getResource("/resources/views/drawerAdmin.fxml"));
			DrawerAdminController drawerController = new DrawerAdminController(page, topBarLb);
			drawerController.initData(user);
			loader.setController(drawerController);
			box = loader.load();
			drawer.setSidePane(box);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initData(Funcionario f) {
		user = f;
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

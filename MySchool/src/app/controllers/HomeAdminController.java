package app.controllers;

import java.io.IOException;

import com.jfoenix.controls.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class HomeAdminController {
	
	 @FXML
	 private HBox content;
	 
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
	public void initialize() {
		VBox box;
		try {
			box = FXMLLoader.load(getClass().getResource("../../resources/views/drawerAdmin.fxml"));
			drawer.setSidePane(box);
		} catch (IOException e) {
			System.out.println("ERRO DE CARREGAMENTO DE TELA");
		}
	}

	/**
	 * Chamada Quando clicado no hamburger(botão com 3 riscos)
	 * abre ou fecha o menu de navegação
	 * @param event - Evento de mouse
	 */
	public void toggleDrawer(MouseEvent event) {
		if (drawer.isOpened())
			drawer.close();
		else
			drawer.open();
	}
	
	

	

}

package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneController {
	/**
	 * Irá mudar de janela, carregando uma tela nova
	 * @param event - evento de clique do botão
	 * @param screenName - Nome do arquivo da tela a ser mudada
	 * @param controller - Controlador da tela a ser mudada
	 */
	void changeScreen(ActionEvent event, String screenName, Object controller)  {
		
		FXMLLoader loader;
		try {
			loader = new FXMLLoader(getClass().getResource("/resources/views/" + screenName));
			loader.setController(controller);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(new Scene(loader.load(), 800, 600));
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

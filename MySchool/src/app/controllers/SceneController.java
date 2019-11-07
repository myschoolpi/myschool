package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneController {
	void changeScreen(ActionEvent event, String screenName)  {
		
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("../../resources/views/"+screenName));
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(new Scene(parent, 800, 600));
			window.show();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("ERRO AO MUDAR DE TELA");
		}
		
		
	}
}

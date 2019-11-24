package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneController {
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

package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneController {
	void changeScreen(ActionEvent event, String screenName) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("../../resources/views/"+screenName));
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(new Scene(parent, 800, 400));
		window.show();
	}
}

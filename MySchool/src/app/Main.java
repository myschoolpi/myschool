package app;	
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("../resources/views/login.fxml"));
		primaryStage.setTitle("MySchool");
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
}

package mainApp;
import controller.MainViewController;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application
{
    public static void main(String[] args) 
    {
        MainApp.launch(args);
    }

	@Override
    public void start(Stage primaryStage) throws Exception
    {
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/resources/MainView.fxml"));
		Parent root = fxmlloader.load();
		MainViewController controller = fxmlloader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(root);
		scene.setFill(null);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    
}
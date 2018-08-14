package mainApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
		// 根据文件设置位置
		File conf = new File("config.ini");
		if(conf.exists() == true)
		{
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(conf));
				String rawText = reader.readLine();
				String[] splited = rawText.substring(1, rawText.length()-1).split(",");
				double x = Double.valueOf(splited[0]);
				double y = Double.valueOf(splited[1]);
				primaryStage.setX(x);
				primaryStage.setY(y);
				reader.close();
			}
			catch (Exception e)
			{
			}
		}
		primaryStage.show();
	}
    
}
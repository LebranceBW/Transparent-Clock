package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainViewController implements Initializable
{
	@FXML
    private Label timeLabel;
	@FXML
    private ImageView menuBtn, exitBtn;				
	private Stage stage;
	private double xBias, yBias;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// 定时更新时间
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run()
			{
				Platform.runLater(()->timeLabel.setText(new SimpleDateFormat("HH:mm:ss").format(new Date())));
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
//		// 菜单按钮设置移动浮现
//		menuBtn.setOnMouseMoved(e->menuBtn.setTr);
//		menuBtn.setOnMouseExited(e->menuBtn.setVisible(false));
		// 响应拖拽事件
		menuBtn.setOnMouseDragged(e->
		{
			double newX = e.getScreenX() - xBias;
			double newY = e.getScreenY() - yBias;
			stage.setX(newX);
			stage.setY(newY);
		}
		);
		menuBtn.setOnMousePressed(e->
		{
				xBias = e.getScreenX() - stage.getX();
				yBias = e.getScreenY() - stage.getY();
		}
		);
		exitBtn.setOnMouseClicked(e->
		{
			timer.cancel();
			File conf = new File("config.ini");
			try 
			{
				System.out.println(conf.getAbsolutePath());
				if(!conf.exists())
					conf.createNewFile();
				FileWriter fw = new FileWriter(conf, false);
				fw.write(String.format("(%.2f, %.2f)", stage.getX(), stage.getY()));

				fw.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
			Platform.exit();
		}
		);
	}

}

package Application;

import frontEnd.Controllers.PagesLocations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontEnd extends Application
{
	// so that it can be updated from any where
	private static Stage stage;
	private int width = 700;
	private int length = 600;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource(new PagesLocations().welcomePage));
			Scene scene = new Scene(root, width, length);
			stage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("File System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e)
		{
			System.out.println("Exception " + e);
		}
	}

	public void changeScene(String newScene)
	{
		try
		{
			Parent pane = FXMLLoader.load(getClass().getResource(newScene));
			stage.getScene().setRoot(pane);
		} catch (Exception exc)
		{
			System.out.println("Unable to Open File : " + exc.getStackTrace());
		}
	}

	public static void main(String args[])
	{
		launch(args);
	}
}

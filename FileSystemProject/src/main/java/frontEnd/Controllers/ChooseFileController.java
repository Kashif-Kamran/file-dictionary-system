package frontEnd.Controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ChooseFileController extends SuperPage
{
	@FXML
	private Button chooseVocabulary;
	@FXML
	private Button chooseInputFiles;
	@FXML
	private Button addFile;
	@FXML
	private Button loadFiles;
	@FXML
	private TextField vocabularyTxt;


	public void chooseVocabulary(ActionEvent event)
	{
		System.out.println("Choose Vocabulary Called");
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);

	}

	public void chooseInputFiles(ActionEvent event)
	{
		System.out.println("Choose InputFiles Called");
	}

	public void loadFiles(ActionEvent event)
	{
		System.out.println("Load Files Called");
	}
}

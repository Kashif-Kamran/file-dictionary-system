package frontEnd.Controllers;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Application.FrontEndFactory;
import BusinessLayer.FileSystem;
import BusinessLayer.FileSystemFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
	@FXML
	private ListView listView;
	@FXML
	private Label errorLabel;

	// Vocabulary Files
	File vocabularyFile;
	List<File> selectedFiles;

	public void chooseVocabulary(ActionEvent event)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		vocabularyFile = fileChooser.showOpenDialog(null);
		if (vocabularyFile != null)// To avoid null pointer exception
			vocabularyTxt.setText(vocabularyFile.getName());
		else
		{
			vocabularyFile = null;
			vocabularyTxt.setText("");
		}
	}

	public void chooseInputFiles(ActionEvent event)
	{
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		selectedFiles = fc.showOpenMultipleDialog(null);
		if (selectedFiles != null)
		{
			for (int i = 0; i < selectedFiles.size(); i++)
			{
				listView.getItems().add(selectedFiles.get(i).getName());
			}

		} else
		{
			System.out.println("_This is Error_");
		}
	}

	public void clearButtonPressed(ActionEvent event)
	{
		listView.getItems().clear();
	}

	public void loadFiles(ActionEvent event)
	{
		errorLabel.setText("");

		try
		{
			if (vocabularyFile == null)
			{
				errorLabel.setText("Please Select Vocabulary File");
			} else if (selectedFiles == null)
			{
				errorLabel.setText("Please Select Input Files");
			} else
			{
				List<File> inputFilesList = new LinkedList<File>();
				Iterator<File> itr = selectedFiles.iterator();
				while(itr.hasNext())
				{
					inputFilesList.add(itr.next());
				}
				FileSystem system = FileSystemFactory.getInstance(vocabularyFile, inputFilesList)
						.getFileSystem();

				FrontEndFactory.getInstance().getFrontEnd().changeScene(new PagesLocations().manuPage);
			}

		} catch (Exception exc)
		{
			errorLabel.setText(exc.getMessage());
		}
	}
}

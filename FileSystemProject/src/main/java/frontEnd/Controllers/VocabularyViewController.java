package frontEnd.Controllers;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import Application.FrontEnd;
import Application.FrontEndFactory;
import BusinessLayer.FileSystem;
import BusinessLayer.FileSystemFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VocabularyViewController implements Initializable
{
	@FXML
	private ListView<String> wordsList;
	@FXML
	private Label error;
	@FXML
	private Label fileName;
	@FXML
	private Button backButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		try
		{
			FileSystem system = FileSystemFactory.getInstance(null, null).getFileSystem();
			this.fileName.setText(system.getVocabularyFileName());
			Iterator<String> itr = system.getVocabularyBST().iterator();
			while (itr.hasNext())
			{
				wordsList.getItems().add(itr.next());
			}

		} catch (Exception exc)
		{
			error.setText(exc.getMessage());
		}
	}

	public void backButton(ActionEvent event)
	{
		FrontEnd front = FrontEndFactory.getInstance().getFrontEnd();
		front.changeScene(new PagesLocations().manuPage);

	}

}

package frontEnd.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ManuController extends SuperPage
{
	@FXML
	private Button bstButton;
	@FXML
	private Button inputFile;
	@FXML
	private Button exit;
	@FXML
	private Label errorlabel;

	public void VocabularyBST(ActionEvent event)
	{
		this.changeScene(new PagesLocations().VocabularyViewPage);
	}

	public void InputFiles(ActionEvent event)
	{
		this.changeScene(new PagesLocations().inputFileDetails);
	}

	public void exit(ActionEvent event)
	{
		System.exit(0);
	}
}

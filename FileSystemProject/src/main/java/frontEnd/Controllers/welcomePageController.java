package frontEnd.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class welcomePageController extends SuperPage
{
	@FXML
	Button cont = new Button();

	public void continueButtonPressed(ActionEvent event)
	{
		this.changeScene(new PagesLocations().chooseFilePage);

	}
		
}

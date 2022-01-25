package frontEnd.Controllers;

import Application.FrontEnd;
import Application.FrontEndFactory;

public class SuperPage
{
	public void changeScene(String newPagePath)
	{
		// FrontEnd Singleton will be used
		FrontEnd frontEnd = FrontEndFactory.getInstance().getFrontEnd();
		frontEnd.changeScene(new PagesLocations().chooseFilePage);
	}
}

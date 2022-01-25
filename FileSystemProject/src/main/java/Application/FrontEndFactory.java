package Application;

public class FrontEndFactory
{
	FrontEnd frontEnd;
	static FrontEndFactory instance;
	
	private FrontEndFactory()
	{
		frontEnd = new FrontEnd();
	}

	public synchronized static FrontEndFactory getInstance()
	{
		if(instance==null)
		{

			instance = new FrontEndFactory();
		}
		return instance;
	}

	public FrontEnd getFrontEnd()
	{
		return frontEnd;

	}
}

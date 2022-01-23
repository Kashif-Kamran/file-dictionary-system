import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class Vocabulary
{
	TreeSet<String> vocab; // Tree to store data
	FileHandle file; // vocabulary files
	// when the object will be created the files 
	public Vocabulary(String fileName) throws Exception
	{
		super();
		vocab = new TreeSet<String>();
		this.file = new FileHandle(fileName);
		this.populateVocabulary();
	}

	public void populateVocabulary()
	{
		LinkedList<String> wordsList = (LinkedList<String>) file.getWordList();
		Iterator<String> itr = wordsList.iterator();
		while (itr.hasNext())
		{
			vocab.add(itr.next());

		}
	}

	public TreeSet<String> getVocabTree()
	{
		return vocab;
	}

	public boolean searchWord(String str)
	{
		Iterator<String> itr = vocab.iterator();
		while (itr.hasNext())
		{
			if (str.compareToIgnoreCase(itr.next()) == 0)
			{
				return true;
			}
		}
		return false;
	}
// For testing
	public static void main(String[] args)
	{
		try
		{
			Vocabulary vocab = new Vocabulary("./textFiles/test.txt");
			Iterator<String> itr = vocab.getVocabTree().iterator();

			while (itr.hasNext())
			{
				System.out.println(itr.next());
			}
		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}

	}
}
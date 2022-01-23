import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandle extends Thread
{
	String fileName; // File being read
	List<String> wordList; // List of words in file

	// Constructor
	public FileHandle(String fileName) throws Exception
	{
		super();
		this.fileName = fileName;
		wordList = new LinkedList<String>();
		this.checkFile();
		this.loadWordList();

	}



	private void checkFile() throws FileNotFoundException
	{
		if ((new File(fileName).exists()) == false)
		{
			throw new FileNotFoundException(fileName + "  Not Found");
		}
	}

	// Function to load the words of file into wordList
	private void loadWordList()
	{
		File file = new File(this.fileName);
		try
		{
			Scanner reader = new Scanner(file);
			String tempWord;
			while (reader.hasNext())
			{
				tempWord = reader.next();
				tempWord = this.removePollution(tempWord);
				tempWord = tempWord.toLowerCase();
				wordList.add(tempWord);

			}
			reader.close();

		} catch (Exception exc)
		{
			System.out.println(fileName + " Not found");
		}
	}

	// Function to remove the pollution
	private String removePollution(String pollutedWord)
	{
		pollutedWord = pollutedWord.replace(",", "");
		pollutedWord = pollutedWord.replace("?", "");
		pollutedWord = pollutedWord.replace(")", "");
		pollutedWord = pollutedWord.replace("(", "");
		pollutedWord = pollutedWord.replace(":", "");
		pollutedWord = pollutedWord.replace(".", "");
		pollutedWord = pollutedWord.replace(";", "");
		String pollutionFreeWord = pollutedWord;
		return pollutionFreeWord;
	}

	public String getFileName()
	{
		return fileName;
	}

	public List<String> getWordList()
	{
		return wordList;
	}
}

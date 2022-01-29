package BusinessLayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandle extends Thread
{
	File fileToRead; // File being read
	List<String> wordList; // List of words in file

	// Constructor
	public FileHandle(File file) throws Exception
	{
		super();
		this.fileToRead = file;
		wordList = new LinkedList<String>();
		this.checkFile();
		this.loadWordList();
	}

	private void checkFile() throws FileNotFoundException
	{
		if ((fileToRead.exists()) == false)
		{
			throw new FileNotFoundException(fileToRead.getName() + "  Not Found");
		}
	}

	// Function to load the words of file into wordList
	private void loadWordList()
	{

		try
		{
			Scanner reader = new Scanner(fileToRead);
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
			System.out.println(fileToRead.getName() + " Not found");
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
		return fileToRead.getName();
	}

	public String getAbsolutePath()
	{
		return fileToRead.getAbsolutePath();
	}
	public List<String> getWordList()
	{
		return wordList;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandle
{
	String fileName; // File being read
	List<String> wordList; // List of words in file

	// Constructor
	public FileHandle(String fileName) throws Exception
	{
		super();
		this.fileName = fileName;
		wordList = new LinkedList<String>();
		this.loadWordList();
	}

	// Function to load the words of file into wordList
	public void loadWordList() throws Exception
	{
		try
		{
			File file = new File(this.fileName);
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

		} catch (FileNotFoundException exc)
		{
			throw new FileNotFoundException(this.fileName + " File Not Found");
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

	@Override
	public String toString()
	{
		return "FileHandle [fileName=" + fileName + ", wordList=" + wordList + "]";
	}

	public static void main(String[] args)
	{

		try
		{
			FileHandle file = new FileHandle("./textFiles/test.txt");
			LinkedList<String> list = (LinkedList<String>) file.getWordList();
			Iterator<String>itr = list.iterator();
			int i = 1;
			while (itr.hasNext())
			{
				System.out.println(i + " : " + itr.next());
				i++;
			}

		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}
	}
}

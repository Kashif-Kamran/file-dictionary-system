import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandle
{
	String fileName;
	List<String> wordList;

	// Constructor
	public FileHandle(String fileName)
	{
		super();
		this.fileName = fileName;
		wordList=new LinkedList<String>();
	}

	public void loadWordList() throws Exception
	{
		try
		{
			File file= new File(this.fileName);
			Scanner reader = new Scanner(file);
			String tempWord;
			while (reader.hasNext())
			{
				tempWord=reader.next();
				tempWord=this.removePollution(tempWord);
				tempWord=tempWord.toLowerCase();
				wordList.add(tempWord);
				
			}
			reader.close();

		} catch (FileNotFoundException exc)
		{
			throw new FileNotFoundException(this.fileName + " File Not Found");
		}
	}
	
	private String removePollution(String pollutedWord)
	{
		pollutedWord=pollutedWord.replace(",","");
		pollutedWord=pollutedWord.replace("?","");
		pollutedWord=pollutedWord.replace(")","");
		pollutedWord=pollutedWord.replace("(","");
		pollutedWord=pollutedWord.replace(":","");
		pollutedWord=pollutedWord.replace(".","");
		String pollutionFreeWord=pollutedWord;
		return pollutionFreeWord;
	}
	
	public static void main(String[] args)
	{
		FileHandle file = new FileHandle("./textFiles/test.txt");
		try
		{
			file.loadWordList();
			
		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}
	}
}

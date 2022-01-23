package BusinessLayer;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class FileSystem
{
	Vocabulary vocabulary;
	LinkedList<WordsCollection> inputFilesCollection;

	public FileSystem(String vocabularyFileName, List<String> inputFilesNames) throws Exception
	{
		vocabulary = new Vocabulary(vocabularyFileName);
		inputFilesCollection = new LinkedList<WordsCollection>();
		Iterator<String> inputFilesNamesItr = inputFilesNames.iterator();
		while (inputFilesNamesItr.hasNext())
		{
			inputFilesCollection.add(new WordsCollection(inputFilesNamesItr.next(), vocabulary));
		}

	}

	// get Vocabulary Tree Set
	public TreeSet<String> getVocabularyBST()
	{
		return vocabulary.getVocabTree();
	}

	// get Input File Vectors
	public List<String> getInputFileVector(String fileName) throws FileNotFoundException
	{
		Iterator<WordsCollection> itr = inputFilesCollection.iterator();
		while (itr.hasNext())
		{
			FileHandle requiredFile = itr.next().getFileInfo();
			if (requiredFile.getFileName().compareTo(fileName) == 0)
			{
				return requiredFile.getWordList();
			}
		}
		throw new FileNotFoundException("Unable to Get File Vector [ " + fileName + " ] Not Found");

	}

	public List<Word> getFileMatchWords(String fileName) throws FileNotFoundException
	{
		Iterator<WordsCollection> itr = inputFilesCollection.iterator();
		while (itr.hasNext())
		{
			WordsCollection tempCollection = itr.next();
			if (tempCollection.getFileInfo().getFileName().compareTo(fileName) == 0)
			{
				return tempCollection.getMatchedWords();
			}
		}
		throw new FileNotFoundException("Unable To Fild Matched Words  [ " + fileName + " ] Not Found");
	}

	public static void main(String[] args)
	{
		try
		{
			List<String> files = new LinkedList<String>();
			files.add("./textFiles/inputFile.txt");
			FileSystem system = new FileSystem("./textFiles/test.txt", files);
			System.out.println("BST : " + system.getVocabularyBST());
			System.out.println("Vector : " + system.getInputFileVector("./textFiles/inputFile.txt"));
			System.out.println("Match Words: " + system.getFileMatchWords("./textFiles/inputFile.txt"));

		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}

	}

}

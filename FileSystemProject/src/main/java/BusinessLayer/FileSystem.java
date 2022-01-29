package BusinessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FileSystem
{
	Vocabulary vocabulary;
	LinkedList<WordsCollection> inputFilesCollection;
	WordsCollection selectedWordCollection;

// Default Constructor
	public FileSystem(File vocabularyFile, List<File> inputFilesNames) throws Exception
	{
		vocabulary = new Vocabulary(vocabularyFile);
		inputFilesCollection = new LinkedList<WordsCollection>();
		Iterator<File> inputFilesNamesItr = inputFilesNames.iterator();
		while (inputFilesNamesItr.hasNext())
		{
			inputFilesCollection.add(new WordsCollection(inputFilesNamesItr.next(), vocabulary));
		}
	}

	// finds the file with 'fileName' from words Collection and sets its value to
	// local word
	public void setSelectedFile(String fileName) throws FileNotFoundException
	{
		List<WordsCollection> list = inputFilesCollection.stream().filter(x -> x.getFileName().compareTo(fileName) == 0)
				.collect(Collectors.toList());

		if (list != null)
		{

			this.selectedWordCollection = list.get(0);
		}
		else {
			throw new FileNotFoundException("File -> '" + fileName + "' Not Found");
		}
	}


	// Searches and returns input files list
	public List<FileHandle> getInputFiles()
	{
		List<FileHandle> filesList = new LinkedList<FileHandle>();
		Iterator<WordsCollection> itr = inputFilesCollection.iterator();
		while (itr.hasNext())
		{
			filesList.add(itr.next().getFileInfo());
		}
		return filesList;
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

	//
	public List<Word> getFileMatchWords(String fileName) throws FileNotFoundException
	{
		Iterator<WordsCollection> itr = inputFilesCollection.iterator();
		while (itr.hasNext())
		{
			WordsCollection tempCollection = itr.next();
			if (tempCollection.getFileName().compareTo(fileName) == 0)
			{
				return tempCollection.getMatchedWords();
			}
		}
		throw new FileNotFoundException("Unable To Fild Matched Words  [ " + fileName + " ] Not Found");
	}

	public String getVocabularyFileName()
	{
		return vocabulary.getFileName();
	}

	public static void main(String[] args)
	{
		try
		{
			File vocabularyFile = new File("./textFiles/test.txt");
			List<File> files = new LinkedList<File>();
			files.add(new File("./textFiles/inputFile.txt"));
			files.add(new File("./textFiles/inputFile2.txt"));
			FileSystem system = new FileSystem(vocabularyFile, files);
			System.out.println("BST : " + system.getVocabularyBST());
			System.out.println("Vector : " + system.getInputFileVector("inputFile.txt"));
			System.out.println("Match Words: " + system.getFileMatchWords("inputFile.txt"));
		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}

	}
}

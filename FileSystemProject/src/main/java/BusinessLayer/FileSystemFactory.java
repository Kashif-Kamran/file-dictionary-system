package BusinessLayer;

import java.util.LinkedList;
import java.util.List;

public class FileSystemFactory
{

	private FileSystem fileSystem;

	private static FileSystemFactory factory;

	private FileSystemFactory(String vocabFileName, List<String> inputFilesNames) throws Exception
	{
		fileSystem = new FileSystem(vocabFileName, inputFilesNames);
	}

	public static synchronized FileSystemFactory getInstance(String vocabFileName,
			List<String> inputFilesNames) throws Exception
	{
		if (factory == null)
		{
			factory = new FileSystemFactory(vocabFileName, inputFilesNames);
		}
		return factory;
	}

	public FileSystem getFileSystem()
	{
		return this.fileSystem;
	}
	public static void main(String[] args)
	{
		try {
			String vocabFile = "./textFiles/test.txt";
			List<String> fileNames = new LinkedList<String>();
			fileNames.add("./textFiles/inputFile.txt");
			fileNames.add("./textFiles/inputFile2.txt");
			fileNames.add("./textFiles/inputFile3.txt");
			FileSystemFactory factory = FileSystemFactory.getInstance(vocabFile, fileNames);
			FileSystem fact2 = FileSystemFactory.getInstance(null, null).getFileSystem();
			System.out.println(fact2.getFileMatchWords("./textFiles/inputFile.txt"));
		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}

	}

}

package BusinessLayer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileSystemFactory
{

	private FileSystem fileSystem;

	private static FileSystemFactory factory;

	private FileSystemFactory(File vocabFile, List<File> inputFiles) throws Exception
	{
		fileSystem = new FileSystem(vocabFile, inputFiles);
	}

	public static synchronized FileSystemFactory getInstance(File vocabFile, List<File> inputFiles) throws Exception
	{
		if (factory == null)
		{
			factory = new FileSystemFactory(vocabFile, inputFiles);
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
			File vocabFile = new File("./textFiles/test.txt");
			List<File> fileNames = new LinkedList<File>();
			fileNames.add(new File("./textFiles/inputFile.txt"));
			fileNames.add(new File("./textFiles/inputFile2.txt"));
			fileNames.add(new File("./textFiles/inputFile3.txt"));
			FileSystemFactory factory = FileSystemFactory.getInstance(vocabFile, fileNames);
			FileSystem fact2 = FileSystemFactory.getInstance(null, null).getFileSystem();
			System.out.println(fact2.getFileMatchWords("inputFile.txt"));
		} catch (Exception exc)
		{
			System.out.println(exc.getMessage());
		}

	}

}

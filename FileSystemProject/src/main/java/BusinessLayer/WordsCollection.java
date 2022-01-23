package BusinessLayer;
import java.util.Iterator;
import java.util.LinkedList;

public class WordsCollection
{

	LinkedList<Word> matchedWords;
	FileHandle file;

	public WordsCollection(String fileName, Vocabulary vocab) throws Exception
	{
		file = new FileHandle(fileName);
		matchedWords = new LinkedList<Word>();
		this.matchVocabulary(vocab);
	}

	public LinkedList<Word> getMatchedWords()
	{
		return matchedWords;
	}

	public int indexedAt(String str)
	{
		Iterator<Word> itr = matchedWords.iterator();
		int index = 0;
		while (itr.hasNext())
		{
			if (str.compareToIgnoreCase(itr.next().getWord()) == 0)
			{
				return index;
			}
			index++;
		}
		return -1;

	}

	public void matchVocabulary(Vocabulary vocab)
	{
		Iterator<String> itr = this.file.getWordList().iterator();
		while (itr.hasNext())
		{
			String temp = itr.next();
			if (vocab.searchWord(temp))
			{
				int index = this.indexedAt(temp);
				if (index == -1)
				{
					matchedWords.add(new Word(temp));
				} else
				{
					matchedWords.get(index).incrementFreq();

				}

			}
		}
	}

	public FileHandle getFileInfo()
	{
		return file;
	}


}


public class Word
{
	String word;
	Integer freq;
	
	public Word(String word)
	{
		this.word=word;
		freq=1;
	}
	public String getWord()
	{
		return word;
	}

	@Override
	public String toString()
	{
		return "[" + word + ", freq=" + freq + "]";
	}
	public Integer getFreq()
	{
		return freq;
	}
	public void incrementFreq()
	{
		freq++;
	}
	public static void main(String[] args)
	{
		

	}

}

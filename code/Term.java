import java.util.*;
public class Term {

	String word;
	Hashtable synonyms;
	
Term(String str)
{
word=str;
synonyms=WordNetInterface.getSynonyms(str);
}
boolean isPresent(String str)
{
	return synonyms.containsKey(str);
}

}
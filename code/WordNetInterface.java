import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.list.*;
import net.didion.jwnl.dictionary.*;
import net.didion.jwnl.dictionary.morph.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
class WordNetInterface
{
public static Hashtable getSynonyms(String lookUpWord)
{

Hashtable relativeWords = new Hashtable();
try{
JWNL.initialize(new FileInputStream("..\\config\\file_properties.xml"));
PointerType pointerType;
pointerType = PointerType.SIMILAR_TO;
relativeWords.put(lookUpWord, 1);
Dictionary wnDict = Dictionary.getInstance();
if(wnDict == null){
System.out.println("NULL");
}
POS[] posarray=new POS[4];
posarray[0]=POS.ADJECTIVE;
posarray[1]=POS.ADVERB;
posarray[2]=POS.VERB;
posarray[3]=POS.NOUN;
for(int k=0;k<4;k++)
{
try{
IndexWord indexWord = wnDict.getIndexWord(posarray[k], lookUpWord);
			Synset[] synSets = indexWord.getSenses();
for(int i=0; i<synSets.length; i++){

				Pointer[] syn = synSets[i].getPointers();
				for(int j=0; j<syn.length; j++){
				if(syn[j].getType().symmetricTo(pointerType))
						{
							Synset synset =syn[j].getTargetSynset();
							Word synsetWord =synset.getWord(0);
							String word = synsetWord.getLemma();
							word = word.replaceAll("\\(a\\)", "");
							if(!relativeWords.containsKey(word)){
							System.out.println("\n"+word);
							relativeWords.put(word, 1);
							}
						}
					}
				}

					}catch(Exception e)
					{
						continue;
					}
				
			   
			}
}catch(Exception e)
{

//System.out.println(e.toString());
}
return relativeWords;
}
}

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class FactCheckDriver {
	GoogleQuery gq;
	TextProcessor tp;
	static String filteredStmt;
	  
	  FactCheckDriver()
	  {
	      gq=new GoogleQuery();
	      tp=new TextProcessor();
	  }
		 public static boolean test(String stmt) {
	      try {
		GlobalInformation.init();
		FactCheckDriver fct;
	        fct=new FactCheckDriver();
	        fct.gq.getResults(fct.gq.getUrl(stmt));
	        fct.gq.getWebsites();
	        fct.gq.getInformation();
	        fct.tp.filterTags(fct.gq.information);
		    filteredStmt=fct.tp.filterStopWords(stmt);
		    fct.tp.filterInformation();
                
		if(PatternMatcher.verify(fct.tp.filteredInformation,filteredStmt))
		{
		//System.out.println("Fact!");
		GlobalInformation.write("	1\n");
		return true;
		}
		else
		{
		//System.out.println(" Not A Fact!");
		GlobalInformation.write("	0\n");
		return false;
		}
     }catch(Exception e) {
		return true;
	}
	    }    
}

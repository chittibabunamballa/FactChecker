import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
 public class GoogleQuery {
    String results;
    String websites;
    String[] information=null;
    int webCount;
 public String getUrl(String query){

		Pattern p = Pattern.compile("[\\x20]+");
                            Matcher m = p.matcher(query); 
		  return  m.replaceAll("+");
	    }

    public void getResults(String query)
    {
     try
     {
       URL add = new URL("http://ajax.googleapis.com/ajax/services/search/web?start=0&rsz=large&v=1.0&q="+query);
        URLConnection con = add.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            results+=inputLine;
        in.close();
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
 
    }
    public void getWebsites()
    {
      String REGEX="\\bunescapedUrl\\b";
      Pattern p = Pattern.compile(REGEX);
       Matcher m = p.matcher(results); 
        webCount=0;
        websites="";
       while(m.find()&&webCount<5)
   {
         int c=m.end()+2;
         while(results.charAt(++c)!='"')
         websites+=results.charAt(c);
         websites+="\n";
         webCount++;
}  
	GlobalInformation.write("\n\nrelated websites are:\n"+websites);
	}
    public void getInformation()
    {
      information=new String[webCount];
      BufferedReader in,br=new BufferedReader(new StringReader(websites));
      String site;
      URL add;
      URLConnection con;
    for(int i=0;i<webCount;i++)
     information[i]="";
     int i=0;
   try
{
	while((site=br.readLine())!=null)
    {
      
    try
{   
      add=new URL(site);
      con=add.openConnection();
      in = new BufferedReader(new InputStreamReader(con.getInputStream()));
       String inputLine;
        while ((inputLine = in.readLine()) != null) 
        information[i]+=inputLine;
        in.close();
       i++;
}
catch(Exception e)
{
continue;
}        
 }
}
catch(Exception e)
{
}
}

}

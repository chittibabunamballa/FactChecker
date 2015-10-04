import java.io.*;
import java.util.regex.*;
public class Test{
   public static void main(String args[])throws IOException{
	GlobalInformation.init();
	FileReader fr=new FileReader("..\\data\\input.txt");
	BufferedReader br=new BufferedReader(fr);
	String str;
	boolean classes[]=getClasses();
	int i=0,correct=0,total=50;
	double per;
	Pattern p = Pattern.compile("[\\x20]+");
	while((str=br.readLine())!=null){
	   try{
	     if(FactCheckDriver.test(str)==classes[i++]) correct+=1;		
	    }catch(Exception e){
		continue;
	      }
    }
	br.close();
	per=(double)correct*100/total;
	GlobalInformation.write("\n\nPerformance is:"+per);
	 }
public static boolean[] getClasses()throws IOException{
	FileReader fr=new FileReader("..\\data\\in.txt");
	BufferedReader br=new BufferedReader(fr);
	String str;
	boolean[] arr=new boolean[50];
	int i=0;
	Pattern p = Pattern.compile("[\\x20]+");
	while((str=br.readLine())!=null){
	   String[] s=p.split(str);
	   int flag=s[(s.length)-1].codePointAt(0)-48;
	   if(flag==0) arr[i++]=false;
	   else arr[i++]=true;
	  }
	   return arr;
      }
}
			
			
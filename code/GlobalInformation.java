import java.io.*;
public class GlobalInformation {
  public static void init(){
	try {
	FileWriter fw=new FileWriter("..\\data\\output.txt");
	fw.close();
	}catch(Exception e){
     	}
}
  public static void write(String s){
	try {
	FileWriter fw=new FileWriter("..\\data\\output.txt",true);
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(s);
	bw.close();
	}catch(Exception e){
     }
	}
		}

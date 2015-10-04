import java.util.regex.*;
import java.util.*;
class PatternMatcher
{
public static String getPlainText(String text)
   {
			Pattern p = Pattern.compile("[^0-9a-zA-Z]+");
                               	 Matcher m = p.matcher(text); 
                                	 return  m.replaceAll(" ");
  }
		
public static double scoreCalculator(String[] text,String fact)
{
fact=PatternMatcher.getPlainText(fact);
String[] str1=fact.toLowerCase().split(" ");
Term[] cw=new Term[str1.length];
for(int i=0;i<str1.length;i++)
cw[i]=new Term(str1[i]);
double avgscore=0;
int length=0;
for(int k=0;k<text.length;k++)
{
text[k]=PatternMatcher.getPlainText(text[k]);
GlobalInformation.write("\nplain Text obtained from site"+k+"is:"+text[k]);
String[] str2=text[k].toLowerCase().split(" ");
int i=0,pres=0,dist=0,d1=0,d2=0,j;
double score=0;
int temp1=0,temp2=0;
while(true)
{
pres=0;
dist=0;
d1=i;
d2=i;
boolean flag[]=new boolean[str1.length];
for(int l=0;l<str1.length;l++)
flag[l]=false;
for(j=i;j<(i+(3*(str1.length-1)+str1.length))&&j<str2.length;j++)
{
int l;
for(l=0;l<str1.length;l++)
if(cw[l].isPresent(str2[j])&&flag[l]!=true) break;
if(l!=str1.length&&!flag[l])
{
pres+=1;
if(d1!=i) dist+=(j-d1+1);
d1=j+1;
flag[l]=true;
}
if(d1!=i)
{
d2=j;
}
}
if(dist!=0)
{
for(int x=pres;x<str1.length;x++)
dist+=5;
double temp=(double)pres/dist;
if(score<temp)
{
score=temp;
temp1=i;
temp2=j;
}
}
if(j!=(i+(3*(str1.length-1)+str1.length))) break;
i++;
}
GlobalInformation.write("\n\nBest Window is: ");
for(int x=temp1;x<temp2;x++)
{
GlobalInformation.write(str2[x]+" ");
System.out.print(str2[x]+" ");
}
GlobalInformation.write("\n\nscore for website"+k+"is:"+score);
avgscore+=score;
if(score!=0.0) length++;
}
avgscore/=(double)length;
avgscore*=(double)(str1.length-1)/str1.length;
GlobalInformation.write("\n\navgerage score is"+avgscore+"	");
return avgscore;
}
public static boolean verify(String[] text,String fact)
    {   double CUT_OFF=0.5;
        if(PatternMatcher.scoreCalculator(text,fact)<CUT_OFF) return false;
	else return true;
    }
}


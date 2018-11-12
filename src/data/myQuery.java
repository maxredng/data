/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author Maxkarpov
 */
public class myQuery {

    public String[] cols;
    public String tab;
    public String scheme;
    public String[] fields;
    public String[] values;
    public String[] ord;
    public Tab.TabType type;
    
    public int colnum;
    
    public myQuery(String query)
    {
    populate(query);
    
    }
    
    void getFields(String st)
    {

    
    String[] blocks=st.split(" and ");
        fields=new String[blocks.length];
        values=new String[blocks.length];    
    for(int i=0;i<blocks.length;i++)
    {
        String line=blocks[i];


        if(line.split(" or ").length<2)
        {
        String[] pr=line.split("=");
        String[] pm=line.split(">");
        String[] pl=line.split("<");
        String[] isi=line.split(" IS ");
        if(pr.length>1)
        {
        fields[i]=pr[0];
        
        
        values[i]=pr[1].replace("'", "");
        }else
        {
        if(pm.length>1)
        {
            fields[i]=pm[0]+">";
            values[i]=pm[1];
        }else
        {
            if(isi.length<2)
            {
            fields[i]=pl[0]+"<";
            values[i]=pl[1];        
            }else
            {
            fields[i]=isi[0];
            values[i]=isi[1];
            }
        }
        }
        }else
        {
            String tmp=line.replace("(", "").replace(")", "");
            String[] temp=tmp.split(" or ");
            String res="";
            String del="=";
            if(temp[0].split("=").length>1)           
            {
            fields[i]=temp[0].split("=")[0];
            }else
            {
            if(temp[0].split(">").length>1)
            {
            fields[i]=temp[0].split(">")[0]+">";
            
            }else
            {
            fields[i]=temp[0].split("<")[0]+"<";            
          
            }
            }
            for(int j=0;j<temp.length;j++)
            {
String de="=";
                String su=temp[j];
                
                if(su.split(del).length<1)
                {
                if(su.split(">").length>1)
                {
                de=">";
                }else
                {
                de="<";
                }
                }
                
                String fld=su.split(de)[1].replace("'", "");
                if(j<temp.length-1)
                {
                    res=res+fld+":";
                }else
                {
                    res=res+fld;
                
                }
            }
            values[i]=res;
            
        }
    
    }
    
   
    }
    
    
        public boolean match(String ar, int pi)
    {
        boolean result=false;
        
        //String eq="=";
        String ls="<";
        String mr=">";
        
        String rw=values[pi];
            
        String[] ork=rw.split(":");
        
        
        String fld=fields[pi];      

        int l=fld.indexOf("<");
        int m=fld.indexOf(">");



        int olt=ork.length;
     
        for(int i=0;i<olt;i++)
        {
            String ln=ork[i];

            if(fld.contains(mr)&&fld.contains(ls))
            {
            if(ar.equals(ln))
            {
                result=true;
                break;
            }
            }else
            {
            if(fld.indexOf(mr)>-1)
            {
            if(Integer.parseInt(ar)>Integer.parseInt(ln))
            {
                result=true;
                break;
            
            }
            
            }else
            {
            if(fld.indexOf(ls)>-1)
            {
            if(Integer.parseInt(ar)<Integer.parseInt(ln))
            {
                result=true;
                break;
            
            }
            
            }            
            
            
            }
            
            
            }
        }
    
        return result;
    }
    
    final void populate(String qr)
    {
        try {
    Pattern regex = Pattern.compile("select (.*) from (.*) where (.*) order by (.*)");
    Matcher regexMatcher = regex.matcher(qr);
    
    while (regexMatcher.find()) {
        for (int i = 1; i <= regexMatcher.groupCount(); i++) 
        {
         cols=regexMatcher.group(1).split(",");
         String fr=regexMatcher.group(2);
         String f[]=fr.split("\\.");
         scheme=f[0];
         
         try
         {
           
         tab=f[1];
         String vr=regexMatcher.group(3);
         getFields(vr);

       //  String ogr=regexMatcher.group(4);
         ord=regexMatcher.group(4).split(",");
         
         if(tab.equals("col"))
         {
         type=Tab.TabType.col;
         }
         if(tab.equals("config"))
         {
         type=Tab.TabType.config;
         
         }
         if(tab.equals("fact"))
         {
         type=Tab.TabType.fact;
         
         }
         if(tab.equals("img"))
         {
         type=Tab.TabType.img;
         
         }
         if(tab.equals("ppl"))
         {
         type=Tab.TabType.ppl;
         }
         
         if(tab.equals("block"))
         {
         type=Tab.TabType.block;
         
         }
                
         
         
         }catch(Exception exv)
         {
         //skip if thrown;
         }
         

         
        }
       
    } 
} catch (PatternSyntaxException ex) {
    // Syntax error in the regular expression
}
    
    
    }
}

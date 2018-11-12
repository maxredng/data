/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDB;

import data.connection;
import data.data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxkarpov
 */
public class Table {
    
    public static String hom="http://www.theparovoz.ru:8080/corefx/";
    public static String pat;
    public enum tab
    {
    col,config,block,fact,img,terrain,ppl,bablo,vars,quotes,orders
    
    }
    public String name;
    public tab Name;
    public connection cn;
    public Column[] cols;
    public boolean isBin;
    
    public Table(tab tab)
    {
    Name=tab;
    populate();
    }
    
    public String escp(String ln)
    {
    String result="";
    char qu='\'';
    char sl='\\';
        char[] re=ln.toCharArray();
        
        if(ln!=null)
        {
        for(int i=0;i<re.length;i++)
        {
            char tm=re[i];
            if(tm!=qu)
            {
            result=result+tm;
            }else
            {
            result=result+sl+tm;
            
            }
        
        }
        }else
        {
        result=null;
        }
    return result;
    }
    
    
    public String CreateTable(connection con)
    {
        String result="";
        cn=con;
        String query="create table "+con.name+"."+name+"(";
        
        for(int i=0;i<cols.length;i++)
        {
            Column col=cols[i];
            
            if(i<cols.length-1)
            {
                query=query+col.getCode()+", ";
            }else
            {   
                query=query+col.getCode()+")";
            }
        
        
        }
        
        data da=new data(con);
        
        try
        {
        
        result=da.Execute(query);
        
        }catch(Exception ex)
        {
        result=query;
        }
        
        return result;
        
    }
    
    
    
    
    
     public static List<String[]> readFile(String st,String del)
    {
        List<String[]> result=new ArrayList<>();
        String d="|";
        BufferedReader br=null;    
        try
        {
            
            br= new BufferedReader(new FileReader(st)); 
        
        String line;
        
        while((line=br.readLine())!=null)
        {
            
            String[] tmp=line.split(del, 0);
            
            result.add(tmp);
        
        }
        
        
        }catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
        finally
        {
            try
            {
            if(br!=null)
            {
            br.close();
            }
            
            }catch(IOException ex)
            {
            System.out.print(ex.getMessage());
            }
        }
        return result;
    }    
    
     
     
     
     
    public String getInsertQuery(String[] tem)
    {
    String result="";
    
    if(tem.length>0)
    {
    if(tem[0]==null)
    {
        System.out.println("here");
    
    }
    
    }
    
    int len=cols.length;
//    if(tem.length==len)
//    {
    StringBuilder bl=new StringBuilder();
    String st="insert into "+cn.name+"."+name+" values(";
    bl.append(st);
    for(int i=0;i<len;i++)
    {
        String temp=null;
       if(tem.length>i)
       {
        
       temp=cols[i].getInsertCode(escp(tem[i]));
       }
    if(i<len-1)
    {
        bl.append(temp);
        bl.append(",");
    }else
    {
        bl.append(temp);
        bl.append(")");   
    }
    
    }
    result=bl.toString();    
  //  }
//    else
//    {
//    result="error";
//    }
    

    
    
    return result;
    }
    
    
    public String fill()
    {
    String result="";
    
    List<String[]> l=new ArrayList<>();
    
           System.out.println("Working Directory = " +
           System.getProperty("user.dir"));
    String pth=hom+name;
   // result=name+".csv";
    l=readFile(name+".csv", "<delimeter>");
    
    int len=l.size();
    int ln=cols.length;
    
    {
    for(int i=0;i<len;i++)
    {
     String qr=getInsertQuery(l.get(i));
     data da=new data(cn);
              //"./img/"+
              File file=new File("img/"+l.get(i)[0]+".jpg");  
     try
    {
        if(!isBin)
        {
        result=da.Execute(qr);
        }else
        {
//String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
            InputStream stream =new FileInputStream(file);
            
        result=da.ExecuteBin(l.get(i)[0], stream);
        
        
        }
    }catch(Exception ex)
    {
        result=ex.getMessage();
        
    }     
     
     
    }

    }

    

    
    return result;
    }
    
    final void populate()
    {
        name=Name.toString();
        
        switch(Name)
        {
            case col:
            {
            cols=new Column[11];
            cols[0]=new Column("row","VARCHAR(45)");
            cols[1]=new Column("col","VARCHAR(45)");
            cols[2]=new Column("element","VARCHAR(45)");
            cols[3]=new Column("name","VARCHAR(45)");   
            cols[4]=new Column("value","VARCHAR(45)");
            cols[5]=new Column("parent","VARCHAR(45)");
            cols[6]=new Column("scope","VARCHAR(45)");
            cols[7]=new Column("type","VARCHAR(45)");
            cols[8]=new Column("isel","VARCHAR(45)");
            cols[9]=new Column("block","VARCHAR(45)");
            cols[10]=new Column("num","VARCHAR(45)");
            isBin=false;
            break;
            }
            case config:
            {
            cols=new Column[9];
            cols[0]=new Column("isel","VARCHAR(45)",null,"UNI");
            cols[1]=new Column("scema","VARCHAR(45)");
            cols[2]=new Column("table","VARCHAR(45)");
            cols[3]=new Column("col","VARCHAR(45)");   
            cols[4]=new Column("fields","VARCHAR(300)");
            cols[5]=new Column("vals","VARCHAR(300)");
            cols[6]=new Column("colnum","int");
            cols[7]=new Column("ord","VARCHAR(100)");
            cols[8]=new Column("isel2","VARCHAR(45)");
            isBin=false;
            
            break;
            }       
            case vars:
            {
            cols=new Column[2];
            cols[0]=new Column("id","VARCHAR(45)","NO","UNI");
            cols[1]=new Column("value","VARCHAR(100)");

            isBin=false;
            
            break;
            }           
            case block:
            {
            cols=new Column[7];
            cols[0]=new Column("top","int(11)");
            cols[1]=new Column("lef","int(11)");
            cols[2]=new Column("region","VARCHAR(45)");
            cols[3]=new Column("shape","VARCHAR(45)");   
            cols[4]=new Column("orient","VARCHAR(45)");
            cols[5]=new Column("name","VARCHAR(60)");
            cols[6]=new Column("pat","VARCHAR(100)");
            isBin=false;
            
            break;
            }        
        
            case fact:
            {
            cols=new Column[8];
            cols[0]=new Column("id","VARCHAR(45)");
            cols[1]=new Column("cat","VARCHAR(45)");
            cols[2]=new Column("cat_pres","VARCHAR(45)");
            cols[3]=new Column("descr","VARCHAR(500)");   
            cols[4]=new Column("parent","VARCHAR(45)");
            cols[5]=new Column("par_desc","VARCHAR(500)");
            cols[6]=new Column("lan","VARCHAR(45)");
            cols[7]=new Column("als","VARCHAR(45)");
            isBin=false;
            
            break;
            }   
                
            case img:
            {
            cols=new Column[2];
            cols[0]=new Column("id","VARCHAR(45)","NO","PRI");
            cols[1]=new Column("image","blob");
            isBin=true;
            break;
            }
            case terrain:
            {
                
            cols=new Column[3];
            
            cols[0]=new Column("name","VARCHAR(100)");
            cols[1]=new Column("area","VARCHAR(45)");
            cols[2]=new Column("type","VARCHAR(45)");            
            isBin=false;
                
                break;
            }
            case ppl:
            {
            cols=new Column[13];
            cols[0]=new Column("phone","VARCHAR(70)","","PRI");
            cols[1]=new Column("email","VARCHAR(45)");
            cols[2]=new Column("first","VARCHAR(60)");
            cols[3]=new Column("last","VARCHAR(45)");   
            cols[4]=new Column("pass","VARCHAR(45)");
            cols[5]=new Column("city","VARCHAR(45)");
            cols[6]=new Column("region","VARCHAR(100)");
            cols[7]=new Column("street","VARCHAR(100)");
            cols[8]=new Column("bldg","VARCHAR(45)");
            cols[9]=new Column("app","VARCHAR(45)");
            cols[10]=new Column("postcode","VARCHAR(45)");
            cols[11]=new Column("admin","VARCHAR(45)");
            cols[12]=new Column("time","VARCHAR(120)");            
            isBin=false;
            
            break;
            }
            case bablo:
            {
            cols=new Column[3];
            
            cols[0]=new Column("id","VARCHAR(100)","NO","PRI");
            cols[1]=new Column("owner","VARCHAR(45)","NO",null);
            cols[2]=new Column("amout","double");
            isBin=false;
            break;
            }
            case quotes:
            {
            cols=new Column[2];
            
            cols[0]=new Column("id","VARCHAR(45)","NO","PRI");
            cols[1]=new Column("value","VARCHAR(300)");
      
            isBin=false;
            break;
            }    
            case orders:
            {
            cols=new Column[7];
            cols[0]=new Column("orderid","VARCHAR(45)");
            cols[1]=new Column("prodid","VARCHAR(60)");            
            cols[2]=new Column("amount","VARCHAR(45)"); 
            cols[3]=new Column("personid","VARCHAR(45)");
            cols[4]=new Column("address","VARCHAR(500)");
            cols[5]=new Column("status","VARCHAR(45)");  
            cols[6]=new Column("comments","VARCHAR(300)");           
            }

        }
    
    }
}

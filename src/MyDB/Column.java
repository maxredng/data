/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDB;

/**
 *
 * @author maxkarpov
 */
public class Column {
    
    public String Name;
    public String Type;
    public String Nll;
    public String Key;
    public String Value;
    
    public Column(String name,String type)
    {
        Name=name;
        Type=type;
    
    }
    
    public Column(String name,String type,String nll,String key)
    {
        Name=name;
        Type=type;    
        Nll=nll;
        Key=key;
    }
    
    
    public void AddValue(String val)
    {
    Value=val;
    
    }
    public String getInsertCode(String val)
    {
    String result="";
        
    if(val!=null)
    {
        if(Type.contains("VARCHAR"))
        {
            result="'"+val+"'";
        
        }else
        {
        result=val;
        
        }
        
        if(val.equals("NULL")||val.equals(""))
        {
        result=null;
        
        }
    }else
    {
    
    result=null;
    }
    return result;
    }
    
    boolean contains(String[] s, String st)
    {
        boolean result=false;
        int len=s.length;
        for(int i=0;i<len;i++)
        {
            if(s[i].equals(st))
            {
                result=true;
                break;
            }
        
        }
        
        return result;
    
    }
    
    
    public String getCode()
    {
    String result="";
    
    String[] reserve={"table","schema","column"};
    
    if(contains(reserve,Name))
    {
    Name="`"+Name+"`";
    }
    
    result=Name+" "+Type;
    if(Nll!=null)
    {
        result=result+" NOT NULL";
    }
    
    if(Key!=null)
    {
        
        if(Key.equals("PRI"))
        {
        result=result+" PRIMARY KEY";
        }else
        {
            
            
         if(Key.equals("UNI"))
        {
        result=result+" UNIQUE KEY";
        }
       // result=result+" UNIQUE KEY";
        }
        
    }
    
    return result;
    }
    
}

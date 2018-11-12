/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;

/**
 *
 * @author maxkarpov
 */
public class Tab {
    public String[] header;
    public List<String[]> table;        
    
    
    public enum TabType
    {
        col,
        config,
        fact,
        block,
        img,
        ppl,

    }
    
    public String[] getHeader(TabType t)
    {
        String[] result=null;
    
        switch(t)
        {
            case col:
            {
                String[] temp={"row","col","element","name","value","parent","scope","type","isel","block"};
                result=temp;
                break;
            }
            case fact:
            {
            String[] temp={"id","cat","cat_pres","descr","parent","par_desc","lan"};
            
                break;
            }
            case ppl:
            {
            break;
            }
                
                
        
        }
        
        
        return result;
    }
    
    public void pour(String query, connection con,int i)
    {
        data da=new data(con);
        try
        {
        table=da.pullTable(query, i);
        }catch(Exception px)
        {}
    
    }

    public int getCol(String col)
    {
        int result=-1;
    
        String cl=col.replace("<", "");
        cl=cl.replace(">", "");
        
        
        for(int i=0;i<header.length;i++)
        {
            if(header[i].equals(cl))
            {
                result=i;
                break;
            }
        }

        return result;
    }
}

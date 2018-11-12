/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author kisa
 */
public class dtree{
    
    public connection con;
    public List<String> lis;
    public dtree(connection co,String st)
    {
    con=co;
    lis=new ArrayList<String>();
    pop(st);
    }
    
    
    final void pop(String ss)
    {
     data d=new data(con);
    String s="";
    if(!lis.contains(ss)&&ss.length()>0)
    {
    lis.add(ss);
    }
    try
    {
    String query="select distinct parent from prod.tree where id='"+ss+"'";
    s=d.pullString(query);
    }catch(Exception w)
    {
    
    }
    if(s.length()>0)
    {
        if(!lis.contains(s))
        {
    lis.add(s);
        }
    pop(s);
    }
    }
    
    public int getIndex(String st, String sel)
    {
    
    int res=3;
    String query="select distinct parent from prod.tree where id='"+st+"'";
    data da=new data(con);
    String pr="";
    try
    {
    pr=da.pullString(query);
    }catch(Exception w)
    {
    System.out.print(w.fillInStackTrace());
    }
    int g=lis.indexOf(st);
    int tier=lis.size()-lis.indexOf(pr)+1;
   

        if(sel.length()>0)
        {
   res=tier;
        }
    return res;
    }

}

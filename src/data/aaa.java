
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

//import html.feed;

/**
 *
 * @author kisa
 */
public class aaa {
    
    public boolean logged;
    public String error;
    public boolean admin;
    public boolean locked;
    
    public aaa(String user,String pass)
    {
    populate(user,pass);
    
    }
    
    public aaa(String user,String pass,boolean enc)
    {
    
    populate(user,pass,enc);
    }
   public aaa(String user)
   {
   populate(user);
   }
    
   public aaa()
   {
       logged=true;
       admin=false;
   }
   final void populate(String use)
    {
   // feed f=new feed();
    connection con=new connection(MyDB.Schema.schema.prod);
    data da=new data(con);
    String pa="";
    String ac="";
    String adm="";
    
    if(!locked)
    {
    try
    {
   
    String aq="select admin from prod.ppl where email='"+use+"'";
   // String aquery="select active from prod.ppl where email='"+use+"'";
     
    adm=da.pullString(aq);
   // ac=da.pullString(aquery);
    }catch(Exception ex)
    {
    error=ex.getMessage();
    }

        if(adm!=null)
        {
        if(adm.equals("yes"))
        {
        admin=true;
        }
        }
    }else
    {
        logged = false;
        locked = true;
        lock(use);
    }
        
    } 
   
   public void unlock(String us)
   {
       String fq = "delete from prod.fail where email='" + us + "'";
       String lq = "delete from prod.locked where email='" + us + "'";
       
        connection cn = new connection(MyDB.Schema.schema.prod);
        data d = new data(cn);
        
        try
        {
            d.Execute(fq);
            d.Execute(lq);
        }
        catch(Exception x)
        {
            System.out.print(x.getMessage());
        }
   
   }
   
   public void lock(String us)
   {
       String query = "select failed from prod.fail where email='" + us + "'";
       
        connection cn = new connection(MyDB.Schema.schema.prod);
        data d = new data(cn);
        
        try
        {
            int fail = d.pullInt(query);
            int newf = 0;
            String neq = "";
            if(fail>-1)
            {
                newf = fail+1;
                neq = "update prod.fail set failed=" + newf + " where email = '" + us + "'";
            }else
            {
                newf = 0;
                neq = "insert into prod.fail values('" + us + "'," + newf + ")";
            }
            
            d.Execute(neq);
            
            if(newf>3)
            {
                String loq = "insert into prod.locked values('" + us + "')";
                d.Execute(loq);
            }
        
        }catch(Exception x)
        {
            System.out.print(x.getMessage());
        }
   
   }
   
   final boolean locked(String us)
   {
       boolean result = false;
        
       
        String query = "select email from prod.locked where email='"+us+"'";
        connection cn = new connection(MyDB.Schema.schema.prod);
        data d = new data(cn);
        
        try
        {
            String mail = d.pullString(query);
            if(mail!=null)
            {
                if(!mail.isEmpty())
                {
                    result = true;
                }
            }
        }
        catch(Exception x)
        {}
   
       
       return result;
   }
   
   final void populate(String use,String pas,boolean enc)
    {
    connection con=new connection(MyDB.Schema.schema.prod);
    data da=new data(con);
    String pa="";
    String ac="";
    String adm="";
    String password = "";
    
    if(!locked(use))
    {
    try
    {
    String query="select pass from prod.ppl where email='"+use+"'";
    String aq="select admin from prod.ppl where email='"+use+"'";
   // String aquery="select active from prod.ppl where email='"+use+"'";
    pa=da.pullString(query);
    PassEncryptor encr = new PassEncryptor();
    password = encr.decrypt(pa);
    adm=da.pullString(aq);
   // ac=da.pullString(aquery);
    }catch(Exception ex)
    {
    error=ex.getMessage();
    }
        if(password!=null)
        {
        if(pas.equals(password))
        {
        logged=true;
        if(adm!=null)
        {
        if(adm.equals("yes"))
        {
        admin=true;
        }
        }
        }else
        {
        logged=false;
        }
        
        }else
        {
        logged=false;
        }
    }else
    {
        logged = false;
        locked = true;
        lock(use);
    }
    
    
    }
   
    final void populate(String use,String pas)
    {
    String password = "";
    connection con=new connection(MyDB.Schema.schema.prod);
    data da=new data(con);
    String pa="";
    String ac="";
    String adm="";
    
    if(!locked(use))
    {
    try
    {
    String query="select pass from prod.ppl where email='"+use+"'";
    String aq="select admin from prod.ppl where email='"+use+"'";
   // String aquery="select active from prod.ppl where email='"+use+"'";
    pa=da.pullString(query);
    adm=da.pullString(aq);
   // ac=da.pullString(aquery);
    }catch(Exception ex)
    {
    error=ex.getMessage();
    }
        if(pa!=null)
        {
        if(pas.equals(pa))
        {
        logged=true;
        unlock(use);
        if(adm!=null)
        {
        if(adm.equals("yes"))
        {
        admin=true;
        }
        }
        }else
        {
            PassEncryptor encr = new PassEncryptor();
            try
            {
            password = encr.decrypt(pa);
            }catch(Exception wex)
            {}
            
            if(password.equals(pas))
            {
                    logged=true;
                     unlock(use);
        if(adm!=null)
        {
        if(adm.equals("yes"))
        {
        admin=true;
        }
        }
            
            
           }else
            {
            logged=false;
            lock(use);
            }
        
            
        
        }
        
        }else
        {
        logged=false;
        lock(use);
        }
        
    }else
    {
        logged = false;
        locked = true;
        
    }
    }
    
}

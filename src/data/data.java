/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class data {
    
    String q="'";
    char qu='"';
    private String CreateColQuery="CREATE  TABLE `config`.`dumcol` (`row` VARCHAR(45) NOT NULL, `col` VARCHAR(45) NOT NULL,`element` VARCHAR(45) NOT NULL, `name` VARCHAR(45) NOT NULL, `value` VARCHAR(45) NOT NULL, `parent` VARCHAR(45) NOT NULL, `scope` VARCHAR(45) NOT NULL, `type` VARCHAR(45) NOT NULL, `isel` VARCHAR(45) NOT NULL)";
    private String CreateImgTable="CREATE  TABLE `prod`.`img` (`id` VARCHAR(45) NOT NULL UNIQUE, `image` BLOB)";
    private String CreateProRuTable="create table `prod`.`prodru`(`id` VARCHAR(45) NOT NULL, `img` BLOB NULL, `capt` VARCHAR(45) NOT NULL, `desc` VARCHAR(300) NOT NULL, `price` VARCHAR(45) NOT NULL,`term` VARCHAR(120) NOT NULL, `maker` VARCHAR(45) NOT NULL, `seller` VARCHAR(45) NOT NULL,`other` VARCHAR(45) NOT NULL)"; 
    private String CreateTree="create table `prod`.`tree`(`id` VARCHAR(45) NOT NULL, `parent` VARCHAR(45) NOT NULL)";
    private String CreateBlockQuery="create table `prod`.`block`(`top` INT NOT NULL, `left` INT NOT NULL, `region` VARCHAR(45) NOT NULL,`shape` VARCHAR(45) NOT NULL,`orient` VARCHAR(45) NOT NULL)";
    private String CreateVarQuery="create table `config`.`vars`(`id` VARCHAR(45) NOT NULL,`value` VARCHAR(45) NOT NULL)";
    connection con;
    public String error;
    
    public data(connection co)
    {
    
        con=co;
    
    }
    
    public data()
    {
    
    }
    
    public static String parseFrom(String body,String start,String end)
{
String result="";

            try {
    Pattern regex = Pattern.compile(start+"(.*)"+end);
    Matcher regexMatcher = regex.matcher(body);
    
    while (regexMatcher.find()) {
        for (int i = 1; i <= regexMatcher.groupCount(); i++) 
        {
         result=regexMatcher.group(1);
        }
       
    } 
} catch (PatternSyntaxException ex) 
{
    // Syntax error in the regular expression
}
    


return result;

}
    
    
    public Blob pullBlob(String query) throws ClassNotFoundException
    {
    Blob res=null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {
        res=rs.getBlob(1);
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
    
    }
    }
    
    }
    
    return res;
    
    }
  
    public int pullInt(String query) throws ClassNotFoundException
    {
    int res=-1;
    boolean quoted=false;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    String col=parseFrom(query,"select "," from");
    
    if(col.startsWith("'"))
    {
    
    if(col.endsWith("'"))
    {
    quoted=true;
    
    }
    
    }
    
    if(!quoted){
    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
    Properties cp=new Properties();
    cp.put("user",con.user);
    cp.put("password",con.password);
    cp.put("useUnicode","true");
    cp.put("charSet","UTF8");
    
  //  conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, cp);    
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {
        res=rs.getInt(1);
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
    
    }
    }
    
    }
    }else
    {
    
    
    }
    return res;
    }    
    
    public String pullString(String query) throws ClassNotFoundException
    {
    String res="";
    boolean quoted=false;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    String col=parseFrom(query,"select "," from");
    
    if(col.startsWith("'"))
    {
    
    if(col.endsWith("'"))
    {
    quoted=true;
    
    }
    
    }
    
    if(!quoted){
    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
    Properties cp=new Properties();
    cp.put("user",con.user);
    cp.put("password",con.password);
    cp.put("useUnicode","true");
    cp.put("charSet","UTF8");
    
  //  conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, cp);    
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {
        res=rs.getString(1);
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
  //  res = error;
    }
    }
    
    }
    }else
    {
    res=col.substring(1,col.length()-1);
    
    }
    return res;
    }
    
    public List<String[]> pullTable(String query, int cls) throws ClassNotFoundException
    {
    List<String[]> res=new ArrayList<>();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean quoted=false;
    
    String col=parseFrom(query,"select "," from");
    
    if(col.startsWith("'"))
    {
    
    if(col.endsWith("'"))
    {
    quoted=true;
    
    }
    
    }
    
    
    if(!quoted){
    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
        Properties properties=new Properties();
        properties.setProperty("user",con.user);
        properties.setProperty("password",con.password);

        //properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","UTF-8");

    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, properties);
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {
    String[] temp=new String[cls];
    
    for(int i=1;i<cls+1;i++)
    {
    temp[i-1]=rs.getString(i);
    
    }
    res.add(temp);
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
   // System.out.println(error);
    }
    }
    
    }
    }
    else
    {
    String[] rsu={col.substring(1,col.length()-1)};
    res.add(rsu);
    }
    return res;
    }
    
    public List<String> pullList(String query, int cls) throws ClassNotFoundException
    {
    List<String> res=new ArrayList<>();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    String col=parseFrom(query,"select "," from");

    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
        Properties properties=new Properties();
        properties.setProperty("user",con.user);
        properties.setProperty("password",con.password);

        //properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","UTF-8");

    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, properties);
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {

    

    res.add(rs.getString(cls));
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
   // System.out.println(error);
    }
    }
    
    }

    return res;
    }
        
    
public String createSchema () throws ClassNotFoundException
    {
        
        String res="";
         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate("create database "+con.name+" DEFAULT CHARACTER SET utf8 collate utf8_general_ci");
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }    
    
    public String createConfig () throws ClassNotFoundException
    {
        
        String res="";
         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate("create database "+con.name+" DEFAULT CHARACTER SET utf8");
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
    
    public String createProd () throws ClassNotFoundException
    {
        
        String res="";
         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate("create database "+con.name+" DEFAULT CHARACTER SET utf8");
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
    
    public String createTreeTable () throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate(CreateTree);
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
    
    
    public String createConfigTable () throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate(CreateColQuery);
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
 
    public String createProdRuTable () throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/", con.user, con.password);
stmt=conn.createStatement();
stmt.executeUpdate(CreateProRuTable);
res="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
    
        public String Execute (String quer) throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
        Properties properties=new Properties();
        properties.setProperty("user",con.user);
        properties.setProperty("password",con.password);

        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","UTF-8");
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, properties);
stmt=conn.createStatement();
stmt.executeUpdate(quer);
res="success";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
res="failed: "+error;


}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }
 
        
                  public String ExecuteBin (String tit,InputStream fa) throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
stmt=conn.createStatement();
PreparedStatement ps = conn.prepareStatement("insert into "+con.name+".img values(?,?)");


 
ps.setString(1, tit);

ps.setBinaryStream(2, fa,fa.available());
ps.executeUpdate();
res="success";
conn.close();
data dop=new data(vari.cop);
try
{
dop.Execute("insert into prod.auth values('"+tit+"','"+""+"')");
}catch(Exception xe)
{
res="error: failed to add record 2";
}
}catch(Exception ex)
{
    try
    {
       
        Auth au=new Auth(tit,"");
        if(au.isOK){
 PreparedStatement psy=conn.prepareStatement("update prod.img set image=? where id=?");   
 psy.setString(2,tit);

 psy.setBinaryStream(1, fa,fa.available());
 int rows=psy.executeUpdate();
 res="success: "+Integer.toString(rows);
conn.close();
    }
    }catch(Exception nex)
    {
        error=nex.getMessage();
        res="failed: "+error; 
    }



}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }  

          public String ExecuteBin (String tit,File f,String us) throws ClassNotFoundException
    {
        
        String res="";
        

         
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try
{
Class.forName("com.mysql.jdbc.Driver").getClass();
   
try
{
conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
stmt=conn.createStatement();
PreparedStatement ps = conn.prepareStatement("insert into prod.img values(?,?)");


 
ps.setString(1, tit);
InputStream fa=new FileInputStream(f);
ps.setBinaryStream(2, fa,fa.available());
ps.executeUpdate();
res="success";
conn.close();
data dop=new data(vari.cop);
try
{
dop.Execute("insert into prod.auth values('"+tit+"','"+us+"')");
}catch(Exception xe)
{
res="error: failed to add record 2";
}
}catch(Exception ex)
{
    try
    {
       
        Auth au=new Auth(tit,us);
        if(au.isOK){
 PreparedStatement psy=conn.prepareStatement("update prod.img set image=? where id=?");   
 psy.setString(2,tit);
 InputStream fa=new FileInputStream(f);
 psy.setBinaryStream(1, fa,fa.available());
 int rows=psy.executeUpdate();
 res="success: "+Integer.toString(rows);
conn.close();
    }
    }catch(Exception nex)
    {
        error=nex.getMessage();
        res="failed: "+error; 
    }



}


    
}finally
{
  try
  {
      if(conn!=null)
      {
  conn.close();
      }
  }catch(Exception ee)
  {
  error=ee.getMessage();
  }
}


        return res;
    }  
          
              public Date pullTimeStamp(String query) throws ClassNotFoundException
    {
    Date res=new Date();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try
    {
    Class.forName("com.mysql.jdbc.Driver").getClass();
    try
    {
    conn = DriverManager.getConnection("jdbc:mysql://localhost:"+con.port+"/"+con.name, con.user, con.password);
    stmt=conn.createStatement();
    rs=stmt.executeQuery(query);
    
    while(rs.next())
    {
        Date ts=rs.getTimestamp(1);
        res=ts;
    }
        
    }catch(Exception em)
    {
    error=em.getMessage();
    
    }
        
        
    }finally
    {
    if(conn!=null)
    {
    try
    {
    conn.close();
    }catch(Exception ex)
    {
    error="error closing connection: "+ex.getMessage();
    
    }
    }
    
    }
    
    return res;
    }
}



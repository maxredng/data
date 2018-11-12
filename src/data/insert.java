/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kisa
 */
public class insert extends data{
    
    public String status;
    
    public insert(connection con, String[] st, String table)
    {
        super(con);
        try
        {
        pop(st,table);
        }catch(ClassNotFoundException n)
        {
        status=n.getMessage();
        }
    }
    
    
    final String pop(String[] s,String ta)throws ClassNotFoundException
    {
    String re="";
    
    
    String quer="insert into "+ta+" values("+Mhelper.getInsertString(s)+")";
         
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
stmt.executeUpdate(quer);
re="created";
conn.close();
}catch(Exception ex)
{
error=ex.getMessage();
re="failed: "+error;


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


      
    status=re;
    return re;
    }
}

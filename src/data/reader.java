/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class reader {

    public static List<String[]> readTable(String path,char ch)
    {
    List<String[]> re=new ArrayList<>();
    
    File file=new File(path);
    FileInputStream fstr = null;
    BufferedInputStream bstr = null;
    BufferedReader br=null;
            

    try
    {
  fstr=new FileInputStream(file);  
  bstr=new BufferedInputStream(fstr);
  try
  {
  br=new BufferedReader(new InputStreamReader(fstr, "KOI8-R"));
  }catch(Exception ex)
  {
  
  }
  String line;
  while((line=br.readLine())!=null)
  {
  String[] temp=line.split(",");
  re.add(temp);
  
  }
    }catch(Exception ex)
    {
  System.out.println(ex.getMessage());
    }
    
    return re;
    }
    
}

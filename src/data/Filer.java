/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxkarpov
 */
public class Filer {
    
    public static List<String> readFile(String st)
    {
        List<String> result=new ArrayList<String>();
        BufferedReader br=null;    
        try
        {
            br= new BufferedReader(new FileReader(st)); 
        
        String line;
        
        while((line=br.readLine())!=null)
        {
            result.add(line);
        
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

    public static List<String[]> readFile(String st,String del)
    {
        List<String[]> result=new ArrayList<String[]>();
        
        BufferedReader br=null;    
        try
        {
            br= new BufferedReader(new FileReader(st)); 
        
        String line;
        
        while((line=br.readLine())!=null)
        {
            result.add(line.split(del));
        
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
    
}

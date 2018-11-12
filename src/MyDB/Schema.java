/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDB;

import data.connection;
import data.data;


/**
 *
 * @author maxkarpov
 */
public class Schema {
    
    public enum schema
    {
        config,prod,bank,pushkin
    
    }
    
    public schema Name;
    public String name;
    public Table[] tables;
    public connection con;
  //  feed f;
    
    public Schema(schema nm)
    {
    Name=nm;
    populate();
    }
    
    
    public String Create()
    {
        String result="";
    
        String query="create schema if not exists "+name+" character set utf8 collate utf8_general_ci";
        
        data da=new data(con);
        
        try
        {
            result=da.createSchema();
        
        }catch(Exception ex)
        {
        result=ex.getMessage();
        }
        
        return result;
    }
    
    
    
    
    final void populate()
    {
     name=Name.toString();   
 //    f=new feed();
     
     switch(Name)
    {
        case config:
        {
            tables=new Table[3];
            
            tables[0]=new Table(Table.tab.col);
            tables[1]=new Table(Table.tab.config);
            tables[2]=new Table(Table.tab.vars);
             
            
            con=new connection(Name);
            break;
        }
        case prod:
        {
            
            tables=new Table[4];
            
            tables[0]=new Table(Table.tab.fact);
            tables[1]=new Table(Table.tab.img);
            tables[2]=new Table(Table.tab.ppl);  
            tables[3]=new Table(Table.tab.orders);             
            
            //this commented for future projects
            
//            tables=new Table[6];
//            
//            tables[0]=new Table(Table.tab.fact);
//            tables[1]=new Table(Table.tab.img);
//            tables[2]=new Table(Table.tab.terrain);
//            tables[3]=new Table(Table.tab.ppl);  
//            tables[4]=new Table(Table.tab.block);
//            tables[5]=new Table(Table.tab.orders);           
            con=new connection(Name);
            break;
        }
            
        case bank:
        {
        tables=new Table[2];
        
        tables[0]=new Table(Table.tab.bablo);
        tables[1]=new Table(Table.tab.quotes);
        
        con=new connection(Name);
        
        break;
        
        }
    
    }
    
    }
    
}

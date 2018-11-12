package data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




public class connection {
    
    
    public String user;
    public String password;
    public String name;
    public String port;
    public MyDB.Schema.schema sc;
    
    public connection(String us, String pass, String nam, String po)
    {
        user=us;
        password=pass;
        name=nam;
        port=po;
    }
    
    public connection(MyDB.Schema.schema s)
    {
        
    password="topor234";
    user="root";
    port="3306";
    name=s.toString();
    
    
    }
    
    
}

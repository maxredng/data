/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author kisa
 */
public class Auth extends data{
    public String Name;
    public String ID;
    public boolean isOK;
    public Auth(String id,String nam)
    {
        super(vari.cop);
        Name=nam;
        ID=id;
        populate();
        
    }
    
    final void populate()
    {
        String no="";
        try
        {
        no=pullString("select name from prod.auth where id='"+ID+"'");
        }catch(Exception ex)
        {
        error=ex.getLocalizedMessage();
            }
        
        if(no.equals(Name))
        {
        isOK=true;
        }
    }
    
}

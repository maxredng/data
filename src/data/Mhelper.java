/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author kisa
 */
public class Mhelper {
    
    public static String concatenate(String[] st,char ch)
    {
    String re="";
    
    for(int i=0;i<st.length;i++)
    {
    if(i<st.length-1)
    {
    re=re+st[i]+ch;
    
    }else
    {
    re=re+st[i];
    }
    
    }
    
    return re;
    }
    
    public static String getInsertString(String[] st)
        {
    String re="";
    String qu="'";
    char ch=',';
    
    for(int i=0;i<st.length;i++)
    {
    if(i<st.length-1)
    {
    re=re+qu+st[i]+qu+ch;
    
    }else
    {
    re=re+qu+st[i]+qu;
    }
    
    }
    
    return re;
    }
}

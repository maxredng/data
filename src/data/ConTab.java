/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;

/**
 *
 * @author Maxkarpov
 */
public class ConTab extends Tab{
    
    public ConTab(List<String[]> l)
    {
    table=l;
    populate();
    }
    
    public ConTab(){}
    
    final void populate()
    {
    String[] temp={"isel","scema","table","col","fields","vals","colnum","ord","isel2"};
    header=temp;    
    
    }
}

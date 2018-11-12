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
public class FactTab extends Tab{
    
    public FactTab(List<String[]> l)
    {
    table=l;
    populate();
    }
    
    public FactTab()
    {
        populate();
    }
    
    final void populate()
    {
            String[] temp={"id","cat","cat_pres","descr","parent","par_desc","lan"};
            header=temp;
    
    }
    
}

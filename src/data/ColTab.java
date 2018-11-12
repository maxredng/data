/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;

/**
 *
 * @author maxkarpov
 */
public class ColTab extends Tab{
    


    public ColTab(List<String[]> l)
    {
        table=l;
        populate();
    }
    
    public ColTab()
    {
        populate();
    }
    
    final void populate()
    {
    String[] temp={"row","col","element","name","value","parent","scope","type","isel","block"};
    header=temp;
    }
}

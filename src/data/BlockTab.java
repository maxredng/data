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
public class BlockTab extends Tab{
    
    public  BlockTab(List<String[]> l)
    {
        table=l;
        populate();
    }
    
    public BlockTab()
    {
        populate();
    }
    
    final void populate()
    {
        String[] temp={"top","left","region","shape","orient","name","pat"};
        header=temp;
    
    }
}

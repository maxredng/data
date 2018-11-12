/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxkarpov
 */
public class TableModel {
    
    public List<String> columns;
    public MyDB.Table.tab tb;
    
    public TableModel(MyDB.Table.tab t)
    {
    columns=new ArrayList<>();
    tb=t;
    }
    
    public int getColNum(String col)
    {
        int result=-1;
    
            result=columns.indexOf(col);
        
        return result;
    }
    
    
    public void populate()
    {
        switch(tb)
        {
            case col:
            {
                columns.add("row");
                columns.add("col");
                columns.add("element");
                columns.add("name");
                columns.add("value");
                columns.add("parent");
                columns.add("scope");
                columns.add("type");
                columns.add("isel");
                columns.add("block");
                columns.add("num");
            
                break;
            }
            case config:
            {
                columns.add("isel");
                columns.add("scema");
                columns.add("table");
                columns.add("col");
                columns.add("fields");
                columns.add("vals");
                columns.add("colnum");
                columns.add("ord");
                columns.add("isel2");

                break;
            }        
            case fact:
            {
                columns.add("id");
                columns.add("cat");
                columns.add("cat_pres");
                columns.add("descr");
                columns.add("parent");
                columns.add("par_desc");
                columns.add("lan");
                columns.add("als");            
                break;
            }        
        }
    
    
    }
            
    
}

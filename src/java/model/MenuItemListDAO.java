/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import db.access.IDBAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author mashit
 */

public class MenuItemListDAO implements IItemListDAO{
    IDBAccess db;
    private static final String GET_CURRENT_MENU_ITEMS =
            "SELECT id,item_name,item_price FROM menu";

    public MenuItemListDAO(IDBAccess db){
        this.db = db;
    }

    private void openLocalDbConnection() throws DataAccessException{
            
        try{
            db.openConnection(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/restaurant",
                    "root", "");
        } catch (IllegalArgumentException | ClassNotFoundException | SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    
    @Override
    public List<MenuItem> getCurrentItemList() throws DataAccessException{
        List<Map> raw = new ArrayList<>();
        List<MenuItem> items = new ArrayList<>();
        
        this.openLocalDbConnection();
        
        try{
            raw = db.getCurrentRecords(GET_CURRENT_MENU_ITEMS);
            db.closeConnection();
        } catch (SQLException e1) {
            throw new DataAccessException(e1.getMessage(), e1);
        }
        
        MenuItem menuItem = null;

        // Translate List<Map> into List<Employee>
        for (Map m : raw) {
            menuItem = new MenuItem();

            String id = m.get("id").toString();
            menuItem.setId(Integer.valueOf(id));
            String itemName = m.get("item_name").toString();
            menuItem.setName(itemName);
            String itemPrice = m.get("item_price").toString();
            menuItem.setPrice(Double.valueOf(itemPrice));


            items.add(menuItem);
        }

        return items;
        
    }

    @Override
    public void saveItemOrder(List<MenuItem> orderList) throws RuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

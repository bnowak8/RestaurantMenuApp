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
import static model.Consts.*;

/**
 *
 * @author mashit
 */

public class MenuItemListDAO implements IItemListDAO{
    IDBAccess db;
                                

    public MenuItemListDAO(IDBAccess db){
        this.db = db;
    }

    @Override
    public void openDbConnection() throws DataAccessException{
            
        try{
            db.openConnection(DB_DRIVER, DB_URL, DB_USER_NAME, DB_PSWD);
        } catch (IllegalArgumentException | ClassNotFoundException | SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return
     * @throws DataAccessException
     */
    @Override
    public List<MenuItem> getCurrentItemList() throws DataAccessException{
        List<Map> raw = new ArrayList<>();
        List<MenuItem> items = new ArrayList<>();
        
        this.openDbConnection();
        
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

            String id = m.get(ITEM_ID).toString();
            menuItem.setId(Integer.valueOf(id));
            String itemName = m.get(ITEM_NAME).toString();
            menuItem.setName(itemName);
            String itemPrice = m.get(ITEM_PRICE).toString();
            menuItem.setPrice(Double.valueOf(itemPrice));


            items.add(menuItem);
        }
        return items;
    }

    @Override
    public void closeDbConnection() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}

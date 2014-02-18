/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import db.access.IDBAccess;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Consts.*;
/**
 *
 * @author mashit
 */
public class OrderServiceDAO implements IDAO{
    IDBAccess db;
                               
    
    public OrderServiceDAO(IDBAccess db){
        this.db = db;
    }
    
    public void createOrder() throws DataAccessException{
        this.openDbConnection();
        
    }
    
    public void addItemToCurrentOrder(int item_id,int quantity){
        
    }
    
    private String buildAddItemToCurrentOrderSQLStmt(int item_id,int quantity){
        String stmt = ADD_ITEM_TO_CURRENT_ORDER + item_id + COMMA + quantity + 
                      ADD_ITEM_TO_CURRENT_ORDER2;
        return stmt;
    }

    @Override
    public void openDbConnection() throws DataAccessException {
        try{
            db.openConnection(DB_DRIVER, DB_URL, DB_USER_NAME, DB_PSWD);
        } catch (IllegalArgumentException | ClassNotFoundException | SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public void closeDbConnection() throws DataAccessException {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

}

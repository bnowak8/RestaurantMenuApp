/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.access;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mashit
 */
public interface IDBAccess {
    
    
    public abstract void openConnection(String driverClassName, String url, String username, String password) 
	throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;

    public abstract List getCurrentRecords(String sqlString) throws SQLException;
    
    public abstract boolean insertNewRecord() throws SQLException;
    
    
}

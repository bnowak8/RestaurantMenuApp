/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author bNowak8
 */
public interface IDAO {
    public abstract void openDbConnection() throws DataAccessException;
    public abstract void closeDbConnection() throws DataAccessException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;

/**
 *
 * @author mashit
 */
public interface IItemListDAO extends IDAO{
    
    @Override
    public abstract void openDbConnection() throws DataAccessException;
    @Override
    public abstract void closeDbConnection() throws DataAccessException;
    List<MenuItem> getCurrentItemList() throws DataAccessException;
    
}

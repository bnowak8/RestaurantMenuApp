/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import db.access.DBAccess;
import javax.servlet.http.HttpServletRequest;
import static model.Consts.*;

/**
 *
 * @author mashit
 */
public class MenuListService {
    IItemListDAO menuItemsDAO;
    DBAccess db;

    public MenuListService(){
        db = new DBAccess();
        menuItemsDAO = new MenuItemListDAO(db);
    }

    public void getCurrentMenuItemList(HttpServletRequest request) throws DataAccessException{
        request.setAttribute(MENU_LIST, menuItemsDAO.getCurrentItemList());
    }
}

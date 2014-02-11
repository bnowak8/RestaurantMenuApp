/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import db.access.DBAccess;
import db.access.IDBAccess;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mashit
 */
public class MenuOrderService {
    IItemListDAO menuItemsDAO;
    IDBAccess db;

    public MenuOrderService(){
        db = new DBAccess();
        menuItemsDAO = new MenuItemListDAO(db);
    }

    public void getCurrentMenuItemList(HttpServletRequest request) throws DataAccessException{
        request.setAttribute("menuList", menuItemsDAO.getCurrentItemList());
    }

}

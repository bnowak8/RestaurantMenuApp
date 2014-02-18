/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author mashit
 */
public class Consts {
    public static final String 
           //Order Service DAO
           CREATE_NEW_ORDER = "INSERT INTO restaurant.order VALUES();",
           ADD_ITEM_TO_CURRENT_ORDER="INSERT INTO restaurant.order_item VALUES(null,(SELECT MAX(order_id) FROM restaurant.order),",
           ADD_ITEM_TO_CURRENT_ORDER2=");\n" + "UPDATE restaurant.order SET order_total = order_total +\n" +
           "(SELECT item_price FROM restaurant.menu_item WHERE item_id = \n" +
           "(SELECT item_id FROM restaurant.order_item WHERE order_id = \n" +
           "(SELECT MAX(order_id) FROM restaurant.order_item) AND id = \n" +
           "(SELECT MAX(id) FROM restaurant.order_item)))\n" +
           "* \n" +
           "(SELECT item_quantity FROM restaurant.order_item WHERE order_id = \n" +
           "(SELECT MAX(order_id) FROM restaurant.order_item) AND order_id = \n" +
           "(SELECT MAX(order_id) FROM restaurant.order_item) AND id = \n" +
           "(SELECT MAX(id) FROM restaurant.order_item))\n" +
           "WHERE order.order_id = (SELECT MAX(order_id) FROM restaurant.order_item);",
           SUBMIT_ORDER="",
           COMMA=",",
            
            //Menu List DAO Constants
            GET_CURRENT_MENU_ITEMS = "SELECT item_id,item_name,item_price FROM menu_item",
            ITEM_ID = "item_id",
            ITEM_NAME = "item_name",
            ITEM_PRICE = "item_price",
            
            //Database Connection Constants
            DB_DRIVER = "com.mysql.jdbc.Driver",
            DB_URL = "jdbc:mysql://localhost:3306/restaurant",
            DB_USER_NAME = "root",
            DB_PSWD = "admin",
            
            //Menu List Service Constants
            MENU_LIST = "menuList"
            ;
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.access;

import java.util.*;
import java.sql.*;

/**
 *
 * @author mashit
 */
public class DBAccess implements IDBAccess{
    private Connection conn;
    
    @Override
    public void openConnection(String driverClassName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException {
        String msg = "Error: url is null or zero length!";
        if( url == null || url.length() == 0 ) throw new IllegalArgumentException(msg);
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        Class.forName (driverClassName);
        conn = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    @Override
    public List getCurrentRecords(String sqlString) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final List list=new ArrayList();
        Map record = null;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlString);
                metaData = rs.getMetaData();
                final int fields=metaData.getColumnCount();

                while( rs.next() ) {
                        record = new HashMap();
                        for( int i=1; i <= fields; i++ ) {
                                try {
                                        record.put( metaData.getColumnName(i), rs.getObject(i) );
                                } catch(NullPointerException npe) { 
                                        // no need to do anything... if it fails, just ignore it and continue
                                }
                        } // end for
                        list.add(record);
                } // end while

        } catch (SQLException sqle) {
                throw sqle;
        } finally {
                try {
                        stmt.close();
                        conn.close();
                } catch(SQLException e) {
                        throw e;
                } // end try
        } // end finally

        return list;
    }

    @Override
    public boolean insertNewRecord(int item_id, int quantity) throws SQLException {
        Statement stmt = null;
        
        try{
        stmt = conn.createStatement();
        stmt.executeUpdate(buildInsertStmt(item_id,quantity));
        }catch(SQLException e){
            return false;
           
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                throw e;
            }
        }
        
        return true;
        
        
    }
    
    private String buildInsertStmt(int item_id, int quantity){
        String stmt = null;
        stmt += "INSERT INTO restaurant.order VALUES();\n" +
"INSERT INTO restaurant.order_item VALUES(null,(SELECT MAX(order_id) FROM restaurant.order)," + item_id + "," + quantity + ");";
        
        stmt += "UPDATE restaurant.order SET order_total = \n" +
"	(SELECT item_price FROM restaurant.menu_item WHERE menu_item.item_id = \n" +
"		(SELECT item_id FROM restaurant.order_item WHERE order_item.order_id = \n" +
"			(SELECT MAX(order_id) FROM restaurant.order_item))) \n" +
"	* \n" +
"	(SELECT item_quantity FROM restaurant.order_item WHERE order_item.order_id = \n" +
"		(SELECT MAX(order_id) FROM restaurant.order_item)) WHERE order.order_id = \n" +
"			(SELECT MAX(order_id) FROM restaurant.order_item);";
        return stmt;
        
    }
    
}

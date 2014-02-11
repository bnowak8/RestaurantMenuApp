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
        } catch (Exception e) {
                throw e;
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
    public boolean insertNewRecord() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws Exception {
            DBAccess db = new DBAccess();
            db.openConnection("com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/restaurant", 
                    "root", "");
            
            List records = db.getCurrentRecords("select id, item_name, item_price from menu");
            System.out.println(records);
        }
}

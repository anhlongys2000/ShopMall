/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class DBUtil {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection cn =null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databasename=ShoppingMall";
        cn = DriverManager.getConnection(url,"sa","1234567");
        return cn;
    }
}

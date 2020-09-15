package com.xupt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        String url = "jdbc:mysql:///novel?serverTimezone=UTC";
        String  user = "root";
        String password = "d2430414211";
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return conn;
    }

}

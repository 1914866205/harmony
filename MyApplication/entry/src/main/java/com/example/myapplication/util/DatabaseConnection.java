package com.example.myapplication.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String DBDRIVER = "com.mysql.jdbc.Driver";
    private static String DBURL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
    private static String DBUSER = "root";
    private static String DBPASSWORD = "root";

    private Connection conn = null;


    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBDRIVER);
        System.out.println(this.conn);
        this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);//
        System.out.println(this.conn);

    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        }
        System.out.println(this.conn);
        return this.conn;
    }

    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

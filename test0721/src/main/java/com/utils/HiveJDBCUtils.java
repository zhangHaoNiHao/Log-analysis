package com.utils;

import java.sql.*;

public class HiveJDBCUtils {
    public static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String Url="jdbc:hive2://192.168.120.100:10000/default";

    private static Connection conn;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public static Connection getConnection(){

        try
        {
            Class.forName(driverName);
            conn = DriverManager.getConnection(Url,"root","123456");
        }
        catch(ClassNotFoundException e)  {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs) throws SQLException
    {
        if ( rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}

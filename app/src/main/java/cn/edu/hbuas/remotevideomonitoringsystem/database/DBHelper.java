package cn.edu.hbuas.remotevideomonitoringsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hbuas.remotevideomonitoringsystem.bean.Constant;

public class DBHelper {

    //MySQL 驱动
    private static String driver_class = "com.mysql.jdbc.Driver";

    //MYSQL数据库连接Url   192.168.43.163  192.168.1.61   192.168.1.19
    private static String driver_url = "jdbc:mysql://" + Constant.URL + "/intelligent_video_surveillance?useSSL=false&serverTimezone=GMT";

    //用户名
    private static String database_user = "root";
    //密码  qpt880408he
    private static String database_password = "qpt880408he";

    private static Connection conn = null;

    /**
     * 连接数据库
     */

    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName(driver_class);//获取MYSQL驱动
                System.out.println("驱动加载成功");
                conn = DriverManager.getConnection(driver_url, database_user, database_password);//获取连接
                System.out.println("conn:" + conn.toString());
                System.out.println("连接成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 关闭数据库
     */

    public static void closeAll(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库
     */

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
package cn.edu.hbuas.remotevideomonitoringsystem.database;

import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.hbuas.remotevideomonitoringsystem.bean.Car;

public class DBService {

    private Connection conn = null; //打开数据库对象
    private PreparedStatement ps = null;//操作整合sql语句的对象
    private ResultSet rs = null;//查询结果的集合

    //DBService 对象
    public static DBService dbService = null;

    /**
     * 构造方法 私有化
     */

    private DBService() {

    }

    /**
     * 获取MySQL数据库单例类对象
     */

    public static DBService getDbService() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }


    /**
     * 查
     */

    public List<Car> getUserData() {
        //结果存放集合
        List<Car> list = new ArrayList<Car>();
        //MySQL 语句
        String sql = "select * from daily_update_table";
        //获取链接数据库对象
        conn = DBHelper.getConn();
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = conn.prepareStatement(sql);
                if (ps != null) {
                    rs = ps.executeQuery();
                    if (rs != null) {
                        while (rs.next()) {
                            Car c = new Car();
                            c.setPlate_number(rs.getString(1));
                            c.setName(rs.getString(2));
                            c.setMessage(rs.getString(3));
                            c.setCreateTime(rs.getString(4));
                            list.add(c);
                        }
                    }
                }
            }
//        System.out.println("连接关闭");
//        DBHelper.closeAll(conn, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 修改数据库中某个对象的状态   改
     */

    public int updateUserData(String plate_numbers) {
        int result = -1;
        if (!StringUtils.isNullOrEmpty(plate_numbers)) {
            //获取链接数据库对象
            conn = DBHelper.getConn();
            //MySQL 语句
            String sql = "update daily_update_table set name=?,CreateTime=? where plate_numbers=?";
            try {
                boolean closed = conn.isClosed();
                if (conn != null && (!closed)) {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, "1");//第一个参数state 一定要和上面SQL语句字段顺序一致
                    ps.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    ps.setString(3, plate_numbers);//第二个参数 phone 一定要和上面SQL语句字段顺序一致
                    result = ps.executeUpdate();//返回1 执行成功
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBHelper.closeAll(conn, ps);//关闭相关操作
        return result;
    }

    /**
     * 批量向数据库插入数据   增
     */

    public int insertUserData(List<Car> list) {
        int result = -1;
        if ((list != null) && (list.size() > 0)) {
            //获取链接数据库对象
            conn = DBHelper.getConn();
            //MySQL 语句
            String sql = "INSERT INTO daily_update_table VALUES (?,?,?,?)";
            try {
                boolean closed = conn.isClosed();
                if ((conn != null) && (!closed)) {
                    for (Car car : list) {
                        ps = conn.prepareStatement(sql);
                        String plate_numbers = car.getPlate_number();
                        String name = car.getName();
                        String message = car.getMessage();
                        String createTime = car.getCreateTime();
                        ps.setString(1, plate_numbers);//第一个参数 name 规则同上
                        ps.setString(2, name);//第二个参数 phone 规则同上
                        ps.setString(3, message);//第三个参数 content 规则同上
                        ps.setString(4, createTime);//第四个参数 state 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBHelper.closeAll(conn, ps);//关闭相关操作
        return result;
    }


    /**
     * 删除数据  删
     */

    public int delUserData(String plate_numbers) {
        int result = -1;
        if (!StringUtils.isNullOrEmpty(plate_numbers)) {
            //获取链接数据库对象
            conn = DBHelper.getConn();
            //MySQL 语句
            String sql = "delete from daily_update_table where plate_numbers=?";
            try {
                boolean closed = conn.isClosed();
                if ((conn != null) && (!closed)) {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, plate_numbers);
                    result = ps.executeUpdate();//返回1 执行成功
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBHelper.closeAll(conn, ps);//关闭相关操作
        return result;
    }

}


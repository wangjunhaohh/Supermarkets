package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcConn {
    private static final String driverlassName="";
    private static final String url="";
    private static final String username="";
    private static final String password="";
    //定义SQLServer数据库驱动程序
    private static   DataSource dataSource;

    static {

        DruidDataSourceFactory factory = new DruidDataSourceFactory(); //实例化Druid工厂类
        Properties pro = new Properties();
        InputStream in = jdbcConn.class.getClassLoader().getResourceAsStream("dbConn.properties");// 使用ClassLoader加载properties配置文件生成对应的输入流

        try {
            pro.load(in);// 使用properties对象加载输入流
            dataSource = factory.createDataSource(pro);//创建数据源
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {// 建立数据库连接
        Connection conn = null;

        try {

            conn = dataSource.getConnection();//创建数据库连接
        } catch (Exception e) {   e.printStackTrace();    }
        return conn;
    }
    public  static void close(ResultSet rs, Statement stmt, Connection conn) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  static void close(PreparedStatement pstmt, Connection conn){
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  static void close(Statement stmt, Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}

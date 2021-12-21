package Images;

import bean.image;
import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.jdbcConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class imageDao {
    public static void add(String time,String path){
        Connection conn= jdbcConn.getConnection();
        String sql="insert into image(time,photo) value(?,?)";
        QueryRunner runner=new QueryRunner();
        Object o[]=new Object[]{time,path};
        try {
            runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static List<image> query(){
        Connection conn=jdbcConn.getConnection();
        String sql="SELECT * FROM image ORDER  BY id DESC";
        QueryRunner runner=new QueryRunner();
        List<image> list=new ArrayList<>();
        try {
            list=runner.query(conn,sql,new BeanListHandler<image>(image.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  list;
    }
    public static int selectCount(){
        int count=0;
        String sql="select count(*) from image";
        Connection conn=jdbcConn.getConnection();
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                count=rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(selectCount());
    }
}

package home;

import bean.*;
import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import util.jdbcConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sqlD {
    public static int addGood(Good good, Connection conn){
        QueryRunner runner=new QueryRunner();
        String sql="insert  into good (id,Goods,Num,Time,Supplier,Photo) value(?,?,?,?,?,?)";
        Object [] o=new Object[]{good.getId(),good.getGoods(),good.getNum(),good.getTime(),good.getSupplier(),good.getPhoto()};
        int count= 0;
        try {
            count = runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public static int addGood(Good good){
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner=new QueryRunner();
        String sql="insert  into good (id,Goods,Num,Time,Supplier,Photo) value(?,?,?,?,?,?)";
        Object [] o=new Object[]{good.getId(),good.getGoods(),good.getNum(),good.getTime(),good.getSupplier(),good.getPhoto()};
        int count= 0;
        try {
            count = runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public static void addGood(int n){
        int count=0;
        Connection connection=jdbcConn.getConnection();
        while (count<n){
            Good good=new Good();
            good.setId(Rand.getId());
            good.setGoods(Rand.getGoods());
            good.setNum(Rand.Num());
            good.setTime(Rand.getTime());
            good.setSupplier(Rand.getSupplier());
            good.setPhoto(Rand.getPhoto());
            addGood(good,connection);
            count++;
        }
    }
    //按id返回商品数据
    public static Good findById(String id) throws SQLException {
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from good where id=?";//定义查询语句
        Good good = runner.query(conn, sql, new BeanHandler<Good>(Good.class), id);//将查询结果以实体类Pet返回
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return good;
    }
    //按用户名返回单个用户数据
    public static Administrator findByUname(String uname) throws SQLException {
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from administrator where username=?";//定义查询语句
        Administrator administrator = runner.query(conn, sql, new BeanHandler<Administrator>(Administrator.class), uname);//将查询结果以实体类Pet返回
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return administrator;
    }
    //按条件产生商品数据集合
    public static List<Good> findQuerry(String condition) throws SQLException {
        List<Good> good = new ArrayList<Good>();
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from good where "+condition;//定义查询语句
        good = runner.query(conn, sql, new BeanListHandler<Good>(Good.class));
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return good;
    }
    public static List<List<Student>> query(){
        Connection conn= jdbcConn.getConnection();
        String sql="select * from Student";
        List<List<Student>> rows=new ArrayList<>();
        QueryRunner runner=new QueryRunner();

        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
                List<Student> row=new ArrayList<>();
                row.add(new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),
                        rs.getString(4)));
                rows.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  rows;
    }
    //按商品id删除单行记录
    public static void deleteGood(String id) {
        Connection conn= jdbcConn.getConnection();
        String sql="delete  from good where id=?";
        QueryRunner qr=new QueryRunner();
        try {
            qr.update(conn,sql,id);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //按商品id删除多行记录
    public static int delGood(String id) throws SQLException {
        int count = 0;
        Connection conn = jdbcConn.getConnection();
        String sql = "delete from good where id in (" + id + ")";
        QueryRunner runner = new QueryRunner();
        count = runner.update(conn, sql);
        conn.close();
        return count;
    }
    //修改仓库数据
    public static int updateGood(Good good) throws SQLException {
        int count = 0;
        Connection conn = jdbcConn.getConnection();
        String sql = "update good set Goods=?,Num=?,Time=?,Supplier=?,Photo=? where id=?";
        Object[] stutmp = new Object[]{good.getGoods(),good.getNum(),good.getTime(),
                good.getSupplier(),good.getPhoto(),good.getId()};
        QueryRunner runner = new QueryRunner();
        count = runner.update(conn, sql, stutmp);
        return count;
    }
    //添加出售记录
    public static int addSales(Sales sales){
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner=new QueryRunner();
        String sql="insert  into sales_record (id,Goods,Num,OutTime,Supplier,Photo,TotalPrice) value(?,?,?,?,?,?,?)";
        Object [] o=new Object[]{sales.getId(),sales.getGoods(),sales.getNum(),sales.getOutTime(),sales.getSupplier(),sales.getPhoto(),sales.getTotalPrice()};
        int count= 0;
        try {
            count = runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    //添加商品单价
    public static int addinformanation(){
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner=new QueryRunner();
        String sql="INSERT into product_information(id) SELECT id from good";
        int count=0;
        try {
            count=runner.execute(conn,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql2="UPDATE product_information SET monovalent =FLOOR(RAND()*100+60)";
        try {
            runner.execute(conn,sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    //按id返回商品单价
    public static int Price(String id){
        Connection conn=jdbcConn.getConnection();
        String sql="SELECT monovalent FROM product_information WHERE id=?";
        QueryRunner runner=new QueryRunner();
        Object o[]=null;
        try {
            o=runner.query(conn,sql,new ArrayHandler(),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String str=JSON.toJSONString(o);
        String s=str.replace("]","");
        String s2=s.replace("[","");
        int price=Integer.parseInt(s2);
        return price;
    }
    //添加用户数据
    public static int addAdministrator(Administrator administrator, Connection conn){
        QueryRunner runner=new QueryRunner();
        String sql="insert  ignore into administrator  (username,password) value(?,?)";
        Object [] o=new Object[]{administrator.getUsername(),administrator.getPassword()};
        int count= 0;
        try {
            count = runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    //返回所有用户数据集合
    public static List<Administrator> findQuerryAdminstratot(String condition) throws SQLException {
        List<Administrator> administrators = new ArrayList<Administrator>();
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from administrator where "+condition;//定义查询语句
        administrators = runner.query(conn, sql, new BeanListHandler<Administrator>(Administrator.class));
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return administrators;
    }
    public static Object countGoods(){
        Connection conn=jdbcConn.getConnection();
        String sql="SELECT COUNT(id) FROM good ";
        QueryRunner runner=new QueryRunner();
        Object i=0;
        try {
            i=runner.query(conn,sql,new ScalarHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
    public static String countGoodsNum(){
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner=new QueryRunner();
        String sql="select SUM(case when Goods='猪肉' then 1 else 0 end) as 猪肉,\n" +
                "\t\t\t SUM(case when Goods='纸' then 1 else 0 end) as 纸,\n" +
                "\t\t\tSUM(case when Goods='垫子' then 1 else 0 end) as 垫子,\n" +
                "\t\t\tSUM(case when Goods='奶粉' then 1 else 0 end) as 奶粉,\n" +
                "\t\t\tSUM(case when Goods='可乐' then 1 else 0 end) as 可乐,\n" +
                "\t\t\tSUM(case when Goods='棉被' then 1 else 0 end) as 棉被,\n" +
                "\t\t\tSUM(case when Goods='床' then 1 else 0 end) as 床,\n" +
                "\t\t\tSUM(case when Goods='面包' then 1 else 0 end) as 面包,\n" +
                "\t\t\tSUM(case when Goods='苹果' then 1 else 0 end) as 苹果,\n" +
                "\t\t\tSUM(case when Goods='书' then 1 else 0 end) as 书,\n" +
                "\t\t\tSUM(case when Goods='西瓜' then 1 else 0 end) as 西瓜,\n" +
                "\t\t\tSUM(case when Goods='梨子' then 1 else 0 end) as 梨子,\n" +
                "\t\t\tSUM(case when Goods='手机' then 1 else 0 end) as 手机,\n" +
                "\t\t\tSUM(case when Goods='笔' then 1 else 0 end) as 笔,\n" +
                "\t\t\tSUM(case when Goods='电视' then 1 else 0 end) as 电视,\n" +
                "\t\t\tSUM(case when Goods='五花肉' then 1 else 0 end) as 五花肉,\n" +
                "\t\t\tSUM(case when Goods='杯子' then 1 else 0 end) as 杯子\n" +
                "FROM good\n";
        Object [] list= new Object[0];
        try {
            list = runner.query(conn,sql,new ArrayHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String str= JSON.toJSONString(list);
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }
    public static String countGoodsName(){
        Connection conn=jdbcConn.getConnection();
        String sql="select Goods FROM good GROUP BY Goods";
        QueryRunner runner=new QueryRunner();
        List list=new ArrayList();
        try {
            list=runner.query(conn,sql,new ArrayListHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json=JSON.toJSONString(list);
        String s=json.replace("]","");
        String s2=s.replace("[","");
        String s3=s2.replace("\"","'");
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "["+s3+"]";
    }
    public static String countGoodsNum2(){
        Connection conn=jdbcConn.getConnection();
        String sql="select SUM(Num)/10000 FROM good GROUP BY Goods";
        QueryRunner runner=new QueryRunner();
        List list = new ArrayList();
        try {
            list = runner.query(conn,sql,new ArrayListHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String json=JSON.toJSONString(list);
        String s=json.replace("]","");
        String s2=s.replace("[","");
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "["+s2+"]";
    }
    public static Object countUser(){
        Connection conn=jdbcConn.getConnection();
        String sql="SELECT COUNT(username) FROM administrator ";
        QueryRunner runner=new QueryRunner();
        Object i=0;
        try {
            i=runner.query(conn,sql,new ScalarHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  i;
    }
    //提交购物单产生的修改good数据
    public static void updGoods(int num,String id){
        Connection conn=jdbcConn.getConnection();
        String sql="UPDATE good set Num=Num-? WHERE id =?";
        QueryRunner runner=new QueryRunner();
        Object []o=new Object[]{num,id};
        try {
            runner.update(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int addPerson(Person person){
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner=new QueryRunner();
        String sql="insert  into person (id,name,startTime,endTime,reason,Photo) value(?,?,?,?,?,?)";
        Object [] o=new Object[]{person.getId(),person.getName(),person.getStartTime(),person.getEndTime(),person.getReason(),person.getPhoto()};
        int count= 0;
        try {
            count = runner.execute(conn,sql,o);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public static List<Person> findPerson(String condition) throws SQLException {
        List<Person> people = new ArrayList<Person>();
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from person where "+condition;//定义查询语句
        people = runner.query(conn, sql, new BeanListHandler<Person>(Person.class));
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return people;
    }
    public static void deletePeople(String id) {
        Connection conn= jdbcConn.getConnection();
        String sql="delete  from person where id=?";
        QueryRunner qr=new QueryRunner();
        try {
            qr.update(conn,sql,id);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
//        addinformanation();
//        Connection conn=jdbcConn.getConnection();
//        int count=0;
//        while (count<1000){
//            Administrator administrator=new Administrator();
//            administrator.setUsername(Rand.getStrings(5,15));
//            administrator.setPassword(Rand.getStrings(3,9));
//            addAdministrator(administrator,conn);
//            count++;
//        }
//        conn.close();
//        System.out.println(count);
//        List<Administrator> slist=sqlD.findQuerryAdminstratot("1=1");
//        for(Administrator s:slist){
//            System.out.println(s.getUsername());
//        }

        System.out.println(Price("2345928"));
    }
}

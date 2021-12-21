package home2;

import bean.Good;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.jdbcConn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/Login1")
public class Login1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");//设置响应的编码
        String uname = req.getParameter("uname");//获取输入的用户名
        Good userPar = new Good();//实例化用户实体类
        Good userResult=null;
        try {
            userResult= Login1.findByKey(uname);//匹配用户名或学号与密码
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (userResult!=null) {//匹配成功
            resp.getWriter().print("ok");//返回ok
        } else {
            resp.getWriter().print("error");//可省略
        }


    }
    public static Good findByKey(String Num) throws SQLException {
        Connection conn=jdbcConn.getConnection();
        QueryRunner runner = new QueryRunner();//使用阿帕奇的数据库操作工具DBUtils中的类
        String sql = "select * from good where Num=?";//定义查询语句
        Good good = runner.query(conn, sql, new BeanHandler<Good>(Good.class), Num);//将查询结果以实体类Pet返回
        conn.close();//因为使用的数据库连接池，此处的关闭只是将连接返回数据池中，并没有真正关闭，以等待其它应用直接使用
        return good;
    }
}

package home;

import bean.Person;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/personServlet")
public class personServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置请求的编码
        resp.setContentType("text/html;charset=utf-8");//设置响应的编码
        PrintWriter out=resp.getWriter();//获取响应字节打印流

        String data="";
        String key=null;
        if(req.getParameter("id")==null||req.getParameter("id")==""){//获取待搜索的学号，其中id为传过来的参数
            key ="1=1";//为空显示所有商品信息
        }else{
            key = "id =" + req.getParameter("id");//不为空，模糊查询
        }
        try {
            List<Person> slist= sqlD.findPerson(key);//查询所有学生信息
            data= JSON.toJSONString(slist);//将集合转换成json字符串
            int count=slist.size();//获取学生信息条数
            data="{\"code\": 0,\"msg\": \"\",\"count\": "+count+",\"data\":"+data+"}";//字符串拼接成Layui的table组件能识别的json字符串
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        out.print(data);
        System.out.println("id:"+req.getParameter("id"));
        sqlD.deletePeople(req.getParameter("id2"));
    }
}

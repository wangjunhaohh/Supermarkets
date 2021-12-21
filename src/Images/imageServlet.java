package Images;

import bean.Good;
import bean.image;
import com.alibaba.fastjson.JSON;
import home.sqlD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        List<image> slist= imageDao.query();//查询所有学生信息
        String data="";
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        int count=slist.size();//获取学生信息条数
        data="{\"code\": 0,\"msg\": \"\",\"count\": "+count+",\"data\":"+data+"}";
        out.print(data);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

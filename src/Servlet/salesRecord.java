package Servlet;

import bean.Good;
import bean.Sales;
import com.alibaba.fastjson.JSONObject;
import home.sqlD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/salesRecord")
public class salesRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
//        resp.getWriter().print("ok");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format(now);
        sqlD.updGoods(Integer.parseInt(req.getParameter("Num")),req.getParameter("id"));
        Sales sales=new Sales();
        sales.setId(req.getParameter("id"));
        sales.setGoods(req.getParameter("name"));
        sales.setNum(Integer.valueOf(req.getParameter("Num")));
        sales.setOutTime(time);
        sales.setSupplier(req.getParameter("supplier"));
        sales.setPhoto(req.getParameter("filesrc"));//获取上传的图片文件名
        sales.setTotalPrice(Integer.parseInt(req.getParameter("Num"))*Integer.parseInt(req.getParameter("price")));
        System.out.println(sales);
        JSONObject res = new JSONObject();//使用阿里的FastJson实例化json对象
        res.put("code", 0);//创建键值对，设置键code的值为0
        if (sqlD.addSales(sales) > 0) {//使用方法添加学生信息，添加成功返回提交页去处理
            res.put("code", 1);//修改键值对，设置键code的值为0
        }
        out.print(res.toJSONString());//输出json字符串
    }
}

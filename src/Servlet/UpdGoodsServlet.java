package Servlet;

import bean.Good;
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

@WebServlet("/UpdGoodsServlet")
public class UpdGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求的编码
        response.setContentType("text/html;charset=utf-8");//设置响应的编码
        PrintWriter out = response.getWriter();//获取响应字节打印流
        Good good = new Good();
        good.setId(request.getParameter("id"));//输入框的值
        good.setGoods(request.getParameter("name"));
        good.setNum(Integer.valueOf(request.getParameter("num")));
        good.setTime(request.getParameter("time"));
        good.setSupplier(request.getParameter("supplier"));
        good.setPhoto(request.getParameter("filesrc"));//获取上传的图片文件名
        System.out.println(good);
        try {
            JSONObject res = new JSONObject();//使用阿里的FastJson实例化json对象
            res.put("code", 0);//创建键值对，设置键code的值为0
            if (sqlD.updateGood(good) > 0) {
                res.put("code", 1);//修改键值对，设置键code的值为0
            }
            out.print(res.toJSONString());//输出json字符串
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

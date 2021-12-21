package home;

import bean.Stuinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddStu2Servlet")
@MultipartConfig
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求的编码
        response.setContentType("text/html;charset=utf-8");//设置响应的编码
        PrintWriter out = response.getWriter();//获取响应字节打印流
        Stuinfo stu = new Stuinfo();//实例化学生信息实体类
        stu.setSno(request.getParameter("sno"));//设置学号为学号输入框的值
        stu.setName(request.getParameter("name"));
        stu.setAge(request.getParameter("age"));
        stu.setSex(request.getParameter("sex"));
        stu.setTel(request.getParameter("phone"));
        //实现头像文件上传，
        String photo="";
        Part part = request.getPart("photo");//获取文件上传域
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

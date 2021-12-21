package Servlet;

import home.Rand;
import mail.email;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
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
        String code= Rand.getStrings(3,9);
        this.getServletContext().setAttribute("code",code);
        if(req.getParameter("email").endsWith("@qq.com")){
            resp.getWriter().print("1");
            email e=new email(req.getParameter("email"),"用户：", "如果这封邮件不是你操作的，请忽略。注册验证码："+code);
            e.start();
            System.out.println(5);

        }else {
            resp.getWriter().print("0");
        }
    }
}

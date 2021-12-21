package Servlet;

import bean.Administrator;
import home.sqlD;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        String user=req.getParameter("uname");
        String password=req.getParameter("pwd");
        System.out.println(req.getParameter("uname"));
        System.out.println(req.getParameter("pwd"));
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("captcha"));
        HttpSession session = req.getSession();
        List<Administrator> slist= null;
        try {
            slist = sqlD.findQuerryAdminstratot("1=1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int i=0;
        String code=(String)this.getServletContext().getAttribute("code");
        if(user.length()==0||password.length()==0){
            resp.getWriter().print("1");
        }else{
//            &&code.equals(req.getParameter("captcha"))
            for(Administrator s:slist){
                if(s.getUsername().equals(user)&&s.getPassword().equals(password)
                ){
                    session.setAttribute("user",user);
                    ServletContext context=this.getServletContext();
                    Integer count=(Integer)context.getAttribute("count");
                    if(count==null){
                        count=1;
                    }else{
                        count++;
                    }
                    context.setAttribute("count",count);
                    session.setAttribute("count1",count);
                    Cookie uname=new Cookie("user",user);
                    Cookie pwd=new Cookie("password",password);
                    uname.setMaxAge(60*60);
                    pwd.setMaxAge(60*60);
                    resp.addCookie(uname);
                    resp.addCookie(pwd);
                    i=1;
                    break;
                }
            }
            if(i==0){
                resp.getWriter().print("2");
            }else{
                resp.getWriter().print("ok");
            }
        }

//

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

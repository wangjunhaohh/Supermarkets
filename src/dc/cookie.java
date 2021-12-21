package dc;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/abbc")
public class cookie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        //获取并遍历客户端
        if ("汪俊豪".equals(req.getParameter("username")) && "2019401331".equals(req.getParameter("password"))) {
            if ("true".equals(req.getParameter("check"))) {
                Cookie nameC = new Cookie("username", md5(req.getParameter("username")));
                Cookie passwordC = new Cookie("password", md5(req.getParameter("password")));
                resp.addCookie(nameC);
                resp.addCookie(passwordC);
            } else {
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        resp.addCookie(cookie);
                    }
                }
            }
            out.println("登陆成功");
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("<h1>用户名或密码不正确，请重新输入！</h1>");
            resp.setHeader("Refresh", "3;URL=layuimini-2-onepage/page/ccf.jsp");//
        }
    }
    public final static String md5(String value){//md5加密
        String result = null;
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
            md5.update((value).getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException error){
            error.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }
}
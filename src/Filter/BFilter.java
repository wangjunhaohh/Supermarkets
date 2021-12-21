package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "BFilter",urlPatterns = "/supermarketWareManagement/index2.jsp",initParams = {
        @WebInitParam(name="loginPage",value = "ccf.jsp"),
        @WebInitParam(name="loginServlet" ,value="LoginServlet")
})
public class BFilter implements Filter {
    private FilterConfig config;

    public void destroy() {
    }



    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {    HttpServletResponse response=(HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HttpSession session = ((HttpServletRequest) req).getSession();
        String requestPath = ((HttpServletRequest) req).getServletPath();
        String loginPage = config.getInitParameter("loginPage");
        String loginServlet = config.getInitParameter("loginServlet");
        if(session.getAttribute("user")!=null||requestPath.endsWith(loginPage)||requestPath.endsWith(loginServlet))
        {
            chain.doFilter(req,resp);
        }
        else {
            request.setAttribute("tip","无权访问请先登录");
//            request.getRequestDispatcher(loginPage).forward(request,response);
            out.print("<script>");
            out.print("alert('还未登录,无权访问！')");
//            out.print("window.location.href=\"http://localhost:8080/text_war_exploded/home/sasd.jsp\"");
            out.print("</script>");
            ((HttpServletResponse) resp).setHeader("Refresh","0;http://localhost:8080/Supermarket_war_exploded/supermarketWareManagement/page/ccf.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
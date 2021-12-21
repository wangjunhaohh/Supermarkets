package Servlet;

import home.sqlD;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/DelGoodsServlet")
public class DelGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String sno = request.getParameter("xh");
        String[] goodslist = request.getParameterValues("id");//获取showStu.jsp传递过来在data参数中定义的待删除的学号，返回数组
        String[] photolist = request.getParameterValues("pic2");////获取showStu.jsp传递过来待删除的文件，返回数组
        String good = "";
        for (String s : goodslist) {//遍历ajax传递过来的学号数组
            good = good + s + ",";
        }
        good = good.substring(0, good.length() - 1);//去除最后一个“,”
        System.out.println(good);
        String result = "false";//设置默认返回值
        try {
            if (sqlD.delGood(good) > 0) {//删除数据库记录
                //遍历删除上传的图像文件
                for (String sfile : photolist) {//遍历ajax传过来的图片文件名数组
                    String filepath = this.getServletContext().getRealPath("/uploadfile/") + sfile;//获取图片文件的物理路径;
                    if (!sfile.equals("nopic.jpg")) {
                        new File(filepath).delete();//如果不是头像未上传，则删除文件
                    }
                }
                result = "ok";//设置返回值到ajax调用showStu.jsp中的删除按钮事件
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        response.sendRedirect(request.getContextPath()+"/ch16/showStu2.jsp");
        response.getWriter().print(result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            System.out.println(sqlD.delGood("11,12"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(5);
    }
}

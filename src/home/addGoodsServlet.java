package home;

import bean.Good;
import util.FileTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/addGoodsServlet")
public class addGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Good good = new Good();
        good.setId(req.getParameter("id"));//设置学号为学号输入框的值
        good.setGoods(req.getParameter("good"));
        good.setNum(Integer.valueOf(req.getParameter("num")));
//        good.setSex(req.getParameter("sex"));
        good.setTime(req.getParameter("time"));
        good.setSupplier(req.getParameter("Supplier"));
        good.setPhoto(req.getParameter("photo"));//添加图片名称
        Part part = req.getPart("photo");//获取文件上传域
        String filepath = this.getServletContext().getRealPath("/uploadfile");//获取文件上传位置
        String photo = FileTools.fileUpload(part, filepath);//上传文件，并返回上传后的文件名
        good.setPhoto(photo);

        System.out.println(good);

    }
}

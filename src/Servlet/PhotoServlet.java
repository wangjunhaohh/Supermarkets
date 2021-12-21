package Servlet;
import home.Ren;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@WebServlet("/SaveImgServlet")
public class PhotoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String imageDataUrl = request.getParameter("pp");
        Base64.Decoder decoder =  Base64.getDecoder();
        byte[] b = decoder.decode(imageDataUrl);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        System.out.println(1);
        BufferedImage bi1 = ImageIO.read(bais);
        String s=System.currentTimeMillis()+".png";
        String filepath="D:\\javaweb\\Supermarkets\\web\\supermarketWareManagement\\images\\";
        File w2 = new File(filepath+s);
        ImageIO.write(bi1, "png", w2);
        System.out.println(2);
        Ren r=new Ren();
        try {
            r.renlian("D:\\javaweb\\Supermarkets\\web\\supermarketWareManagement\\images\\1639983419673.png",filepath+s);
            response.getWriter().print("1");
            System.out.println(3);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.getWriter().print("0");
        }
    }
}

package Servlet;

import com.alibaba.fastjson.JSONObject;
import util.FileTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/UploadFileServlet")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("photo");//获取上传文件域
        String fileimg = "";
        if(part.getSubmittedFileName().length()>0) {//如果选择了文件
            String filepath = this.getServletContext().getRealPath("/uploadfile");//获取文件上传位置
            fileimg= FileTools.fileUpload(part, filepath);//上传文件，并返回上传后的文件名
        }
        System.out.println(fileimg);
        JSONObject res = new JSONObject();
        JSONObject resurl = new JSONObject();
        resurl.put("src",fileimg);//设置json键值对
        res.put("code", 0);
        res.put("msg","");
        res.put("data", resurl);
//        String ok="{\n" +
//                "  \"code\": 0\n" +
//                "  ,\"msg\": \"\"\n" +
//                "  ,\"data\": {\n" +
//                "    \"src\": \"+fileimg+\"\n" +
//                "  }\n" +
//                "}      ";
        response.getWriter().print(res.toJSONString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileTools {
    public static String fileUpload(Part part, String filepath) throws IOException {
        String filename=part.getSubmittedFileName();//获取上传文件名
        File file = new File(filepath);
        if(!file.exists()){
            file.mkdirs();
        }
        part.write(filepath+"/"+filename);//上传文件
        return filename;
    }

}

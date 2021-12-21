package home;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunPy {


    public static void runPy(){
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python D:\\PyCharmCommunityEdition\\home\\Demo1.py");
            // 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

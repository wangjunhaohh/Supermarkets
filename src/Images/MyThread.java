package Images;

import bean.image;

import java.util.List;

public class MyThread implements Runnable{
    private int sum;
    private int new_sum;
    private boolean stopMe = true;
    public void stopMe() {
        stopMe = false;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()  {
        sum=imageDao.selectCount();
        WebSocketServlet wbs=new WebSocketServlet();
        while(stopMe){
            new_sum=imageDao.selectCount();
            if(sum!=new_sum){
                System.out.println("change");
                sum=new_sum;
                wbs.onMessage(sum);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

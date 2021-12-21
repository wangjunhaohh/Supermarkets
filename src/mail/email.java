package mail;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送
 *     QQ邮箱--->别的邮箱
 * @author shiyunpeng
 */
public class email extends Thread {
    private String mailAdr;//邮箱
    private String content;//邮件的内容
    private String subject;//邮件的题目
    public email(String mailAdr, String subject, String content) {
        super();
        this.mailAdr = mailAdr;
        this.subject = subject;
        this.content = content;
    }
    public email() {}
    @Override
    public void run() {
        super.run();
        try {
            sendMail(mailAdr, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String achieveCode() {  //由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(3, 9);  //截取字符串第4到8
    }
    //llrupyofmlyibcbg
    private void sendMail(String mailAdr, String subject, String content) throws Exception {
        //加密的邮件套接字协议工厂
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // smtp登陆的账号、密码 ；需开启smtp登陆
        props.setProperty("mail.debug", "true");
        props.put("mail.user", "853824636@qq.com");
        props.put("mail.password", "crzoklpkhyorbbej");
        // 特别需要注意，要将ssl协议设置为true,否则会报530错误
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try {
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
            // 设置收件人
            InternetAddress to = new InternetAddress(mailAdr);
            message.setRecipient(Message.RecipientType.TO, to);
            // 设置抄送
            // InternetAddress cc = new InternetAddress("@qq.com");
            // message.setRecipient(RecipientType.CC, cc);
            // 设置密送，其他的收件人不能看到密送的邮件地址
            // InternetAddress bcc = new InternetAddress("@163.com");
            // message.setRecipient(RecipientType.CC, bcc);
            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        email e=new email();
        email d = new email("853824636@qq.com", "用户：", "如果这封邮件不是你操作的，请忽略。注册验证码："+e.achieveCode());
        d.start();

    }

}
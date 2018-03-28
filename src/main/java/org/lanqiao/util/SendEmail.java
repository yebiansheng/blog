package org.lanqiao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private static final String PROPERTIES_DEFAULT = "email.properties";
    public static String host;
    public static String port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    public static String personal;
    public static Properties properties;
    static{
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        properties = new Properties();
        try{
            InputStream inputStream = SendEmail.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            host = properties.getProperty("mailHost");
            port = properties.getProperty("mailPort");
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mailTimeout");
            personal = "墨裔";
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void sendEmail(String receiver,int code){
		try {
			/*Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "SMTP");
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.port", port);
			// 指定验证为true
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.timeout", timeout);
			// 验证账号及密码，密码需要是第三方授权码
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, passWord);
				}
			};
			Session session = Session.getInstance(props, auth);

			// 2.创建一个Message，它相当于是邮件内容
			MimeMessage message = new MimeMessage(session);
			// 设置发送者
			message.setFrom(new InternetAddress(emailForm));
			// 设置发送方式与接收者
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
			// 设置主题
			message.setSubject("验证码");
			// 设置内容
			message.setContent("验证码:"+Tools.randomNumber(5), "text/html;charset=utf-8");

			// 3.创建 Transport用于将邮件发送
			Transport.send(message);*/
			
			
			Properties props = new Properties();
			 props.setProperty("mail.transport.protocol", "SMTP");
			 props.setProperty("mail.smtp.host", "smtp.163.com");
			 props.setProperty("mail.smtp.port", "25");
			 // 指定验证为true
			 props.setProperty("mail.smtp.auth", "true");
			 props.setProperty("mail.smtp.timeout","1000");
			 // 验证账号及密码，密码需要是第三方授权码
			 Authenticator auth = new Authenticator() {
			 public PasswordAuthentication getPasswordAuthentication(){
			    return new PasswordAuthentication("13132252775@163.com", "13132252775ybs");
			            }
			        };
			 Session session = Session.getInstance(props, auth);

			// 2.创建一个Message，它相当于是邮件内容
			MimeMessage message = new MimeMessage(session);
			// 设置发送者
			message.setFrom(new InternetAddress("13132252775@163.com"));
			// 设置发送方式与接收者
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
			// 设置主题
			message.setSubject("邮件发送测试");
			// 设置内容
			message.setContent("邮件发送测试内容"+code, "text/html;charset=utf-8");

			// 3.创建 Transport用于将邮件发送
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		//sendEmail("3143319590@qq.com");
	
		 /*Properties props = new Properties();
		 props.setProperty("mail.transport.protocol", "SMTP");
		 props.setProperty("mail.smtp.host", "smtp.163.com");
		 props.setProperty("mail.smtp.port", "25");
		 // 指定验证为true
		 props.setProperty("mail.smtp.auth", "true");
		 props.setProperty("mail.smtp.timeout","1000");
		 // 验证账号及密码，密码需要是第三方授权码
		 Authenticator auth = new Authenticator() {
		 public PasswordAuthentication getPasswordAuthentication(){
		    return new PasswordAuthentication("13132252775@163.com", "13132252775ybs");
		            }
		        };
		 Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		MimeMessage message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress("13132252775@163.com"));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("3143319590@qq.com"));
		// 设置主题
		message.setSubject("邮件发送测试");
		// 设置内容
		message.setContent("邮件发送测试内容"+Tools.randomNumber(6), "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);*/

	}

	

}

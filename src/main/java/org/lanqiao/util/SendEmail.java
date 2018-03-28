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
     * ��ʼ��
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
            personal = "ī��";
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
			// ָ����֤Ϊtrue
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.timeout", timeout);
			// ��֤�˺ż����룬������Ҫ�ǵ�������Ȩ��
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, passWord);
				}
			};
			Session session = Session.getInstance(props, auth);

			// 2.����һ��Message�����൱�����ʼ�����
			MimeMessage message = new MimeMessage(session);
			// ���÷�����
			message.setFrom(new InternetAddress(emailForm));
			// ���÷��ͷ�ʽ�������
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
			// ��������
			message.setSubject("��֤��");
			// ��������
			message.setContent("��֤��:"+Tools.randomNumber(5), "text/html;charset=utf-8");

			// 3.���� Transport���ڽ��ʼ�����
			Transport.send(message);*/
			
			
			Properties props = new Properties();
			 props.setProperty("mail.transport.protocol", "SMTP");
			 props.setProperty("mail.smtp.host", "smtp.163.com");
			 props.setProperty("mail.smtp.port", "25");
			 // ָ����֤Ϊtrue
			 props.setProperty("mail.smtp.auth", "true");
			 props.setProperty("mail.smtp.timeout","1000");
			 // ��֤�˺ż����룬������Ҫ�ǵ�������Ȩ��
			 Authenticator auth = new Authenticator() {
			 public PasswordAuthentication getPasswordAuthentication(){
			    return new PasswordAuthentication("13132252775@163.com", "13132252775ybs");
			            }
			        };
			 Session session = Session.getInstance(props, auth);

			// 2.����һ��Message�����൱�����ʼ�����
			MimeMessage message = new MimeMessage(session);
			// ���÷�����
			message.setFrom(new InternetAddress("13132252775@163.com"));
			// ���÷��ͷ�ʽ�������
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
			// ��������
			message.setSubject("�ʼ����Ͳ���");
			// ��������
			message.setContent("�ʼ����Ͳ�������"+code, "text/html;charset=utf-8");

			// 3.���� Transport���ڽ��ʼ�����
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
		 // ָ����֤Ϊtrue
		 props.setProperty("mail.smtp.auth", "true");
		 props.setProperty("mail.smtp.timeout","1000");
		 // ��֤�˺ż����룬������Ҫ�ǵ�������Ȩ��
		 Authenticator auth = new Authenticator() {
		 public PasswordAuthentication getPasswordAuthentication(){
		    return new PasswordAuthentication("13132252775@163.com", "13132252775ybs");
		            }
		        };
		 Session session = Session.getInstance(props, auth);

		// 2.����һ��Message�����൱�����ʼ�����
		MimeMessage message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress("13132252775@163.com"));
		// ���÷��ͷ�ʽ�������
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("3143319590@qq.com"));
		// ��������
		message.setSubject("�ʼ����Ͳ���");
		// ��������
		message.setContent("�ʼ����Ͳ�������"+Tools.randomNumber(6), "text/html;charset=utf-8");

		// 3.���� Transport���ڽ��ʼ�����
		Transport.send(message);*/

	}

	

}

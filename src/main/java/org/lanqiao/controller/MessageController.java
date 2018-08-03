package org.lanqiao.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.dao.ImageMapper;
import org.lanqiao.dao.MessageMapper;
import org.lanqiao.entity.Image;
import org.lanqiao.entity.Message;
import org.lanqiao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

@Controller
public class MessageController {
	@Autowired
	MessageMapper dao;
	@Autowired
	ImageMapper imgdao;
	
	@RequestMapping("intertMsg")
	@ResponseBody
	public void intertMsg(HttpServletResponse response,HttpSession session, Message msg) {
		try {
			Date date = new Date();
			User user = new User();
			int userId=(int) session.getAttribute("userID");
			user.setId(userId);
			msg.setUser(user);
			msg.setDate(date);
			msg.setPraise(0);
			int i = dao.insertMsg(msg);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statu", i);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("intertMsgByArtID")
	@ResponseBody
	public void intertMsg(HttpServletResponse response,HttpSession session, Message msg,String artID) {
		try {
			Date date = new Date();
			User user = new User();
			int userId=(int) session.getAttribute("userID");
			user.setId(userId);
			msg.setUser(user);
			msg.setDate(date);
			msg.setPraise(0);
			msg.setArticle_id(Integer.parseInt(artID));
			int i = dao.insertMsg(msg);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statu", i);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("intertImg")
	@ResponseBody
	public void intertImg(HttpServletResponse response,Image img) {
		try {
			int i = imgdao.insertSelective(img);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statu", i);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("uploadImg")
	@ResponseBody
	public void uploadImg(HttpServletResponse response, String img) {
		System.out.println(img);
		String[] pics = img.split(",");// 0为类型 1为数据
		String type = null;
		if (pics[0].indexOf("image/jpeg") > 0) {
			type = ".jpg";
		} else if (pics[0].indexOf("image/gif") > 0) {
			type = ".gif";
		} else if (pics[0].indexOf("image/png") > 0) {
			type = ".png";
		} else {

		}

		String imgname = GenerateImage(pics[1], type,
				"D:/workspace/eclipseworkspace/TJSD2018/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/blog/images/picture/");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("imgUrl", "images/picture/" + imgname);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GenerateImage(pics[1],
		// type,"D:/apache-tomcat-7.0.73/webapps/imagetemplate/");
	}
	public static String GenerateImage(String imgStr, String imgType, String filePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 根据时间生成图片名称
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmsssss");
			String imgFilePath = filePath + simpleDateFormat.format(new Date()) + imgType;
			// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return simpleDateFormat.format(new Date()) + imgType;
		} catch (Exception e) {
			return "";
		}
	}
}

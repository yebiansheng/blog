package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.User;
import org.lanqiao.service.ArticleService;
import org.lanqiao.service.UserService;
import org.lanqiao.util.SendEmail;
import org.lanqiao.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	@Autowired
	UserService userService;	
	@Autowired
	ArticleService articleService;	
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	@RequestMapping("logincheck")
	public String logincheck(HttpServletRequest request,User user,HttpSession session,HttpServletResponse response,String mdl){
		//request.getParameter("userName");
		
		String name=user.getName();
		User us=userService.getUserByName(name);
		if(us==null){
			 return "redirect:login";  
		}
	
		if(user.getPassword().equals(us.getPassword())){
			session.setAttribute("userName", us.getName());
			session.setAttribute("userID", us.getId());
			if("1".equals(mdl)){
				 //创建Cookie对象  
                Cookie cookie1=new Cookie("username","123");  
                //设置有效时间  
                cookie1.setMaxAge(60*60*24*10);  
                //设置关联路径  
                //cookie1.setPath(request.getContextPath());  
                //cookie2.setPath(request.getContextPath());  
                //发送Cookie给浏览器  
                response.addCookie(cookie1);  
			}
				
			
			return "redirect:index";
		}
		
		return "login";
	}
	
	
	@RequestMapping("code")
	@ResponseBody
	public void code(HttpServletResponse response,String email){
		//request.getParameter("userName");
		try {
			JSONObject jsonObject = new JSONObject();
			int codeNum = Tools.randomNumber(5);
			SendEmail.sendEmail(email, codeNum);
			jsonObject.put("codeNum", codeNum);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("fromCheck")
	@ResponseBody
	public void fromCheck(HttpServletResponse response,String userName,String email){
		//request.getParameter("userName");
		try {
			JSONObject jsonObject = new JSONObject();
			int codeNum = Tools.randomNumber(5);
			SendEmail.sendEmail(email, codeNum);
			jsonObject.put("codeNum", codeNum);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("insertUser")
	public String insertUser(HttpServletRequest request,User user){
		//request.getParameter("userName");
		if(userService.getUserByName(user.getName())==null){
			user.setHead_url("images/head/4.jpg");
			userService.insertSelective(user);
			return "register";
		}
		return "login";
	}
	@RequestMapping("xgmm")
	public String xgmm(HttpServletRequest request,User user){
		//request.getParameter("userName");
		User uaer1=userService.getUserByEmail(user.getEmail());
		uaer1.setPassword(user.getPassword());
		userService.updateByPrimaryKeySelective(uaer1);
		
		return "login";
	}
	
	@RequestMapping("register")
	public String register(){
		return "register";
	}
	@RequestMapping("about")
	public String about(){
		return "about";
	}
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	@RequestMapping("article")
	public String article(){
		return "article";
	}
	@RequestMapping("listpic")
	public String listpic(){
		return "listpic";
	}
	@RequestMapping("message")
	public String message(){
		return "message";
	}
	@RequestMapping("newslistpic")
	public String newslistpic(){
		return "newslistpic";
	}
	@RequestMapping("html")
	public String html(){
		return "html";
	}
	@RequestMapping("register2")
	public String register2(){
		return "register2";
	}
	@RequestMapping("image")
	public String image(){
		return "image";
	}
	@RequestMapping("upload1")
	public String upload1(){
		return "upload";
	}
	@RequestMapping("bianji")
	public String bianji(){
		return "bianji";
	}
	
	
}

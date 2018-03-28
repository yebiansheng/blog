package org.lanqiao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginFilter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 System.out.println("===============================LoginFilter......preHandle=================================");
		 
		  /*String url=request.getRequestURI();  
	        //判断url是否是公开地址(实际使用时将公开地址配置到配置文件中)  
	        if(url.indexOf("login")>=0||url.indexOf("css")>=0||url.indexOf("js")>=0||url.indexOf("js")>=0){  
	            //如果要进行登录提交，放行  
	            return true;  
	        }  */
		 
		 
		 
		 HttpSession session=request.getSession();  
		 String userName=(String) session.getAttribute("userName");
		 if(userName!=null){
			 response.setCharacterEncoding("utf-8");
			 return true;
		 }
		
		 response.sendRedirect("login");
		 
		 return false;
		 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		 System.out.println("==============================LoginFilter......postHandle=============================="); 
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		 System.out.println("==============================LoginFilter......afterCompletion=============================="); 
		// TODO Auto-generated method stub

	}

}

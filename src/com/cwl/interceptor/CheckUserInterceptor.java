package com.cwl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cwl.pojo.Auctionuser;

public class CheckUserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
		

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		HttpSession session = request.getSession();
		
		String path = request.getRequestURI();
		if(path.indexOf("doLogin")!=-1||path.indexOf("register")!=-1) {
			return true;
		}
		Auctionuser loginUser = (Auctionuser) session.getAttribute("user");
		if(loginUser!=null) {
			return true;
		}else {
			System.out.println("拦截了一个非法请求！");
			//访问登录页
			response.sendRedirect("login.jsp");
			return false;
		}
	}

}

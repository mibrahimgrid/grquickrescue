package com.gr.grquickrescue.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LoginBean loginBean = (LoginBean) ((HttpServletRequest)request).getSession().getAttribute("loginBean");
		
		HttpServletRequest reqt = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String reqURI = reqt.getRequestURI();
		
		if(loginBean == null || !loginBean.isLoggedIn() ) 
		{
			resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
		}else 
		{
			if(reqURI.indexOf("/login.xhtml") >=0 ) 
			{
				resp.sendRedirect(reqt.getContextPath() + "/resources/secured/account/account.xhtml");
			}
			
			chain.doFilter(request, response);
		}
		
		
			
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

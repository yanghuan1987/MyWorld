package com.spfood.pms.catagory.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

/**
 * 公共controller方法
 * @author fengjunchao 2016-11-23
 *
 */
@Controller
public class ControllerUtils {
	
	public HttpServletResponse response;
	
	public HttpServletRequest request;
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void out(Object obj){
		try {
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

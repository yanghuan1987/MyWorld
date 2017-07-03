package com.spfood.uias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.common.mto.MessageTransferObject;
import com.spfood.common.security.EmployeeDTO;
import com.spfood.uias.utils.JsonResult;

@Controller
@RequestMapping(value="login")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);
	
	/**
	 * 登录
	 * @param employeeDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public JsonResult login(@RequestBody EmployeeDTO employeeDTO) {
		Subject subject = SecurityUtils.getSubject();
		//解密
		if(employeeDTO != null && employeeDTO.getPassword() != null)
			employeeDTO.setPassword(Base64.decodeToString(employeeDTO.getPassword()));
		
		UsernamePasswordToken token = new UsernamePasswordToken(employeeDTO.getAccount(),
				employeeDTO.getPassword(),employeeDTO.isRememberMe());
		try {
			subject.login(token);
			log.info("["+employeeDTO.getAccount()+"] login success");
		} catch (UnknownAccountException e) {
			return new JsonResult(MessageTransferObject.ERROR, "您输入的用户名不存在",null);//您输入的用户名不存在
		} catch (IncorrectCredentialsException e) {
			return new JsonResult(MessageTransferObject.ERROR, "您输入的密码不正确",null);//您输入的密码不正确
		} catch (Exception e) {
			log.error("login fail :"+e.getMessage(),e);
			return new JsonResult(MessageTransferObject.ERROR, "其他错误",null);//其他错误
		}
		return new JsonResult(MessageTransferObject.SUCCESS, "登录成功",null);
	}
	
	/**
	 * APP登录
	 * @param employeeDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/applogin")
	public JsonResult login(@RequestParam String account,@RequestParam String password,HttpServletResponse response,HttpServletRequest request) {
		//
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		
		Subject subject = SecurityUtils.getSubject();
		//解密
		if(account == null || password == null || "".equals(account) || "".equals(password))
			return new JsonResult(MessageTransferObject.ERROR, "您输入的用户名不存在",null);
		
		UsernamePasswordToken token = new UsernamePasswordToken(account,
				password,false);
		try {
			subject.login(token);
			log.info("["+account+"] login success");
		} catch (UnknownAccountException e) {
			return new JsonResult(MessageTransferObject.ERROR, "您输入的用户名不存在",null);//您输入的用户名不存在
		} catch (IncorrectCredentialsException e) {
			return new JsonResult(MessageTransferObject.ERROR, "您输入的密码不正确",null);//您输入的密码不正确
		} catch (Exception e) {
			log.error("login fail :"+e.getMessage(),e);
			return new JsonResult(MessageTransferObject.ERROR, "其他错误",null);//其他错误
		}
		return new JsonResult(MessageTransferObject.SUCCESS, "登录成功",((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getEmployeeID());
	}
	
	/**
	 * 注销登录
	 * @param employeeDTO
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET,value="/loginOut", produces={"application/xml", "application/json"})
	public void logout(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		try {
			Subject subject = SecurityUtils.getSubject();
			log.info("["+((EmployeeDTO)subject.getPrincipal()).getAccount()+"] logout");
			subject.logout();
			res.sendRedirect(req.getContextPath()+"/main/page");
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath()+"/main/page");
		}
	}
	
}

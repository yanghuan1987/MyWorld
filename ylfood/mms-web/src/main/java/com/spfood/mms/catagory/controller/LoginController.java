package com.spfood.mms.catagory.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.common.mto.MessageTransferObject;
import com.spfood.common.security.EmployeeDTO;

@Controller
@RequestMapping(value="login")
public class LoginController {
	private static final Log log = LogFactory.getLog(LoginController.class);
	/**
	 * 登录
	 * @param employeeDTO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/login", produces={"application/xml", "application/json"})
	public @ResponseBody String login(@RequestBody EmployeeDTO employeeDTO) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(employeeDTO.getAccount(),
				employeeDTO.getPassword(),employeeDTO.isRememberMe());
		String errorcode = null;
		try {
			subject.login(token);
			log.info("["+employeeDTO.getAccount()+"] login success");
		} catch (UnknownAccountException e) {
			errorcode = "1";//您输入的用户名不存在
		} catch (IncorrectCredentialsException e) {
			errorcode = "2";//您输入的密码不正确
		} catch (Exception e) {
			log.error("login fail :"+e.getMessage(),e);
			errorcode = "3";//其他错误
		}
		if (errorcode != null) {
			return errorcode;
		} else {
			return MessageTransferObject.SUCCESS;
		}
	}
	
	/**
	 * 注销登录
	 * @param employeeDTO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/loginOut", produces={"application/xml", "application/json"})
	public @ResponseBody String logout(){
		Subject subject = SecurityUtils.getSubject();
		log.info("["+((EmployeeDTO)subject.getPrincipal()).getAccount()+"] logout");
		subject.logout();
		return MessageTransferObject.SUCCESS;
	}
	
	/**
	 * mms后台主界面
	 * @return
	 */
	@RequestMapping(value="/main")
	public String Main(){
		log.info("访问MMS主界面");
		return "mmsMain";
	}
}

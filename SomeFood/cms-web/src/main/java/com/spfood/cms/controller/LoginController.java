package com.spfood.cms.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="login")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);
	
	/**
	 * cms后台主界面
	 * @return
	 */
	@RequestMapping(value="/main")
	public String Main(){
		log.info("in cmsMain");
		return "cmsMain";
	}
}

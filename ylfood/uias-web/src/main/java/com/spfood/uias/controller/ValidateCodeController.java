package com.spfood.uias.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.uias.utils.JsonResult;
import com.spfood.web.common.utils.ImageVerifyCode;
import com.spfood.web.common.utils.ImageVerifyCodeUtil;
/**
 * 验证码控制器
 *
 */
@Controller
@RequestMapping(value="validatecode")
public class ValidateCodeController {
	
	@Autowired
	private BasicConfigurationService basicConfigurationService;
	
    private static final String VALIDATE_CODE = "validatecode";

    /**
     * 验证码判断控制方法
     * 新生成验证码存入session
     * 图片生成
     *
     */
    @RequestMapping(method=RequestMethod.GET,value="/creatvalidatecode", produces={"application/xml", "application/json"})
    protected void service(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, java.io.IOException {
    	HttpSession session = req.getSession();
    	response.setHeader("Content-Type", "image/JPEG");
    	response.setHeader("Pragma","No-cache");
    	response.setHeader("Cache-Control","no-cache");
    	response.setDateHeader("Expires", 0);
    	//生成验证码
    	ImageVerifyCode icode = ImageVerifyCodeUtil.creatImageVerifyCode(75,24);
    	//打印图片
    	ImageIO.write(icode.getImage(), "JPEG", response.getOutputStream());
        session.setAttribute(VALIDATE_CODE, icode.getCode());	
    }
    
    /**
     * 验证码判断控制
     *
     */
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST,value="/checkvalidatecode", produces={"application/xml", "application/json"})
	public JsonResult CheckValidateCode(HttpServletRequest request,@RequestParam String code)  throws Exception{
    	
    	if(basicConfigurationService.getConfigureValueAsString("PmsIdentifyCode") != null)
    		return new JsonResult(MessageTransferObject.SUCCESS, "success",null);
    	
    	HttpSession sessionHttp = request.getSession();
    	String strAttrValidateCode = (String) sessionHttp.getAttribute("validatecode");
		 
    	if(strAttrValidateCode == null || !strAttrValidateCode.equalsIgnoreCase(code)){
    		return new JsonResult(MessageTransferObject.ERROR, "验证码错误",null);
    	}
			return new JsonResult(MessageTransferObject.SUCCESS, "success",null);
    }
}

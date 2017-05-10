package com.spfood.cms.controller;



import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.cms.intf.SearchKeywordService;
import com.spfood.cms.intf.domain.SearchKeyword;
import com.spfood.cms.utils.JsonResult;
import com.spfood.cms.utils.MessagesUtil;
import com.spfood.common.mto.MessageTransferObject;

@Controller
@RequestMapping(value="keyword")
public class SearchKeywordController {

	private static final Log log = LogFactory.getLog(SearchKeywordController.class);
	
	@Autowired
	private SearchKeywordService searchKeywordService;
	
	@Autowired
	private Validator validator;
	
	/**
	 * cms跳转到关键字页面
	 * @return jsp页面路径
	 */
	@RequiresPermissions("cms:keyword:showPage")
	@RequestMapping(value="/main")
	public String Main(){
		log.info("To access the CMS page keyword");
		return "keyword/searchKeyword";
	}
	
	/**
	 * cms关键字展示数据
	 * @return JsonResult对象
	 */
	@ResponseBody
	@RequiresPermissions("cms:keyword:showPage")
	@RequestMapping(value="/list")
	public JsonResult list(){
		log.info("Show the CMS page keyword");
		return new JsonResult(MessageTransferObject.SUCCESS, null, searchKeywordService.selectAll());
	}
	

	/**
	 * cms修改关键字数据
	 * @param searchKeyword 关键字对象,不为空
	 * @return JsonResult对象
	 */
	@ResponseBody
	@RequiresPermissions("cms:keyword:showPage")
	@RequestMapping(method=RequestMethod.POST, value="/update", produces={"application/xml", "application/json"})
	public JsonResult update(@RequestBody SearchKeyword searchKeyword){
		log.info("Modify the CMS keyword");
		// 通过注入的验证对象对searchKeyword中的字段进行验证,不满足的字段信息放入set集合中
		Set<ConstraintViolation<SearchKeyword>> validate = validator.validate(searchKeyword);
		// 判断验证是否通过
		if (validate.size() > 0){
			//数据验证不通过
			return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("searchKeyword.validate.error"), null);
		}
		// 根据Id判断是新增还是修改
		if (searchKeyword.getId() != null) {
			boolean update = searchKeywordService.update(searchKeyword);
			return update == true ? new JsonResult(MessageTransferObject.SUCCESS, null, null) 
				: new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("searchKeyword.update.error"), null);
		} else {
			SearchKeyword insertSearchKeyword = searchKeywordService.insert(searchKeyword);
			return insertSearchKeyword != null ? new JsonResult(MessageTransferObject.SUCCESS, null, insertSearchKeyword)
				: new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("searchKeyword.update.error"), null);
		}
	}
}

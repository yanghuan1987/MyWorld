package com.spfood.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.cms.intf.MenuService;
import com.spfood.cms.intf.domain.Menu;
import com.spfood.cms.utils.CommodityStatus;
import com.spfood.cms.utils.JsonResult;
import com.spfood.cms.utils.MessagesUtil;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.ProductCategoryService;
import com.spfood.pms.intf.domain.Commodity;


@Controller
@RequestMapping(value = "MenuConfig")
public class MenuController {

	@Resource
	private MenuService menuService;

	@Resource
	private ProductCategoryService productCategoryService;
	
	@Resource
	private CommodityService commodityService;
	
	@Autowired
	private Validator validator;
	
	private static final Log log = LogFactory.getLog(MenuController.class);
	
	/**
	 * cms菜单管理主界面
	 * @return jsp页面路径
	 */
	@RequiresPermissions("cms:MenuConfig:showPage")
	@RequestMapping(value = "/showPage")
	public String showMain() {
		log.info("To access the CMS management main interface menu");
		return "menuConfig/menuConfig";
	}

	/**
	 * cms获取菜单信息
	 * @return JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:MenuConfig:showPage")
	@RequestMapping(method=RequestMethod.POST,value="/getMenuInfo",produces={"application/xml", "application/json"})
	public JsonResult getMenuInfo() {
		log.info("For information on the menu");
		List<Menu> list = menuService.selectAll();
		return new JsonResult(MessageTransferObject.SUCCESS, null, list);
	}

	/**
	 * cms更新编辑信息
	 * @param menu 菜单对象,不为空
	 * @return JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:MenuConfig:showPage")
	@RequestMapping(method=RequestMethod.POST,value="/updateMenu",produces={"application/xml","application/json"})
	public JsonResult updateMenu(@RequestBody Menu menu) {
		log.info("Update the menu information");
		// 通过注入的验证对象对menu中的字段进行验证,不满足的字段信息放入set集合中
		Set<ConstraintViolation<Menu>> validate = validator.validate(menu);
		if (validate.size() > 0){
			// 数据验证不通过
			return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("menu.validate.error"), null);
		}
		Boolean resultBoolean=menuService.updateOne(menu);
		return new JsonResult(MessageTransferObject.SUCCESS, null, resultBoolean);
	}

	/**
	 * 同步品类数据
	 * @return  JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:MenuConfig:showPage")
	@RequestMapping(method=RequestMethod.POST,value="/synchroCategoryInfo",produces={"application/xml","application/json"})
	public JsonResult synchroCategoryInfo() {
		log.info("To get the data from the productCategory interface");
		//从接口获取数据
		HashMap<String, String> hashMap = productCategoryService.selectCategoryAlltoMap();
		log.info("Synchronous data to the database");
		//同步数据到数据库
		List<Menu> list = menuService.synchroCategoryInfo(hashMap);
		log.info("Synchronizing data category");
		//返回同步以后的数据
		return new JsonResult(MessageTransferObject.SUCCESS, MessagesUtil.getMessageByKey("menu.synchronize.success"), list);
	}

	/**
	 * 分页获取商品信息
	 * @param categoryCode 品类编码,不为空
	 * @param pageNum 当前页,不为空
	 * @param pageSize 每页条数,不为空
	 * @param commodity 商品对象,不为空
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/getCommdityInfo",produces={"application/xml","application/json"})
	public JsonResult getCommodityInfo(@RequestParam String categoryCode,
			@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Commodity commodity) {
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(pageNum, pageSize);
		log.info("Data from the interface to get goods");
		commodity.setCommodityStatus(CommodityStatus.UP.getValue());//显示上架商品
		List<Commodity> list = commodityService.selectCommodityByCategory(categoryCode, commodity, pageInfo).getResult();
		// 商品获取信息
		String msgcode = null;
		// 是否获取到信息
		String status = null;
		// 判断是否获取到商品给信息和状态字段赋值
		if (null == list || list.size() == 0) {
			msgcode = MessagesUtil.getMessageByKey("menu.msgcode.error");//没有获取信息
			status = MessageTransferObject.ERROR;
		}else {
			msgcode = MessagesUtil.getMessageByKey("menu.msgcode.success");//获取到信息
			status = MessageTransferObject.SUCCESS;
		}
		return new JsonResult(status, msgcode, commodityService.selectCommodityByCategory(categoryCode, commodity, pageInfo));
	}
}

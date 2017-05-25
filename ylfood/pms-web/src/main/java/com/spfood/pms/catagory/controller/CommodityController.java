package com.spfood.pms.catagory.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.basicservice.intf.ConfigureOptionDTO;
import com.spfood.basicservice.intf.DivisionNode;
import com.spfood.basicservice.intf.DivisionReaderService;
import com.spfood.basicservice.intf.IDGeneratorService;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.common.security.EmployeeDTO;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityCommentService;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
/**
 * 商品控制器
 * @author fengjunchao 2016-12-15
 *
 */
@Controller
@RequestMapping(value="commodity")
public class CommodityController{
	
	@Resource
	private CommodityService commodityService;
	
	@Autowired
	private BasicConfigurationService basicConfigurationService;
	
	@Autowired
	private IDGeneratorService idGeneratorService;
	
	@Autowired
	private DivisionReaderService divisionReaderService;
	
	@Autowired
	private CommodityCommentService commodityCommentService;
	
	/**
	 * pms商品管理后台主界面
	 * @return
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(value="/showMain")
	public String showMain(){
		return "commodity/commodity";
	}
	/**
	 * 获取品类属性
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCategoryPropertyList", produces={"application/xml", "application/json"})
	public @ResponseBody List<ProductCategoryProperty> getAllCategoryList(@RequestParam String categoryCode){
		return commodityService.selectCategoryPropertyByCategoryCode(categoryCode);
	}
	
	/**
	 * 获取商品列表
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityList", produces={"application/xml", "application/json"})
	public @ResponseBody PageInfo<Commodity> selectCommodityAll(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Commodity commodity){
		return commodityService.selectCommodityAll(commodity, new PageInfo<Commodity>(pageNum, pageSize));
	}
	
	/**
	 * 获取商品列表
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityDetial", produces={"application/xml", "application/json"})
	public @ResponseBody PageInfo<Commodity> selectCommodityDetial(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Commodity commodity){
		return commodityService.selectCommodityDetial(commodity, new PageInfo<Commodity>(pageNum, pageSize));
	}
	
	/**
	 *单个商品上架
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateUp", produces={"application/xml", "application/json"})
	public @ResponseBody Commodity updateCommodityStateUp(@RequestBody Commodity commodity){
		commodity.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		if(commodityService.updateCommodityStateUp(commodity.getId())){
			return commodity;
		}else
			return null;
	}
	
	/**
	 *单个商品下架
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateDown", produces={"application/xml", "application/json"})
	public @ResponseBody Commodity updateCommodityStateDown(@RequestBody Commodity commodity){
		commodity.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		if(commodityService.updateCommodityStateDown(commodity.getId())){
			return commodity;
		}else
			return null;
	}
	/**
	 *单个商品删除
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateDelete", produces={"application/xml", "application/json"})
	public @ResponseBody Commodity updateCommodityStateDelete(@RequestBody Commodity commodity){
		commodity.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		if(commodityService.updateCommodityStateDelete(commodity.getId())){
			return commodity;
		}else
			return null;
	}
	/**
	 *多个商品批量上架
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateUps", produces={"application/xml", "application/json"})
	public @ResponseBody String updateCommodityStateUps(@RequestBody Long ids[]){
		if(commodityService.updateCommodityStateUps(ids)){
			return MessageTransferObject.SUCCESS;
		}else
			return null;
	}
	
	/**
	 *多个商品批量下架
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateDowns", produces={"application/xml", "application/json"})
	public @ResponseBody String updateCommodityStateDowns(@RequestBody Long ids[]){
		if(commodityService.updateCOmmodityStateDowns(ids)){
			return MessageTransferObject.SUCCESS;
		}else
			return null;
	}
	/**
	 *多个商品批量删除
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityStateDeletes", produces={"application/xml", "application/json"})
	public @ResponseBody String updateCommodityStateDeletes(@RequestBody Long ids[]){
		if(commodityService.updateCommodityStateDeletes(ids)){
			return MessageTransferObject.SUCCESS;
		}else
			return null;
	}
	/**
	 *获取商品图片
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityPicture", produces={"application/xml", "application/json"})
	public @ResponseBody List<CommodityPicture> selectCommodityPicture(@RequestBody Commodity commodity){
		return commodityService.selectCommodityPictureByCommodityCode(commodity);
	}
	
	/**
	 *新增商品
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/addCommodity", produces={"application/xml", "application/json"})
	public @ResponseBody Commodity addCommodity(@RequestBody Commodity commodity){
		//自动获取商品编码
		commodity.setCommodityCode(idGeneratorService.getNextInnerCommodityCode());
		commodity.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		commodity.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		//获取商品GS1编码，当商品对应的产品有GS1编码时，继承该产品的GS1编码，否则，调用服务自动生成商品GS1编码
		commodity.setCommodityGs1Code((commodity.getProduct().getProductGs1Code() == null || commodity.getProduct().getProductGs1Code().length()==0)?idGeneratorService.getNextGS1CommodityCode():commodity.getProduct().getProductGs1Code());
		commodityService.addCommodity(commodity);
		return commodity;
	}
	
	/**
	 *修改商品
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodity", produces={"application/xml", "application/json"})
	public @ResponseBody String updateCommodity(@RequestBody Commodity commodity){
		commodity.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		commodityService.updateCommodity(commodity);
		return MessageTransferObject.SUCCESS;
	}
	
	/**
	 * 获取所有的重量单位列表。
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicWeightUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicWeightUnit() throws Exception{
		return basicConfigurationService.getBasicWeightUnit();
	}
	
	/**
	 * 获取客户类型列表
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicCustomerType", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicCustomerType() throws Exception{
		return basicConfigurationService.getConfigureOptions("BasicCustomerType");
	}
	
	/**
	 * 获取城市区域列表
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getDivisionNode", produces = {"application/json" })
	public @ResponseBody List<DivisionNode> getDivisionNode() throws Exception{
//		getDivisionHasBusiness();
		return divisionReaderService.getDivisionHasBusiness();
	}
	
	/**
	 * 获得商品评价(好评,差评)对应的总数 
	 */
	@RequiresPermissions("pms:commodity:show")
	@RequestMapping("getCommentTypeCount")
	@ResponseBody
	public CommodityComment getCommentTypeCount(String commodityCode) {
		CommodityComment commodityComment = new CommodityComment();
		commodityComment.setCommodityCode(commodityCode);
		return commodityCommentService.selectCommodityCommentCount(commodityComment);
	}
	
	
}
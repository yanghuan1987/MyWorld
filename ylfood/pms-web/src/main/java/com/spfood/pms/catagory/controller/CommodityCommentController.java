/**
 * 
 */
package com.spfood.pms.catagory.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityCommentService;
import com.spfood.pms.intf.CommodityCommentTypeService;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.ProductCategoryService;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.CommodityCommentType;
import com.spfood.pms.intf.domain.ProductCategory;

/**
 * 评价管理控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="comment")
public class CommodityCommentController {
	@Resource
	private ProductCategoryService categoryService;
	@Resource
	private CommodityService commodityService;
	@Resource
	private CommodityCommentService commodityCommentService;
	@Resource
	private CommodityCommentTypeService commodityCommentTypeService;
	
	/**
	 * pms评价管理后台主界面
	 * @return
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(value="/showMain")
	public String showMain(){
		return "comment/comment";
	}
	
	/**
	 * 获取品类商品评价数量
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCategoryCommentTotal", produces={"application/xml", "application/json"})
	public @ResponseBody PageInfo<ProductCategory> selectCategoryCommentTotal(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody ProductCategory productCategory){
		if (productCategory == null) {
			productCategory = new ProductCategory();
		}
		return categoryService.selectCommodityCommentByPage(new PageInfo<ProductCategory>(pageNum, pageSize),productCategory);
	}
	
	/**
	 * 获取商品评价数量
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityCommentTotal", produces={"application/xml", "application/json"})
	public @ResponseBody PageInfo<Commodity> selectCommodityCommentTotal(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Commodity commodity){
		return commodityService.selectCommodityCommentByPage(new PageInfo<Commodity>(pageNum, pageSize),commodity);
	}
	
	/**
	 * 获取商品评价
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityCommentDetail", produces={"application/xml", "application/json"})
	public @ResponseBody PageInfo<CommodityComment> selectCommodityCommentDetail(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody CommodityComment commodityComment){
		return commodityCommentService.selectCommodityCommentByPage(new PageInfo<CommodityComment>(pageNum, pageSize),commodityComment);
	}
	
	/**
	 * 获取商品评价数量
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCommodityCommentCount", produces={"application/xml", "application/json"})
	public @ResponseBody CommodityComment selectCommodityCommentCount(@RequestBody CommodityComment commodityComment){
		return commodityCommentService.selectCommodityCommentCount(commodityComment);
	}
	
	/**
	 * 更新评论是否显示
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.POST,value="/updateCommodityCommentCountStatus", produces={"application/json"})
	public @ResponseBody boolean updateCommodityCommentCountStatus(@RequestBody CommodityComment commodityComment){
		return commodityCommentService.updateCommodityCommentCountStatus(commodityComment);
	}
	
	/**
	 * 取得评论类别
	 */
	@RequiresPermissions("pms:comment:show")
	@RequestMapping(method=RequestMethod.GET,value="/selectAllInfoType", produces={"application/json"})
	public @ResponseBody List<CommodityCommentType> selectAllInfoType() throws Exception{
		return commodityCommentTypeService.selectAllInfo();
	}
}

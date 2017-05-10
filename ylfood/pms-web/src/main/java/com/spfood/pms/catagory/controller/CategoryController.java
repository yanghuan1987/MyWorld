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
import com.spfood.basicservice.intf.IDGeneratorService;
import com.spfood.common.security.EmployeeDTO;
import com.spfood.pms.intf.ProductCategoryService;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
/**
 * 品类控制器
 * @author fengjunchao 2016-11-18
 *
 */
@Controller
@RequestMapping(value="category")
public class CategoryController{
	
	@Resource
	private ProductCategoryService categoryService;
	
	@Autowired
	private IDGeneratorService idGeneratorService;
	
	@Autowired
	private BasicConfigurationService basicConfigurationService;
	
	/**
	 * pms品类后台主界面
	 * @return
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(value="/showMain")
	public String showMain(){
		return "category/category";
	}
	
	/**
	 * 三级品类界面
	 * @return
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/inshowThreeCatagory", produces={"application/xml", "application/json"})
	public @ResponseBody ProductCategory showCategoryInfos(@RequestBody ProductCategory category){
		return categoryService.selectById(category.getId());
	}
	
	/**
	 * 获取品类列表
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.GET,value="/GetCatagoryList", produces={"application/xml", "application/json"})
	public @ResponseBody List<ProductCategory> getAllCategoryList(){
		return categoryService.selectFirstLevelCategoryAll();
	}
	
	/**
	 * 根据编码获取子品类
	 * @param categoryCode
	 * @return
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/selectCategoryChildren", produces={"application/xml", "application/json"})
	public @ResponseBody List<ProductCategory> getCategoryChildrenList(@RequestParam String categoryCode){
		return categoryService.selectCategoryChildrenByCode(categoryCode);
	}
	
	/**
	 * 新增一、二级品类
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/AddCategory",produces={"application/xml", "application/json"})
	public @ResponseBody ProductCategory addCategory(@RequestBody ProductCategory category){
		category.setCategoryCode((category.getParentCategory() != null?category.getParentCategory().getCategoryCode():"") 
				+ idGeneratorService.getNextCategoryCode(category.getParentCategory() != null?category.getParentCategory().getCategoryCode():null));
		category.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		category.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		return categoryService.insertFirstLevelOrSecondLevelCategroy(category);
	}
	
	/**
	 * 修改一、二级品类
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/UpdateCategory",produces={"application/xml", "application/json"})
	public @ResponseBody ProductCategory updateCategory(@RequestBody ProductCategory category){
		category.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		categoryService.updateFirstLevelOrSecondLevelCategroy(category);
		return category;
	}
	
	/**
	 * 新增三级品类及属性信息
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/AddCategoryProperty",produces={"application/xml", "application/json"})
	public @ResponseBody Long addCategoryProperties(@RequestBody ProductCategory category){
		category.setCategoryCode((category.getParentCategory() != null?category.getParentCategory().getCategoryCode():"") + 
								idGeneratorService.getNextCategoryCode(category.getParentCategory() != null?category.getParentCategory().getCategoryCode():null));
		category.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		category.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		List<ProductCategoryProperty> listTempCategories = category.getProductCategoryPropertys();
		for (int i = 0; i < listTempCategories.size(); i++) {
			listTempCategories.get(i).setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
			listTempCategories.get(i).setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		}
		return categoryService.insertOrUpdateThirdLevelCategory(category, category.getProductCategoryPropertys());
	}
	
	/**
	 * 修改三级品类及属性信息
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/UpdateCategoryProperty",produces={"application/xml", "application/json"})
	public @ResponseBody Long updateCategoryProperties(@RequestBody ProductCategory category){
		category.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		List<ProductCategoryProperty> listTempCategories = category.getProductCategoryPropertys();
		for (int i = 0; i < listTempCategories.size(); i++) {
			listTempCategories.get(i).setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
			listTempCategories.get(i).setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		}
		return categoryService.insertOrUpdateThirdLevelCategory(category, category.getProductCategoryPropertys());
	}
	
	/**
	 * 验证名称是否被占用
	 * @return
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method=RequestMethod.POST,value="/checkProductCategoryName",produces={"application/xml", "application/json"})
	public @ResponseBody Boolean selectProductCategoryNameIsHave(@RequestBody ProductCategory category){
		return categoryService.selectProductCategoryNameIsHave(category);
	}

	/**
	 * 获取品类名称不能取值列表
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("pms:category:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getDisableCategoryPropertyName", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getDisableCategoryPropertyName() throws Exception{
		return basicConfigurationService.getConfigureOptions("PmsDisableCategoryPropertyName");
	}
	
}
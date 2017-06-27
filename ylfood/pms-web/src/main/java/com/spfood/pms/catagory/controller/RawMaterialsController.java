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
import com.spfood.imageupload.intf.ImageUploadService;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.ProductCategoryService;
import com.spfood.pms.intf.RawMaterialsService;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.RawMaterials;
import com.spfood.pms.intf.domain.RawMaterialsPackUnit;
import com.spfood.pms.intf.domain.RawMaterialsPicture;
import com.spfood.pms.intf.domain.RawMaterialsSearchCriteria;

/**
 * RawMaterials Controller
 * 
 * @author qinbenzhong 2016-11-18
 *
 */
@Controller
@RequestMapping(value = "rawMaterials")
public class RawMaterialsController {

	@Autowired
	private RawMaterialsService rawMaterialsService;

	@Resource
	private ProductCategoryService categoryService;

	@Autowired
	private IDGeneratorService idGeneratorService;
	
	@Autowired
	private ImageUploadService imageUploadService;

	@Autowired
	private BasicConfigurationService basicConfigurationService;

	// 显示产品列表信息，用于表格数据的读取和操作
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/selectByPage", produces = {"application/json" })
	public @ResponseBody PageInfo<RawMaterials> selectByPage(@RequestBody RawMaterialsSearchCriteria rawMaterialsSearchCriteria) {
		PageInfo<RawMaterials> pageInfo = rawMaterialsService.selectListByPage(rawMaterialsSearchCriteria);
		List<RawMaterials> rawMaterialses = pageInfo.getResult();
		pageInfo.setResult(rawMaterialses);
		return pageInfo;
	}

	/**
	 * 新增标准产品
	 * @param rawMaterials
	 * @return
	 */
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/addRawMaterials", produces = {"application/json" })
	public @ResponseBody RawMaterials addRawMaterials(@RequestBody RawMaterials rawMaterials) {
		//调用服务生产产品的编码
		rawMaterials.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		rawMaterials.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		rawMaterials.setRawMaterialsCode(idGeneratorService.getNextProductCode());
		Long id = rawMaterialsService.addRawMaterials(rawMaterials);
		rawMaterials.setId(id);
		return rawMaterials;
	}


	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/updateRawMaterials", produces = {"application/json" })
	public @ResponseBody Long updateRawMaterials(@RequestBody RawMaterials rawMaterials) {
		rawMaterials.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		Long id = rawMaterialsService.updateRawMaterials(rawMaterials);
		return id;
	}

	/**
	 * pms后台产品设计主界面
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(value = "/showMain")
	public String showMain() {
		return "rawMaterials/rawMaterials";
	}

	/**
	 * 获取品类列表
	 */
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/GetCatagoryList", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllCategoryList() {
		return categoryService.selectFirstLevelCategoryAll();
	}

	// 显示产品表头信息
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getRawMaterialsProperties", produces = {"application/json" })
	public @ResponseBody ProductCategory getRawMaterialsProperties(@RequestBody RawMaterials rawMaterials) {
		return rawMaterialsService.selectByCategoryCode(rawMaterials.getCategoryCode());
	}
	//包装单位
	@RequiresPermissions("pms:RawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getRawMaterialsPackUnit", produces = {"application/xml", "application/json" })
	public @ResponseBody List<RawMaterialsPackUnit> getRawMaterialsPackUnit(@RequestParam String RawMaterialsCode) {
		return rawMaterialsService.selectRawMaterialsPackUnitByRawMaterialsCode(RawMaterialsCode);
	}
	//系统产生产品的编码
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getRawMaterialsCode", produces = {"application/xml", "application/json" })
	public @ResponseBody String getRawMaterialsCode() {
		return idGeneratorService.getNextProductCode();
	}
	
	//获取产品图片
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getRawMaterialsPicture", produces = {"application/xml", "application/json" })
	public @ResponseBody List<RawMaterialsPicture> getRawMaterialsPicture(@RequestParam String rawMaterialsCode) {
		return rawMaterialsService.getRawMaterialsPictureListByRawMaterialsCode(rawMaterialsCode);
	}

	// 验证产品编码是否重复
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/isRawMaterialsCodeDuplicated", produces = {"application/json" })
	public @ResponseBody boolean isRawMaterialsCodeDuplicated(@RequestBody RawMaterials rawMaterials) {
		return rawMaterialsService.isRawMaterialsCodeDuplicated(rawMaterials);
	}
	
	//返回所有的一级品类
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFirstLevelCategories", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllFirstLevelCategories(){
		return categoryService.selectFirstLevelCategoryAll();
	}
	
	//返回符合条件的二级或三级品类
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllCategoriesByCategortyCode", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllCategoriesByCategortyCode(@RequestBody ProductCategory productCategory){
		return categoryService.selectCategoryChildrenByCode(productCategory.getCategoryCode());
	}
	
	//获取温区
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getRawMaterialsTemperatureZone", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getRawMaterialsTemperatureZone() throws Exception{
		return basicConfigurationService.getConfigureOptions("temperature_zone");
	}
	
	
	//返回所有的符合条件的产品
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllRawMaterialssByCriteria", produces = {"application/json" })
	public @ResponseBody List<RawMaterials> getAllRawMaterialssByCriteria(@RequestBody RawMaterialsSearchCriteria rawMaterialsSearchCriteria) {
		return rawMaterialsService.getAllRawMaterialssByCriteria(rawMaterialsSearchCriteria);
	}
	
	//检查GS1编码是否存在
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.POST, value = "/checkGS1IsExist", produces = {"application/xml", "application/json"})
	public @ResponseBody Boolean selectGs1IsExist(@RequestBody RawMaterials rawMaterials){
		return rawMaterialsService.selectGS1IsExist(rawMaterials);
	}

	//获取所有的包装单位列表，例如瓶、袋、箱、框等等。
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicPackageUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicPackageUnit() throws Exception{
		return basicConfigurationService.getBasicPackageUnit();
	}
	

	//获取辅材基本单位
	@RequiresPermissions("pms:rawMaterials:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getSecondaryRawMaterialsUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getSecondaryRawMaterialsUnit() throws Exception{
		return basicConfigurationService.getConfigureOptions("pms_secondary_product_unit");
	}
}
package com.spfood.pms.catagory.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.spfood.pms.intf.ProductService;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductSearchCriteria;

/**
 * Product Controller
 * 
 * @author qinbenzhong 2016-11-18
 *
 */
@Controller
@RequestMapping(value = "product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Resource
	private ProductCategoryService categoryService;

	@Autowired
	private IDGeneratorService idGeneratorService;
	
	@Autowired
	private ImageUploadService imageUploadService;

	@Autowired
	private BasicConfigurationService basicConfigurationService;

	// 显示产品列表信息，用于表格数据的读取和操作
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/selectByPage", produces = {"application/json" })
	public @ResponseBody PageInfo<Product> selectByPage(@RequestBody ProductSearchCriteria productSearchCriteria) {
		PageInfo<Product> pageInfo = productService	.selectListByPage(productSearchCriteria);
		List<Product> products = pageInfo.getResult();
		pageInfo.setResult(products);
		return pageInfo;
	}

	/**
	 * 新增标准产品
	 * @param product
	 * @return
	 */
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/addProduct", produces = {"application/json" })
	public @ResponseBody Product addProduct(@RequestBody Product product) {
		//调用服务生产产品的编码
		product.setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		product.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		product.setProductCode(idGeneratorService.getNextProductCode());
		Long id = productService.addProduct(product);
		product.setId(id);
		return product;
	}


	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/updateProduct", produces = {"application/json" })
	public @ResponseBody Long updateProduct(@RequestBody Product product) {
		product.setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		Long id = productService.updateProduct(product);
		return id;
	}

	/**
	 * 新增组合产品
	 * @param compositeProduct
	 * @return
	 */
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/addCompositeProduct", produces = {"application/json" })
	public @ResponseBody CompositeProduct addCompositeProduct(@RequestBody CompositeProduct compositeProduct) {
		//系统生成编码
		compositeProduct.getProduct().setCreateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		compositeProduct.getProduct().setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		compositeProduct.getProduct().setProductCode(idGeneratorService.getNextProductCode());
		Long id = productService.addCompositeProduct(compositeProduct);
		compositeProduct.getProduct().setId(id);
		return compositeProduct;
	}
	
	/**
	 * 修改组合产品
	 * @param compositeProduct
	 * @return
	 */
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/updateCompositeProduct", produces = {"application/json" })
	public @ResponseBody Long updateCompositeProduct(@RequestBody CompositeProduct compositeProduct) {
		compositeProduct.getProduct().setLastUpdateUser(((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount());
		Long id = productService.updateCompositeProduct(compositeProduct);
		return id;
	}
	
	/**
	 * pms后台产品设计主界面
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:product:show")
	@RequestMapping(value = "/showMain")
	public String showMain() {
		return "product/product";
	}

	/**
	 * 获取品类列表
	 */
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/GetCatagoryList", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllCategoryList() {
		return categoryService.selectFirstLevelCategoryAll();
	}

	// 显示产品表头信息
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getProductProperties", produces = {"application/json" })
	public @ResponseBody ProductCategory getProductProperties(@RequestBody Product product) {
		return productService.selectByCategoryCode(product.getCategoryCode());
	}

	//系统产生产品的编码
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getProductCode", produces = {"application/xml", "application/json" })
	public @ResponseBody String getProductCode() {
		return idGeneratorService.getNextProductCode();
	}
	
	//获取产品图片
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getProductPicture", produces = {"application/xml", "application/json" })
	public @ResponseBody List<ProductPicture> getProductPicture(@RequestParam String productCode) {
		return productService.getProductPictureListByProductCode(productCode);
	}

	// 验证产品编码是否重复
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/isProductCodeDuplicated", produces = {"application/json" })
	public @ResponseBody boolean isProductCodeDuplicated(@RequestBody Product product) {
		return productService.isProductCodeDuplicated(product);
	}

	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/imageUpload")
	public @ResponseBody List<ProductPicture> uploadImage(HttpServletRequest request)
			throws IOException, ServletException, Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> files = upload.parseRequest(request);
		List<ProductPicture> productPictures = new ArrayList<ProductPicture>();
		for(int i = 0; i<files.size();i++){
			//模拟图片服务器路径
			String fieldName = files.get(i).getFieldName();
			InputStream is = files.get(i).getInputStream();
			
			String fileName = files.get(i).getName();
			int index = fileName.lastIndexOf(".");
			String fileType = fileName.substring(index+1);
			String imageAddress = null;
			byte[] data = readInputStream(is);

			ProductPicture productPicture = new ProductPicture();
			//传递上来的名称为位置+逗号+顺序；如0,1:表示头部图片第2的位置
			if("0".equals(fieldName.split(",")[0])){
				productPicture.setPictureShowPosition(0);
				//文件上传，压缩图片方法
				imageAddress = imageUploadService.uploadAndScaleImage(data, fileType);
			} else{
				//文件上传，非压缩图片方法
				imageAddress = imageUploadService.uploadImage(data, fileType);
				productPicture.setPictureShowPosition(1);
			}
			productPicture.setPictureShowOrder(Integer.parseInt(fieldName.split(",")[1]));
			productPicture.setPictureAddress(imageAddress);
			productPictures.add(productPicture);
		}
		return productPictures;
	}

	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getCompositeProduct", produces = {"application/json" })
	public @ResponseBody CompositeProduct getCompositeProduct(@RequestBody Product product){
		return productService.getCompositeProduct(product);
	} 
	
	//将文件输入流转换为byte数组上传到文件服务器
	public byte[] readInputStream(InputStream inStream) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
	
	//返回所有的一级品类
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFirstLevelCategories", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllFirstLevelCategories(){
		return categoryService.selectFirstLevelCategoryAll();
	}
	
	//返回符合条件的二级或三级品类
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllCategoriesByCategortyCode", produces = {"application/json" })
	public @ResponseBody List<ProductCategory> getAllCategoriesByCategortyCode(@RequestBody ProductCategory productCategory){
		return categoryService.selectCategoryChildrenByCode(productCategory.getCategoryCode());
	}
	
	//获取所有的基本单位列表，包括面积、体积、长度、重量。
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllBasicMeasureUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getAllBasicMeasureUnit() throws Exception{
		return basicConfigurationService.getAllBasicMeasureUnit();
	}
	
	//获取温区
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getProductTemperatureZone", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getProductTemperatureZone() throws Exception{
		return basicConfigurationService.getConfigureOptions("temperature_zone");
	}
	
	//获取所有的包装单位列表，例如瓶、袋、箱、框等等。
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicPackageUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicPackageUnit() throws Exception{
		return basicConfigurationService.getBasicPackageUnit();
	}
	
	//获取所有的包装单位列表，例如瓶、袋、箱、框等等。
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getProductStatus", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getProductStatus() throws Exception{
		return basicConfigurationService.getConfigureOptions("pms_product_status");
	}
	
	//返回所有的符合条件的产品
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllProductsByCriteria", produces = {"application/json" })
	public @ResponseBody List<Product> getAllProductsByCriteria(@RequestBody ProductSearchCriteria productSearchCriteria) {
		return productService.getAllProductsByCriteria(productSearchCriteria);
	}
	
	//检查GS1编码是否存在
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.POST, value = "/checkGS1IsExist", produces = {"application/xml", "application/json"})
	public @ResponseBody Boolean selectGs1IsExist(@RequestBody Product product){
		return productService.selectGS1IsExist(product);
	}
	
	//包装单位
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getProductPackUnit", produces = {"application/xml", "application/json" })
	public @ResponseBody List<ProductPackUnit> getProductPackUnit(@RequestParam String productCode) {
		return productService.selectProductPackUnitByProductCode(productCode);
	}
	
	//获取所有基本单位。
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicPurchaseUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicPurchaseUnit() throws Exception{
		return basicConfigurationService.getBasicPurchaseUnit();
	}
	
	//获取温区
	@RequiresPermissions("pms:product:show")
	@RequestMapping(method = RequestMethod.GET, value = "/getBasicWeightUnit", produces = {"application/json" })
	public @ResponseBody List<ConfigureOptionDTO> getBasicWeightUnit() throws Exception{
		return basicConfigurationService.getConfigureOptions("BasicWeightUnit");
	}
}
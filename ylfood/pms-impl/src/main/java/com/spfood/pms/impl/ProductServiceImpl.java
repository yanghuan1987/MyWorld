package com.spfood.pms.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.ProductService;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductSearchCriteria;
import com.spfood.pms.manager.ProductManager;
import com.spfood.pms.manager.ProductPackUnitManager;
/**
 * Product Service
 * @author qinbenzhong 2016-12-11
 *
 */
public class ProductServiceImpl implements ProductService {
	Logger logger = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductManager productManager;
	@Autowired
	private ProductPackUnitManager productPackUnitManager;
	

	@Override
	public PageInfo<Product> selectListByPage(
			ProductSearchCriteria productSearchCriteria) {
		logger.debug("Start select by page");
		PageInfo<Product> result = productManager.selectListByPage(productSearchCriteria);
		logger.debug("end select by page");
		return result;
	}

	@Override
	public ProductCategory selectByCategoryCode(String categoryCode) {
		return productManager.selectByCategoryCode(categoryCode);
	}

	@Override
	public Long addProduct(Product product){
		productManager.insert(product);
		return product.getId();
	}
	
	@Override
	public Long updateProduct(Product product){
		productManager.update(product);
		return product.getId();
	}

	//验证产品编码是否重复
	@Override
	public boolean isProductCodeDuplicated(Product product){
		Long count = productManager.selectCount(product);
		return count>0 ? true: false;
	}

	//组合产品详情显示
	@Override
	public CompositeProduct getCompositeProduct(Product product) {
		return productManager.getCompositeProduct(product);
	}
	
	@Override
	public List<Product> getAllProductsByCriteria(ProductSearchCriteria productSearchCriteria){
		return productManager.getAllProductsByCriteria(productSearchCriteria);
	}
	
	@Override
	public long updateCompositeProduct(CompositeProduct compositeProduct){
		productManager.updateCompositeProduct(compositeProduct);
		return compositeProduct.getProduct().getId();
	}
	
	@Override
	public Long addCompositeProduct(CompositeProduct compositeProduct){
		productManager.insertCompositeProduct(compositeProduct);
		return compositeProduct.getProduct().getId();
	}

	@Override
	public List<ProductPicture> getProductPictureListByProductCode(
			String productCode) {
		return productManager.getProductPictureListByProductCode(productCode);
	}

	@Override
	public boolean updateProductBuyUnit(Product product) throws Exception{
		if(product == null || product.getId() == null || product.getProductBuyUnitName() == null)
			return false;
		//创建新的产品对象，将需要更新的产品购买单位名称和购买单位编码赋值
		Product p = new Product();
		p.setId(product.getId());//ID赋值
		p.setProductBuyUnitCode(product.getProductBuyUnitCode());//编码赋值
		p.setProductBuyUnitName(product.getProductBuyUnitName());//名称赋值
		if(productManager.updateByIdSelective(p) > 0)
			return true;
		return false;
	}
	@Override
	public Boolean selectGS1IsExist(Product product){
		return productManager.selectGS1IsExist(product);
	}
	@Override
	public List<ProductPackUnit> selectProductPackUnitByProductCode(String productCode){
		return productPackUnitManager.selectByProductCode(productCode);
	};
	
	@Override
	public List<Product> selectPaclUnitListByProductCodes(List<String> list){
		return productManager.selectPaclUnitListByProductCodes(list);
	}
	
	@Override
	public List<Product> selectProducePlaceByProductCodes(List<String> list){
		return productManager.selectProducePlaceByProductCodes(list);
	}

	/**
	 * 为SCM提供接口
	 * @param productSearchCriteria 有分页条件的搜索对象
	 * @return PageInfo 分页对象
	 */
	@Override
	public PageInfo<Product> selectListByPageSecond(ProductSearchCriteria productSearchCriteria) {
		logger.debug("Start select second category by page");
		PageInfo<Product> result = productManager.selectListByPageSecond(productSearchCriteria);
		logger.debug("end select second category by page");
		return result;
	}
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<Product> selectListByPageForWMS(ProductSearchCriteria productSearchCriteria) {
		logger.debug("Start select by pageForWMS");
		PageInfo<Product> result = productManager.selectListByPageForWMS(productSearchCriteria);
		logger.debug("end select by pageForWMS");
		return result;
	}
}

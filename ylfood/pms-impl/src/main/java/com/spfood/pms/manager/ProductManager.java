/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductSearchCriteria;

/**
 * Product Manager接口类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
public interface ProductManager extends BaseManager<Product> {

	public PageInfo<Product> selectListByPage(ProductSearchCriteria searchCriteria);
	
	public int update(Product product);
	
	public ProductCategory selectByCategoryCode(String categoryCode);
	
	public CompositeProduct getCompositeProduct(Product product);
	
	public void insertCompositeProduct(CompositeProduct compositeProduct);
	
	public void updateCompositeProduct(CompositeProduct compositeProduct);
	
	public void deleteCompositeProduct(CompositeProduct compositeProduct);
	
	public List<Product> getAllProductsByCriteria(ProductSearchCriteria searchCriteria);
	
	public List<ProductPicture> getProductPictureListByProductCode(String code);
	
	public Boolean selectGS1IsExist(Product product);
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	List<Product> selectPaclUnitListByProductCodes(List<String> list);
	
	//WMS接口-查询温区，产地
	public List<Product> selectProducePlaceByProductCodes(List<String> list);
	
	/**
	 * 为SCM提供接口专用
	 * @param searchCriteria 分页查询对象
	 * @return PageInfo 分页对象
	 */
	PageInfo<Product> selectListByPageSecond(ProductSearchCriteria searchCriteria);
	
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<Product> selectListByPageForWMS(ProductSearchCriteria searchCriteria); 
}

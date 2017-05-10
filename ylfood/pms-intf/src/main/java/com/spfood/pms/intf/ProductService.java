package com.spfood.pms.intf;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductSearchCriteria;

public interface ProductService {

	public PageInfo<Product> selectListByPage(ProductSearchCriteria productSearchCriteria); 
	
	public ProductCategory selectByCategoryCode(String categoryCode);

	public Long addProduct(Product product);
	
	public Long updateProduct(Product product);

	public boolean isProductCodeDuplicated(Product product);
	
	public CompositeProduct getCompositeProduct(Product product);
	
	public List<Product> getAllProductsByCriteria(ProductSearchCriteria productSearchCriteria);
	
	public long updateCompositeProduct(CompositeProduct compositeProduct);
	
	public Long addCompositeProduct(CompositeProduct compositeProduct);
	
	public List<ProductPicture> getProductPictureListByProductCode(String productCode);
	
	public boolean updateProductBuyUnit(Product product) throws Exception;

	public Boolean selectGS1IsExist(Product product);
	
	public List<ProductPackUnit> selectProductPackUnitByProductCode(String productCode);
	
	public List<Product> selectPaclUnitListByProductCodes(List<String> list);
	//WMS接口-查询温区，产地
	public List<Product> selectProducePlaceByProductCodes(List<String> list);
	
	/**
	 * 为SCM提供接口
	 * @param productSearchCriteria 有分页条件的搜索对象
	 * @return PageInfo 分页对象
	 */
	PageInfo<Product> selectListByPageSecond(ProductSearchCriteria productSearchCriteria);
	
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<Product> selectListByPageForWMS(ProductSearchCriteria productSearchCriteria); 
}

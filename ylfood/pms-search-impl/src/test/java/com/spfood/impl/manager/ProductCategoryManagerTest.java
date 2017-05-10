
package com.spfood.impl.manager;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.Product;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlaceForSelect;
import com.spfood.pms.search.manager.CommodityManager;
import com.spfood.pms.search.manager.ProductCategoryManager;
import com.spfood.pms.search.manager.ProductCategoryPropertyManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class ProductCategoryManagerTest {

	
	@Resource
	private ProductCategoryPropertyManager productCategoryPropertyManager;
	
	@Resource
	private CommodityManager commodityManager;
	
	@Resource
	private ProductCategoryManager productCategoryManager;
	
	/**
	 * 测试通过商品名称模糊查询到所有的品类
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectCategoryByCommodityName() {
		// 添加数据
		commodityManager.insert(createCommodity());
		productCategoryManager.insert(createProductCategory());
		// 拿到数据测试
		List<ProductCategory> productCategorys = productCategoryManager.selectCategoryByCommodityName("大苹果");
		for (ProductCategory productCategory : productCategorys) {
			assertTrue(productCategory != null);
		}
		
	}
	
	/**
	 * 测试通过商品名称模糊查询到所有的品类
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectCategoryByKeywords() {
		// 添加数据
		commodityManager.insert(createCommodity());
		productCategoryManager.insert(createProductCategory());
		List<String> keywords = new ArrayList<String>();
		keywords.add("苹果");
		keywords.add("呵呵");
		// 拿到数据测试
		List<ProductCategory> productCategorys = productCategoryManager.selectCategoryByKeywords(keywords,CommodityShowPlaceForSelect.C端.name());
		for (ProductCategory productCategory : productCategorys) {
			assertTrue(productCategory != null);
		}
	}
	
	
	/**
	 * 测试通过商品编码查询到所有的品类
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectCategoryNameByCommodityCode() {
		// 拿到数据测试
		List<ProductCategory> productCategorys = productCategoryManager.selectCategoryNameByCommodityCode("YLC0000000025");
		for (ProductCategory productCategory : productCategorys) {
			assertTrue(productCategory != null);
		}
	}
	
	/**
	 * 测试通过品类编码查询到所有的品类
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectCategoryByCategoryCode() {
		// 拿到数据测试
		List<ProductCategory> productCategorys = productCategoryManager.selectCategoryByCategoryCode("04001001");
		for (ProductCategory productCategory : productCategorys) {
			assertTrue(productCategory != null);
		}
	}
	
	
	
	/**
	 * 初始化品类数据
	 */
	public ProductCategory createProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryCode("YL999");
		return productCategory;
	}

	/**
	 * 初始化产品数据
	 */
	public Product createProduct() {
		Product product = new Product();
		product.setProductCode("YL888");
		product.setCategoryCode("YL999");
		return product;
	}

	
	/**
	 * 初始化商品数据
	 */ 
	public Commodity createCommodity() {
		Commodity commodity = new Commodity();
		commodity.setCommodityCity("成都");
		commodity.setCommodityCode("YL777");
		commodity.setCommodityComment("好吃不贵");
		commodity.setCommodityGs1Code("qwer");
		commodity.setCommodityName("大苹果");
		commodity.setCommodityPrice(new BigDecimal(5.55));
		commodity.setCommodityProductQuantity(50);
		commodity.setCommodityQuantity(20);
		commodity.setCommoditySalesPrice(new BigDecimal(10));
		commodity.setCommodityWeightUnit("斤");
		commodity.setCommodityWeight(7.77);
		commodity.setCommodityShowPlace("全网");
		commodity.setCommodityStatus(3);
		commodity.setCategory(createProductCategory());
		commodity.setProduct(createProduct());
		return commodity;
	}

	/**
	 * 初始化品类属性数据
	 */
	public ProductCategoryProperty createProductCategoryProperty() {
		ProductCategoryProperty productCategoryProperty = new ProductCategoryProperty();
		productCategoryProperty.setProductCategory(createProductCategory());
		return productCategoryProperty;
	}
	
	
}

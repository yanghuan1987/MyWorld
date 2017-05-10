package com.spfood.impl.manager;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.manager.CommodityManager;
import com.spfood.pms.search.manager.ProductCategoryManager;
import com.spfood.pms.search.manager.ProductCategoryPropertyManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class ProductCategoryPropertyManagerTest {

	
	@Resource
	private ProductCategoryPropertyManager productCategoryPropertyManager;
	
	@Resource
	private CommodityManager commodityManager;
	
	@Resource
	private ProductCategoryManager productCategoryManager;
	
	
	/**
	 * 测试通过商品名称模糊查询获得品类属性集合
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectCategoryByCommodityName() {
		// 拿到数据测试
		List<ProductCategoryProperty> productCategoryPropertys = productCategoryPropertyManager.selectPropertyByCommodityName("苹果");
		for (ProductCategoryProperty productCategoryProperty : productCategoryPropertys) {
			assertTrue(productCategoryProperty.getProductCategory() != null);
		}
	}
	
	
	
}

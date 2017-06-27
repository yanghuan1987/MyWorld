package com.spfood.impl.manager;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.Product;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;
import com.spfood.pms.search.intf.utils.Constant;
import com.spfood.pms.search.manager.CommodityAndCommentManager;
import com.spfood.pms.search.manager.CommodityManager;
import com.spfood.pms.search.manager.ProductCategoryManager;
import com.spfood.pms.search.manager.ProductCategoryPropertyManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
//@Transactional
public class CommodityManagerTest {

	@Resource
	private CommodityManager commodityManager;
	@Resource
	private ProductCategoryManager productCategoryManager;
	@Resource
    private ProductCategoryPropertyManager productCategoryPropertyManager;
	@Autowired
	private CommodityAndCommentManager CommodityAndCommentManager;
	
	
	public ProductCategory createProductCategory() {
	    ProductCategory productCategory=new ProductCategory();
	    productCategory.setCategoryCode("YL999");
        return productCategory;
    }
	public Product createProduct() {
	    Product product=new Product();
	    product.setProductCode("YL888");
	    product.setCategoryCode("YL999");
	    product.setCategory(createProductCategory());
	    return product;
    }

	public Commodity createCommodity() {
	    Commodity commodity =new Commodity();
	    commodity.setCommodityCity("成都");
	    commodity.setCommodityCode("YLC001");
	    commodity.setCommodityComment("好吃");
	    commodity.setCommodityGs1Code("qwer");
	    commodity.setCommodityName("大苹果");
	    commodity.setCommodityPrice(new BigDecimal(5.55));
	    commodity.setCommodityProductQuantity(50);
	    commodity.setCommodityQuantity(20);
	    commodity.setCommoditySalesPrice(new BigDecimal(10));
	    commodity.setProduct(createProduct());
	    commodity.setCategory(createProductCategory());
	    commodity.setCommodityWeightUnit("斤");
	    commodity.setCommodityWeight(7.77);
	    commodity.setCommodityShowPlace("全网");
	    commodity.setCommodityStatus(3);
	    return commodity;
    }
	
	public Commodity createCommodity1() {
        Commodity commodity =new Commodity();
        commodity.setCommodityCity("成都");
        commodity.setCommodityCode("YLC002");
        commodity.setCommodityComment("好吃不贵");
        commodity.setCommodityGs1Code("qwer");
        commodity.setCommodityName("大苹果");
        commodity.setCommodityPrice(new BigDecimal(5.55));
        commodity.setCommodityProductQuantity(50);
        commodity.setCommodityQuantity(20);
        commodity.setCommoditySalesPrice(new BigDecimal(10));
        commodity.setProduct(createProduct());
        commodity.setCategory(createProductCategory());
        commodity.setCommodityWeightUnit("斤");
        commodity.setCommodityWeight(7.77);
        commodity.setCommodityShowPlace("全网");
        commodity.setCommodityStatus(3);
        return commodity;
    }

	/**
	 * 测试新增及通过产品编码查询商品
	 */
	@Test
    @Rollback
    @Transactional
	public void selectCommodityByProduct() {
	    commodityManager.insert(createCommodity());
	    List<Commodity> commodities=commodityManager.selectCommodityByProduct(createProduct().getProductCode());
	    boolean tag=false;
	    for (Commodity commodity : commodities) {
	        if (commodity.getCommodityCode().equals(createCommodity().getCommodityCode())) {
	            tag=true;
            }
        }
	    assertEquals(tag,true);
    }
	
	/**
     * 测试根据商品编码获取商品
     */
	@Test
    @Rollback
    @Transactional
	public void selectByCommodityCode() {
	    commodityManager.insert(createCommodity());
	    Commodity commodity=commodityManager.selectByCommodityCode(createCommodity().getCommodityCode());
	    assertEquals(commodity.getCommodityCode(),createCommodity().getCommodityCode());
    }
	
	/**
     * 根据编码集合查询商品集合
     * @param codeList
     * @return
     */
	@Test
	@Rollback
    @Transactional
	public void selectCommodityByCodelist() {
	    List<String> list=new ArrayList<String>();
	    list.add("YLC0000000033");
	    list.add("YLC0000000035");
	    List<Commodity> commodities=commodityManager.selectCommodityByCodelist(list);
	    assertEquals(2,commodities.size());
    }
	
	@Test
    @Rollback
    @Transactional
	public void selectCommodityListByPage() {
	    PageInfo<Commodity> cInfo=new PageInfo<Commodity>(1, 100);
	    List<String> keyWordsList=new ArrayList<String>();
	    keyWordsList.add("肉");
	    commodityManager.selectCommodityListByPage(cInfo, keyWordsList, null, Constant.OrderByCondition.priceToAsc, Constant.CommodityState.onShift, Constant.CommodityShowPlace.toC);
    }
	@Test
    @Rollback
    @Transactional
	public void selectCategoryPropertyByCategoryCode() {
	    List<ProductCategoryProperty> productCategory=commodityManager.selectCategoryPropertyByCategoryCode("11001001");
	    List<String> list = new ArrayList<String>();
	    list.add("YLC0000000025");
	    list.add("YLC0000000026");
	    list.add("YLC0000000027");
	    List<Commodity> com = commodityManager.selectCommodityPriceByCodelist(list);
	    assertTrue(productCategory.size()>0);
    }
	
	/**
     * 根据编码集合查询商品集合
     * @param codeList
     * @return
     */
	@Test
	@Rollback
    @Transactional
	public void selectCommodityByCodelistAnd() {
	    List<String> list=new ArrayList<String>();
	    list.add("YLC0000000025");
	    list.add("YLC0000000026");
	    List<CommodityAndComment> commodities=CommodityAndCommentManager.selectCommodityByCodelist(list);
	    assertEquals(2,commodities.size());
    }
	
	@Test
    @Rollback
    @Transactional
	public void selectCommodityListByPageAnd() {
		CommodityAndComment commodity = new CommodityAndComment();
		commodity.setCommodityStatus(3);
		commodity.setCommodityShowPlace("C端");
		ProductCategory category = new ProductCategory();
		category.setCategoryCode("04001001");
		commodity.setCategory(category);
	    PageInfo<CommodityAndComment> cInfo=new PageInfo<CommodityAndComment>(1, 3);
	    List<String> keyWordsList = new ArrayList<String>();
	    keyWordsList.add("肉");
	    PageInfo<CommodityAndComment> cInfo2 = CommodityAndCommentManager.selectCommodityListByPage(cInfo, keyWordsList, null, Constant.OrderByCondition.priceToAsc, Constant.CommodityState.onShift, Constant.CommodityShowPlace.toC);
	    PageInfo<CommodityAndComment> cInfo3 = CommodityAndCommentManager.selectCommodityListByPageForApp(cInfo, commodity);
    }
}

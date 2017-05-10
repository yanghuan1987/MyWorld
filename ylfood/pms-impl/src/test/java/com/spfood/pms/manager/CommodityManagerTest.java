package com.spfood.pms.manager;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.dao.CommodityDao;
import com.spfood.pms.dao.CommodityPictureDao;
import com.spfood.pms.dao.CommodityPropertyDao;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.intf.domain.CommodityProperty;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.intf.domain.ProductCategoryPropertyValue;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class CommodityManagerTest {

	@Resource
	private ProductCategoryManager categoryManager;
	@Resource
	private ProductCategoryDao productCategoryDao;
	@Resource
	private CommodityPropertyDao commodityPropertyDao;
	@Resource
	private CommodityPictureDao commodityPictureDao;
	@Resource
	private ProductManager productManager;
	@Resource
	private CommodityDao commodityDao;
	@Resource
	private ProductCategoryPropertyDao productCategoryPropertyDao;
	@Resource
	private CommodityManager commodityManager;
	@Resource
	private CommodityPictureManager commodityPictureManager;
	private Long testId;

	@Test
	@Rollback
	@Transactional
	public void MainTest(){
		insertCommondity();
		updateCommodity();
		selectCategory();
		updateCommodityStatetest();
		selectProduct();
	}
	
	
	public void insertCommondity(){

		//三级品类
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setCategoryCode("test001");
		productCategory1.setCategoryName("testName");
		
		//品类属性
		ProductCategoryProperty productCategoryProperty = new ProductCategoryProperty();//品类属性对象
		productCategoryProperty.setProductCategory(productCategory1);//品类属性对应的品类
		productCategoryProperty.setCategoryPropertyName("test属性");//属性名称
		productCategoryProperty.setCreateUser("test创建者");
		productCategoryProperty.setCreateDate(new Date());//创建时间
		
		//品类属性值
		ProductCategoryPropertyValue productCategoryPropertyValue = new ProductCategoryPropertyValue();//品类属性值对象
		productCategoryPropertyValue.setCategoryPropertyValue("test属性值");//名称
		productCategoryPropertyValue.setProductCategoryProperty(productCategoryProperty);//属性
		
		List<ProductCategoryPropertyValue> productCategoryPropertyValuesList = new ArrayList<ProductCategoryPropertyValue>();
		productCategoryPropertyValuesList.add(productCategoryPropertyValue);
		
		productCategoryProperty.setProductCategoryPropertyValues(productCategoryPropertyValuesList);
		
		List<ProductCategoryProperty> productCategoryPropertyList = new ArrayList<ProductCategoryProperty>();
		productCategoryPropertyList.add(productCategoryProperty);
		//插入三级品类及属性
		categoryManager.insertOrUpdateThirdLevelCategory(productCategory1, productCategoryPropertyList);
		testId = productCategory1.getId();
		
		//创建产品
		Product product = new Product();
		product.setProductCode("8888");
		product.setProductName("产品测试");
		//产品图片
		ProductPicture picture1 = new ProductPicture("product01",
				"D:/image/001.png");
		List<ProductPicture> productPictures = new ArrayList<ProductPicture>();
		productPictures.add(picture1);
		//产品图片集合
		product.setProductPictures(productPictures);

		ProductProperty property1 = new ProductProperty("product01", "品牌",
				"三全");
		List<ProductProperty> productProperties = new ArrayList<ProductProperty>();
		productProperties.add(property1);
		//产品属性集合
		product.setProductProperties(productProperties);
		product.setCategoryCode(productCategory1.getCategoryCode());
		//插入产品
		productManager.insert(product);
		
		//商品基本属性
		Commodity commodity = new Commodity();
		commodity.setCategory(productCategory1);
		commodity.setCommodityCode("1234567890");
		commodity.setCommodityGs1Code("987654321");
//		commodity.setCommodityProductQuantity(9);
		commodity.setProduct(product);
		commodity.setCommodityName("name");
		commodity.setCommodityCity("city");
		commodity.setCommodityWeightUnit("unit");
		commodity.setCommodityWeight(9d);
		commodity.setCommodityShowPlace("place");
		commodity.setCommodityComment("comment");
		commodity.setCommodityShowName("showName");
		commodity.setCommodityPrice(new BigDecimal(9));
		commodity.setCommoditySalesPrice(new BigDecimal(9));
		commodity.setSalesAmount(9);
		commodity.setDefaultFlag(9);
		commodity.setCommodityQuantity(9);
		commodity.setCommodityStatus(3);
		commodity.setCreateDate(new Date());
		commodity.setCreateUser("test");
		commodity.setLastUpdateDate(new Date());
		commodity.setLastUpdateUser("testl");
		//保存图片
		List<CommodityPicture> list = new ArrayList<CommodityPicture>();
		CommodityPicture commodityPictures = new CommodityPicture();
		commodityPictures.setPictureShowOrder(9);
		commodityPictures.setPictureShowPosition(9);
		commodityPictures.setPictureProperty("prop");
		commodityPictures.setPictureSize("1024");
		commodityPictures.setPictureUrl("text.png");
		commodityPictures.setCommodity(commodity);
		list.add(commodityPictures);
		commodity.setCommodityPictures(list);
		//保存属性
		List<CommodityProperty> listp = new ArrayList<CommodityProperty>();
		CommodityProperty commodityProperty = new CommodityProperty();
		commodityProperty.setCommodity(commodity);
		listp.add(commodityProperty);
		commodity.setCommodityPropertys(listp);
		commodityManager.addCommodity(commodity);
		//验证结果
		selectByPage(commodity,"insert");
		selectByCategoryPageInfo(commodity);
	}
	
	public void selectByCategoryPageInfo(Commodity commodity){
		//测试分页方法
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(1, 1);
		commodityManager.selectCommodityListByPage("1",commodity,pageInfo);
	}
	
	public void selectByPage(Commodity commodity,String function){
		//测试分页方法
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(1, 1);
		PageInfo<Commodity> result = commodityManager.selectCommodityListByPage(commodity, pageInfo, null, null, null);
		if (function.equals("insert")) {
			for(Commodity c : result.getResult()){
				testId = c.getId();
				assertEquals("1234567890", c.getCommodityCode());
				assertEquals("987654321", c.getCommodityGs1Code());
				assertEquals(9, c.getCommodityProductQuantity().intValue());
				assertEquals("name", c.getCommodityName());
				assertEquals("city", c.getCommodityCity());
				assertEquals("unit", c.getCommodityWeightUnit());
				assertEquals(9, c.getCommodityWeight().intValue());
				assertEquals("place", c.getCommodityShowPlace());
				assertEquals("comment", c.getCommodityComment());
				assertEquals(9, c.getCommodityPrice().intValue());
				assertEquals(9, c.getCommoditySalesPrice().intValue());
				assertEquals(9, c.getCommodityQuantity().intValue());
				assertEquals(3, c.getCommodityStatus().intValue());
				for(CommodityProperty pro:c.getCommodityPropertys()){
					assertEquals("品牌", pro.getCommdityPropertyName());
					assertEquals("三全", pro.getCommdityPropertyValue());
				}
			}
			List<CommodityPicture> listpic = new ArrayList<CommodityPicture>();
			
			listpic = commodityPictureManager.selectByCommodityCode("1234567890");
			
			for(CommodityPicture picture : listpic){
				assertEquals("prop", picture.getPictureProperty());
				assertEquals("1024", picture.getPictureSize());
				assertEquals("text.png", picture.getPictureUrl());
				assertEquals(9, picture.getPictureShowOrder().intValue());
				assertEquals(9, picture.getPictureShowPosition().intValue());
			}
		}else if (function.equals("update")) {
			Commodity commodityById = new Commodity();
			//测试ID检索
			commodityById = commodityManager.selectByCommodityCode("1234567890");
			
			testId = commodityById.getId();
			assertEquals("1234567890", commodityById.getCommodityCode());
			assertEquals("987654321U", commodityById.getCommodityGs1Code());
			assertEquals(8, commodityById.getCommodityProductQuantity().intValue());
			assertEquals("nameU", commodityById.getCommodityName());
			assertEquals("cityU", commodityById.getCommodityCity());
			assertEquals("unitU", commodityById.getCommodityWeightUnit());
			assertEquals(8, commodityById.getCommodityWeight().intValue());
			assertEquals("placeU", commodityById.getCommodityShowPlace());
			assertEquals("commentU", commodityById.getCommodityComment());
			assertEquals(8, commodityById.getCommodityPrice().intValue());
			assertEquals(8, commodityById.getCommoditySalesPrice().intValue());
			assertEquals(8, commodityById.getCommodityQuantity().intValue());
			for(CommodityProperty pro:commodityById.getCommodityPropertys()){
				assertEquals("品牌", pro.getCommdityPropertyName());
				assertEquals("三全", pro.getCommdityPropertyValue());
			}
			
			List<CommodityPicture> listpic = new ArrayList<CommodityPicture>();
			listpic = commodityPictureManager.selectByCommodityCode("1234567890");
			for(CommodityPicture picture : listpic){
				assertEquals("propU", picture.getPictureProperty());
				assertEquals("1024U", picture.getPictureSize());
				assertEquals("textU.png", picture.getPictureUrl());
				assertEquals(8, picture.getPictureShowOrder().intValue());
				assertEquals(8, picture.getPictureShowPosition().intValue());
			}
		}
		
	}
	/*
	 * 测试更新
	 */
	public void updateCommodity(){

		//检索得到ID
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryCode("test001");
		Commodity commodity = new Commodity();
		Product product = new Product();
		product.setProductCode("8888");
		commodity.setCategory(productCategory);

		//商品基本属性
		commodity.setId(testId);
		commodity.setCommodityCode("1234567890");
		commodity.setCommodityGs1Code("987654321U");
		commodity.setCommodityComment("commentU");
		commodity.setCommodityWeight(8d);
		commodity.setCommodityCity("cityU");
		commodity.setCommodityWeightUnit("unitU");
		commodity.setCommodityShowPlace("placeU");
		commodity.setProduct(product);
		commodity.setCommodityName("nameU");
		commodity.setCommodityShowName("showNameU");
		commodity.setCommodityPrice(new BigDecimal(8));
		commodity.setCommoditySalesPrice(new BigDecimal(8));
		commodity.setSalesAmount(8);
		commodity.setDefaultFlag(8);
		commodity.setCommodityQuantity(8);
//		commodity.setCommodityProductQuantity(8);
		commodity.setLastUpdateDate(new Date());
		commodity.setLastUpdateUser("testlU");
		//保存图片
		List<CommodityPicture> list = new ArrayList<CommodityPicture>();
		CommodityPicture commodityPictures = new CommodityPicture();
		commodityPictures.setPictureShowOrder(8);
		commodityPictures.setPictureShowPosition(8);
		commodityPictures.setPictureProperty("propU");
		commodityPictures.setPictureSize("1024U");
		commodityPictures.setPictureUrl("textU.png");
		commodityPictures.setCommodity(commodity);
		list.add(commodityPictures);
		commodity.setCommodityPictures(list);
		commodityManager.updateCommodity(commodity);
		//验证结果
		selectByPage(commodity,"update");
	}
	
	/*
	 * 测试更新state
	 */
	public void updateCommodityStatetest(){
		Long[] longs = {testId};
		//更新state
		commodityManager.updateCommodityState(longs, 5);
	}
	
	public void selectCategory(){
		List<ProductCategoryProperty> list = new ArrayList<ProductCategoryProperty>();
		list = commodityManager.selectCategoryPropertyByCategoryCode("test001");
		for(ProductCategoryProperty p : list){
			assertEquals("test属性", p.getCategoryPropertyName());
			for(ProductCategoryPropertyValue pv : p.getProductCategoryPropertyValues()) {
				assertEquals("test属性值", pv.getCategoryPropertyValue());
			}
		}
	}
	public void selectProduct(){
		List<Commodity> list = new ArrayList<Commodity>();
		list = commodityManager.selectCommodityByProduct("8888");
		for(Commodity c:list){
			assertEquals("1234567890", c.getCommodityCode());
			assertEquals("987654321U", c.getCommodityGs1Code());
			assertEquals("nameU", c.getCommodityName());
			assertEquals("showNameU", c.getCommodityShowName());
			assertEquals(5, c.getCommodityStatus().intValue());
			assertEquals(8, c.getCommodityQuantity().intValue());
			assertEquals("placeU", c.getCommodityShowPlace());
			assertEquals("unitU", c.getCommodityWeightUnit());
			assertEquals(8, c.getCommodityWeight().intValue());
		}
	}
	
	@Test
	public void selecthree(){
		Commodity commodity1 = new Commodity();
		commodity1.setCommodityStatus(3);
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(1, 100);
		commodity1.setCommodityStatus(3);
		commodity1.setCommodityShowPlace("C端");
		PageInfo<Commodity> pageInfo2 = 
		commodityManager.selectCommodityListByPage("04001",commodity1,pageInfo);
		List<Commodity> list = new ArrayList<Commodity>();
		list = pageInfo2.getResult();
		if (null != list && list.size() != 0) {
			for (Commodity commodity2 : list) {
				assertEquals(3, commodity2.getCommodityStatus().intValue());
			}
		}
	}
	
	@Test
	public void selectBycategoryCode(){
		Commodity commodity = new Commodity();
		
		ProductCategory category = new ProductCategory();
		category.setCategoryCode("04001001");
		commodity.setCategory(category);
		commodity.setCommodityShowPlace("C端");
		commodity.setCommodityStatus(3);
	    List<Commodity> list2 = commodityManager.selectCommodityByCategory(commodity);
	    list2.size();
	    
	    List<String> lists = new ArrayList<String>();
	    lists.add("YLC0000000100");
	    lists.add("YLC0000000101");
	    lists.add("YLC0000000102");
		Commodity commodityById = new Commodity();
		//测试ID检索
		commodityById = commodityManager.selectByCommodityCode("YLC0000000107");
		commodityById.toString();
		List<Commodity> list = commodityManager.selectListByCommodityCodeList(lists);
		list.size();
	}
	
	
	@Test
	public void sendData() {
		List<Commodity> list = new ArrayList<Commodity>();
		list = commodityManager.sendDate();
		list.size();
	}
	
	@Test
	public void selecComment() throws ParseException{
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(1, 15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dst="2017-4-1";
		String ded="2017-4-14";
		Date st = sdf.parse(dst);
		Date ed = sdf.parse(ded);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryCode("04001001");
		Commodity commodity = new Commodity();
		commodity.setCategory(productCategory);
		List<Integer> orderList = new ArrayList<Integer>();
		orderList.add(10);
		orderList.add(5);
		orderList.add(12);
		CommodityComment commodityComment = new CommodityComment();
		commodityComment.setCommentContent("a");//评价关键字
		commodityComment.setOrderNo("3");//订单编号
		commodityComment.setCommentUsercode("a");//用户昵称
		commodityComment.setCommodityCode("0");//商品ID
		commodityComment.setCommodityCommentLevel(0);//评分等级
		commodityComment.setCommodityCommentDateSt(st);
		commodityComment.setCommodityCommentDateEd(ed);
//		commodity.setCommodityName("1");
//		commodity.setCommodityCommentDomain(commodityComment);
		PageInfo<Commodity> pageInfo2 = commodityManager.selectCommodityCommentByPage(pageInfo, commodity);
		pageInfo2.isSelectCount();
//		for (int i = 0; i < orderList.size(); i++) {
//			commodityComment.setOrderBy(orderList.get(i));
//			commodity.setCommodityCommentDomain(commodityComment);
//			PageInfo<Commodity> pageInfo2 = commodityManager.selectCommodityCommentByPage(pageInfo, commodity);
//			pageInfo2.isSelectCount();
//		}
	}
}

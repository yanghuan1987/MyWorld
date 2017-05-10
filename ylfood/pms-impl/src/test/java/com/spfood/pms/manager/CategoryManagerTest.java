package com.spfood.pms.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.intf.domain.ProductCategoryPropertyValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})

public class CategoryManagerTest {
	
	
	public static void main(String[] args) {
		JSONArray arr = new JSONArray();
		for(int i=0;i<12;i++){
			JSONObject obj = new JSONObject();
			obj.put("c", "c");
			arr.add(obj);
		}
		arr.getObject(0).get("c");
	}

	@Resource
	private ProductCategoryManager categoryManager;
	
	@Resource
	private ProductCategoryDao productCategoryDao;
	
	@Resource
	private ProductCategoryPropertyDao productCategoryPropertyDao;
		
	private Long testId;
	
	private Long testId2;

	private Long testId3;
	
	/**
	 * @return the testId3
	 */
	public Long getTestId3() {
		return testId3;
	}

	/**
	 * @param testId3 the testId3 to set
	 */
	public void setTestId3(Long testId3) {
		this.testId3 = testId3;
	}

	public Long getTestId2() {
		return testId2;
	}

	public void setTestId2(Long testId2) {
		this.testId2 = testId2;
	}
	
	public Long getTestId() {
		return testId;
	}
	
	public void setTestId(Long testId) {
		this.testId = testId;
	}

	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HHmmss");
	Date date=new Date();

	
//	@Before
	public void creatDate(){
		//创建一级品类
		ProductCategory test1 = new ProductCategory();
		test1.setCategoryCode("9999");
		test1.setCategoryName("level1");
		test1.setCategoryAnotherName("another01");
		test1.setCategoryComment("Comment");
		test1.setCreateUser("admin");
		test1.setCreateDate(new Date());
		test1.setLastUpdateUser("admin2");
		test1.setLastUpdateDate(new Date());
		//insert
		ProductCategory ProductCategoryReturn1 = new ProductCategory();
		ProductCategoryReturn1 = categoryManager.insertFirstLevelOrSecondLevelCategroy(test1);
		testId = ProductCategoryReturn1.getId();
		
		//创建二级品类目录
		ProductCategory test2 = new ProductCategory();
		test2.setCategoryCode("9999001");
		test2.setCategoryName("level2");
		test2.setCategoryAnotherName("another02");
		test2.setParentCategory(test1);
		test2.getParentCategory().getCategoryCode();
		test2.setParentCode("9999");
		test2.setCategoryComment("Comment02");
		test2.setCreateUser("admin");
		test2.setCreateDate(new Date());
		test2.setLastUpdateUser("admin2");
		test2.setLastUpdateDate(new Date());
		
		ProductCategory ProductCategoryReturn2 = new ProductCategory();
		ProductCategoryReturn2 = categoryManager.insertFirstLevelOrSecondLevelCategroy(test2);
		testId2 = ProductCategoryReturn2.getId();
		
		//创建三级品类目录
		ProductCategory test3 = new ProductCategory();
		test3.setCategoryCode("9999001001");
		test3.setCategoryName("level3");
		test3.setCategoryAnotherName("another03");
		test3.setParentCategory(test2);
		test3.getParentCategory().getCategoryCode();
		test3.setParentCode("9999001");
		test3.setCategoryComment("Comment03");
		test3.setCreateUser("admin");
		test3.setCreateDate(new Date());
		test3.setLastUpdateUser("admin2");
		test3.setLastUpdateDate(new Date());
		//品类属性
		ProductCategoryProperty productCategoryProperty = new ProductCategoryProperty();//品类属性对象
		productCategoryProperty.setCategoryPropertyDescription("Description");
		productCategoryProperty.setCategoryPropertyName("AttributeName");//属性名称
		productCategoryProperty.setSelectFlag(1);
		productCategoryProperty.setCreateUser("admin");
		productCategoryProperty.setCreateDate(new Date());//创建时间
		productCategoryProperty.setLastUpdateUser("admin2");
		productCategoryProperty.setLastUpdateDate(new Date());
		
		//品类属性值
		ProductCategoryPropertyValue productCategoryPropertyValue = new ProductCategoryPropertyValue();//品类属性值对象
		productCategoryPropertyValue.setCategoryPropertyValue("AttributeValue");//名称
		productCategoryPropertyValue.setProductCategoryProperty(productCategoryProperty);//属性
		
		List<ProductCategoryPropertyValue> productCategoryPropertyValuesList = new ArrayList<ProductCategoryPropertyValue>();
		productCategoryPropertyValuesList.add(productCategoryPropertyValue);
		
		productCategoryProperty.setProductCategoryPropertyValues(productCategoryPropertyValuesList);
		
		List<ProductCategoryProperty> productCategoryPropertyList = new ArrayList<ProductCategoryProperty>();
		productCategoryPropertyList.add(productCategoryProperty);
		
		testId3 = categoryManager.insertOrUpdateThirdLevelCategory(test3, productCategoryPropertyList);
	}
	/*
	 * 测试名字是否重复
	 */
	@Test
	public void selectNameIsHave(){
		ProductCategory selectName = new ProductCategory();
		selectName.setCategoryName("level1");
		Boolean result = categoryManager.selectProductCategoryNameIsHave(selectName);
		assertEquals(true,result);
	}
	/*
	 * 测试code是否重复
	 */
	@Test
	public void selectCodeIsHave(){
		ProductCategory selectCode = new ProductCategory();
		selectCode.setCategoryCode("9999");
		Boolean result = categoryManager.selectProductCategoryCodeIsHave(selectCode);
		assertEquals(true,result);
	}
	/*
	 * 测试能否检索到子品类
	 */
	@Test
	public void selectChildrenByCode(){
		List<ProductCategory> ListResult = new ArrayList<ProductCategory>();
		ListResult = categoryManager.selectCategoryChildrenByCode("9999");
		for(ProductCategory property : ListResult){
			assertEquals("9999001",property.getCategoryCode());
			assertEquals("level2",property.getCategoryName());
			assertEquals("9999",property.getParentCategory().getCategoryCode());
			assertEquals("Comment02",property.getCategoryComment());
		}
	}
	
	/*
	 * 测试刚才插入的数据是否能检索出来
	 */
	
	@Test
	public void selectAll(){
		List<ProductCategory> ListResult = new ArrayList<ProductCategory>();
		ListResult = categoryManager.selectCategoryAll();
		
		
		for(int i=0;i<ListResult.size();i++){
			String strCategoryCode = ListResult.get(i).getCategoryCode();
			if(strCategoryCode.equals("9999")){
				assertEquals("level1",ListResult.get(i).getCategoryName());
				assertEquals("Comment",ListResult.get(i).getCategoryComment());
			}
			if(strCategoryCode.equals("9999001")){
				assertEquals("level2",ListResult.get(i).getCategoryName());
				assertEquals("9999",ListResult.get(i).getParentCategory().getCategoryCode());
				assertEquals("Comment02",ListResult.get(i).getCategoryComment());
			}
			if(strCategoryCode.equals("9999001001")){
				assertEquals("level3",ListResult.get(i).getCategoryName());
				assertEquals("9999001",ListResult.get(i).getParentCategory().getCategoryCode());
				assertEquals("Comment03",ListResult.get(i).getCategoryComment());
			}
		}
	}
	
	/*
	 * 测试update是否成功
	 * 	 * 
	 */
	@Test
	public void updateCategory(){
		ProductCategory testupdate = new ProductCategory();
		testupdate.setId(this.getTestId2());
		testupdate.setCategoryCode("9999001");
		testupdate.setCategoryName("uptest");
		testupdate.setCategoryAnotherName("uptest02");
		testupdate.setCategoryComment("Commentuptest");
		testupdate.setLastUpdateUser("admin2uptest");
		testupdate.setLastUpdateDate(new Date());
		@SuppressWarnings("unused")
		int intCount = categoryManager.updateFirstLevelOrSecondLevelCategroy(testupdate);
		
		ProductCategory protest = categoryManager.selectById(testId2);
		assertEquals("9999001",protest.getCategoryCode());
		assertEquals("uptest",protest.getCategoryName());
		assertEquals("uptest02",protest.getCategoryAnotherName());
		assertEquals("Commentuptest",protest.getCategoryComment());
		assertEquals("admin2uptest",protest.getLastUpdateUser());
	}
	
	/*
	 * 测试事务回滚
	 * 
	 */
	@Test
	public void rollbackinsertThree(){

		ProductCategory ProductRollBack = new ProductCategory();
		//创建三级品类目录
		ProductRollBack.setId(testId3);
		ProductRollBack.setCategoryCode("9999001001");
		ProductRollBack.setCategoryName("level3");
		ProductRollBack.setCategoryAnotherName("another03");
		ProductRollBack.setParentCode("9999001");
		ProductRollBack.setCategoryComment("Comment03");
		ProductRollBack.setCreateUser("admin");
		ProductRollBack.setCreateDate(new Date());
		ProductRollBack.setLastUpdateUser("admin2");
		ProductRollBack.setLastUpdateDate(new Date());
		//品类属性
		ProductCategoryProperty productCategoryProperty = new ProductCategoryProperty();//品类属性对象
		productCategoryProperty.setCategoryPropertyDescription("Description");
		productCategoryProperty.setCategoryPropertyName("AttributeName");//属性名称
		productCategoryProperty.setSelectFlag(1);
		productCategoryProperty.setCreateUser("admin");
		productCategoryProperty.setCreateDate(new Date());//创建时间
		productCategoryProperty.setLastUpdateUser("admin2");
		productCategoryProperty.setLastUpdateDate(new Date());
		
		//品类属性值
		ProductCategoryPropertyValue productCategoryPropertyValue = new ProductCategoryPropertyValue();//品类属性值对象
		productCategoryPropertyValue.setCategoryPropertyValue("test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2" +
				"test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2" +
				"test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2" +
				"test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2" +
				"test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2" +
				"test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2test属性2");//名称
		productCategoryPropertyValue.setProductCategoryProperty(productCategoryProperty);//属性
		
		List<ProductCategoryPropertyValue> productCategoryPropertyValuesList = new ArrayList<ProductCategoryPropertyValue>();
		productCategoryPropertyValuesList.add(productCategoryPropertyValue);
		
		productCategoryProperty.setProductCategoryPropertyValues(productCategoryPropertyValuesList);
		
		List<ProductCategoryProperty> productCategoryPropertyList = new ArrayList<ProductCategoryProperty>();
		productCategoryPropertyList.add(productCategoryProperty);
		try {

			categoryManager.insertOrUpdateThirdLevelCategory(ProductRollBack, productCategoryPropertyList);
			//此处应该会有异常抛出
			fail("Should thorwn out exception");
		} catch (Exception e) {
			ProductCategory productCategory = productCategoryDao.selectByCategoryCode("9999001001");
			List<ProductCategoryProperty> listTemp = productCategory.getProductCategoryPropertys();
			for(ProductCategoryProperty proy : listTemp){
				List<ProductCategoryPropertyValue> listValue = proy.getProductCategoryPropertyValues();
				for(ProductCategoryPropertyValue proyValue : listValue){
					assertEquals("AttributeValue",proyValue.getCategoryPropertyValue());
				}
			}
		}
	}
	
	/**
	 * 删除品类下的属性
	 */
	@Test
	public void deleteByProductCategory(){
		ProductCategory productCategory = categoryManager.selectById(testId3);
		if("9999001001".equals(productCategory.getCategoryCode()) && productCategory.getProductCategoryPropertys().size() > 0){
			productCategoryPropertyDao.deleteByProductCategory(productCategory);
		}
		ProductCategory productCategory1 = categoryManager.selectById(testId3);
		//确认是指定品类
		assertEquals("9999001001",productCategory1.getCategoryCode());
		//确认所属属性已被删除
		assertTrue(productCategory1.getProductCategoryPropertys().size() == 0);
	}
	
	/**
	 * 数据删除
	 */
//	@After
	public void tearDownData(){
		if(testId != null){
			ProductCategory productCategory = categoryManager.selectById(testId);
			if("9999".equals(productCategory.getCategoryCode())){
				productCategoryPropertyDao.deleteByProductCategory(productCategory);
				categoryManager.deleteById(testId);
			}
			ProductCategory ProductCategory = categoryManager.selectById(testId);
			assertNull(ProductCategory);
		}
		
		if(testId2 != null){
			ProductCategory productCategory2 = categoryManager.selectById(testId2);
			if("9999001".equals(productCategory2.getCategoryCode())){
				productCategoryPropertyDao.deleteByProductCategory(productCategory2);
				categoryManager.deleteById(testId2);
			}
			ProductCategory ProductCategory2 = categoryManager.selectById(testId2);
			assertNull(ProductCategory2);
		}
		
		if(testId3 != null){
			ProductCategory productCategory3 = categoryManager.selectById(testId3);
			if("9999001001".equals(productCategory3.getCategoryCode())){
				productCategoryPropertyDao.deleteByProductCategory(productCategory3);
				categoryManager.deleteById(testId3);
			}
			ProductCategory ProductCategory3 = categoryManager.selectById(testId3);
			assertNull(ProductCategory3);
		}
	}
	
	@Test
	public void selectName() {
		List<String> list = new ArrayList<String>();
		list.add("19");
		list.add("33");
		list.add("17001");
		list.add("397001");
		HashMap<String, String> hashMap = new HashMap<String, String>();
//		hashMap = categoryManager.selectForName(list);
		hashMap.toString();
		
	}
	
	@Test
	public void selecComment(){
		PageInfo<ProductCategory> pageInfo = new PageInfo<ProductCategory>(1, 5);
		ProductCategory productCategory = new ProductCategory();
		List<String> codeList = new ArrayList<String>();
		codeList.add(null);
		codeList.add("04");
		codeList.add("04001");
		List<Integer> orderList = new ArrayList<Integer>();
		orderList.add(10);
		orderList.add(5);
		orderList.add(12);
		for (int i = 0; i < codeList.size(); i++) {
			productCategory.setParentCode(codeList.get(i));
			CommodityComment commodityComment = new CommodityComment();
			commodityComment.setOrderBy(orderList.get(i));
			productCategory.setCommodityComment(commodityComment);
			PageInfo<ProductCategory> pageInfo2 = categoryManager.selectCommodityCommentByPage(pageInfo, productCategory);
			pageInfo2.isSelectCount();
		}
	}
	
	@Test
	public void selecComment2(){
//		PageInfo<ProductCategory> pageInfo = new PageInfo<ProductCategory>(1, 5);
//		ProductCategory productCategory = new ProductCategory();
//			PageInfo<ProductCategory> pageInfo2 = categoryManager.selectCommodityCommentByPage(pageInfo, productCategory);
		List<ProductCategory> aCategories = categoryManager.selectCategoryAll();
		aCategories.size();
	}
}

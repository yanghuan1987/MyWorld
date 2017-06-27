package com.spfood.mms.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.spfood.mms.dao.ProductCategoryPropertyDao;
import com.spfood.mms.dao.RawMaterialsDao;
import com.spfood.mms.intf.domain.ProductCategory;
import com.spfood.mms.intf.domain.RawMaterials;
import com.spfood.mms.intf.domain.RawMaterialsPackUnit;
import com.spfood.mms.intf.domain.RawMaterialsPicture;
import com.spfood.mms.intf.domain.RawMaterialsProperty;
import com.spfood.mms.intf.domain.RawMaterialsSearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-context.xml","/local-spring-environment.xml","/spring-dubbo.xml"})
public class RawMaterialsManagerTest {

	@Autowired
	private RawMaterialsManager productManager;
	
	@Autowired
	private RawMaterialsDao productdao;
	
	private long prodctId;
	
	private RawMaterials newRawMaterials;
	
	private RawMaterialsSearchCriteria selectsearchCriteria;
	
	private RawMaterials upRawMaterials;
	
	private	RawMaterialsSearchCriteria AfterUpSearchCriteria;
	
	private RawMaterialsSearchCriteria selectComNewSearchCriteria;

	private RawMaterialsSearchCriteria selectupComNSearchCriteria;
	
	private RawMaterials delRawMaterials;
	
	@Resource
	private ProductCategoryManager categoryManager;
	@Resource
	private ProductCategoryPropertyDao productCategoryPropertyDao;

	@Resource
	private RawMaterialsPackUnitManager proUnitManager;
	
	
	/*
	 * 标准产品插入
	 */
	
	public void setDate(){
		
		//插入用标准产品数据bean
		newRawMaterials = new RawMaterials();
		BigDecimal bigDecimal = new BigDecimal("5");
		// 添加产品以及其关联属性： 图片和产品属性
		List<RawMaterialsPicture> productPictures = new ArrayList<RawMaterialsPicture>();
		List<RawMaterialsProperty> productProperties = new ArrayList<RawMaterialsProperty>();
		List<RawMaterialsPackUnit> packUnits = new ArrayList<RawMaterialsPackUnit>();
		newRawMaterials.setRawMaterialsCode("PPP1234567");
		newRawMaterials.setCategoryCode("99999");
		newRawMaterials.setRawMaterialsGs1Code("1234567890");
		newRawMaterials.setRawMaterialsName("test");
		newRawMaterials.setRawMaterialsShowName("testshow");
		newRawMaterials.setRawMaterialsTerminal(bigDecimal);
		newRawMaterials.setRawMaterialsStatus(5);
		newRawMaterials.setSaleFlag(12);
		newRawMaterials.setRawMaterialsType(0);
		newRawMaterials.setRawMaterialsSpecificationValue(5f);
		newRawMaterials.setRawMaterialsSpecificationUnitFirst("kg");
		newRawMaterials.setRawMaterialsSpecificationUnitSecond("slice");
		newRawMaterials.setCreateDate(new Date());
		newRawMaterials.setCreateUser("testone");
		newRawMaterials.setLastUpdateDate(new Date());
		newRawMaterials.setLastUpdateUser("testonel");
		RawMaterialsPicture picture = new RawMaterialsPicture();
		picture.setRawMaterialsCode("PPP1234567");
		picture.setPictureAddress("test1.png");
		picture.setPictureProperty("a");
		picture.setPictureShowOrder(0);
		picture.setPictureShowPosition(1);
		picture.setPictureSize("1024");
		productPictures.add(picture);
		newRawMaterials.setRawMaterialsPictures(productPictures);
		RawMaterialsProperty property = new RawMaterialsProperty();
		property.setRawMaterialsCode("PPP1234567");
		property.setRawMaterialsPropertyName("testpro");
		property.setRawMaterialsPropertyValue("testval");
		property.setCustomerDefineFlag(0);
		productProperties.add(property);
		newRawMaterials.setRawMaterialsProperties(productProperties);
		RawMaterialsPackUnit packUnit = new RawMaterialsPackUnit();
		packUnit.setRawMaterialsCode("PPP1234567");
		packUnit.setUnitCode("code");
		packUnit.setUnitName("name");
		packUnit.setUnitValue(99);
		packUnits.add(packUnit);
		RawMaterialsPackUnit packUnit1 = new RawMaterialsPackUnit();
		packUnit1.setRawMaterialsCode("PPP1234567");
		packUnit1.setUnitCode("code1");
		packUnit1.setUnitName("name1");
		packUnit1.setUnitValue(100);
		packUnits.add(packUnit1);
		newRawMaterials.setRawMaterialsPackUnit(packUnits);
		
		//标准产品查询bean
		selectsearchCriteria = new RawMaterialsSearchCriteria();
		selectsearchCriteria.setPageNum(1);
		selectsearchCriteria.setPageSize(5);
		selectsearchCriteria.setRawMaterialsCode("PPP1234567");
		selectsearchCriteria.setCategoryCode("99999");
		selectsearchCriteria.setRawMaterialsName("test");
		selectsearchCriteria.setRawMaterialsType(0);
		selectsearchCriteria.setSaleFlag(12);
		RawMaterialsProperty selectproperty = new RawMaterialsProperty();
		selectproperty.setRawMaterialsPropertyName("testpro");
		selectproperty.setRawMaterialsPropertyValue("testval");
		selectsearchCriteria.setRawMaterialsProperty(selectproperty);
//		selectsearchCriteria.setCategoryName("no");
		selectsearchCriteria.setIsBuyUnit(false);
		
		//标准产品更新用bean
		upRawMaterials = new RawMaterials();
		upRawMaterials.setId(prodctId);
		BigDecimal upbigDecimal = new BigDecimal("6");
		// 添加产品以及其关联属性： 图片和产品属性
		List<RawMaterialsPicture> upRawMaterialsPictures = new ArrayList<RawMaterialsPicture>();
		List<RawMaterialsProperty> upRawMaterialsProperties = new ArrayList<RawMaterialsProperty>();
		List<RawMaterialsPackUnit> uppackUnits = new ArrayList<RawMaterialsPackUnit>();
		upRawMaterials.setRawMaterialsCode("PPP1234567");
		upRawMaterials.setCategoryCode("99999");
		upRawMaterials.setRawMaterialsGs1Code("1234567890U");
		upRawMaterials.setRawMaterialsName("testU");
		upRawMaterials.setRawMaterialsShowName("testshowU");
		upRawMaterials.setRawMaterialsTerminal(upbigDecimal);
		upRawMaterials.setRawMaterialsStatus(1);
		upRawMaterials.setSaleFlag(1);
		upRawMaterials.setRawMaterialsType(1);
		upRawMaterials.setRawMaterialsSpecificationValue(5f);
		upRawMaterials.setRawMaterialsSpecificationUnitFirst("kgU");
		upRawMaterials.setRawMaterialsSpecificationUnitSecond("sliceU");
		upRawMaterials.setLastUpdateDate(new Date());
		upRawMaterials.setLastUpdateUser("testonelU");
		RawMaterialsPicture upPicture = new RawMaterialsPicture("PPP1234567", "test1U.png");
		upPicture.setPictureProperty("aU");
		upPicture.setPictureShowOrder(1);
		upPicture.setPictureShowPosition(2);
		upPicture.setPictureSize("1024U");
		upRawMaterialsPictures.add(upPicture);
		upRawMaterials.setRawMaterialsPictures(upRawMaterialsPictures);
		RawMaterialsProperty upProperty = new RawMaterialsProperty("PPP1234567", "testproU", "testvalU");
		upProperty.setCustomerDefineFlag(1);
		upRawMaterialsProperties.add(upProperty);
		upRawMaterials.setRawMaterialsProperties(upRawMaterialsProperties);
		RawMaterialsPackUnit uppackUnit = new RawMaterialsPackUnit();
		uppackUnit.setRawMaterialsCode("PPP1234567");
		uppackUnit.setUnitCode("code3");
		uppackUnit.setUnitName("name3");
		uppackUnit.setUnitValue(991);
		uppackUnits.add(uppackUnit);
		RawMaterialsPackUnit uppackUnit1 = new RawMaterialsPackUnit();
		uppackUnit1.setRawMaterialsCode("PPP1234567");
		uppackUnit1.setUnitCode("code4");
		uppackUnit1.setUnitName("name4");
		uppackUnit1.setUnitValue(1001);
		uppackUnits.add(uppackUnit1);
		upRawMaterials.setRawMaterialsPackUnit(uppackUnits);
		
		//更新后再检索条件bean
		AfterUpSearchCriteria = new RawMaterialsSearchCriteria();
		AfterUpSearchCriteria.setPageNum(1);
		AfterUpSearchCriteria.setPageSize(5);
		AfterUpSearchCriteria.setRawMaterialsCode("PPP1234567");
		AfterUpSearchCriteria.setCategoryCode("99999");
		AfterUpSearchCriteria.setRawMaterialsType(1);
		AfterUpSearchCriteria.setSaleFlag(1);
		RawMaterialsProperty AfterUpproperty = new RawMaterialsProperty();
		AfterUpproperty.setRawMaterialsPropertyName("testproU");
		AfterUpproperty.setRawMaterialsPropertyValue("testvalU");
		AfterUpSearchCriteria.setRawMaterialsProperty(AfterUpproperty);
		AfterUpSearchCriteria.setIsBuyUnit(false);
		
	}
	
//	@Transactional
//	@Rollback
	@Test
	public void mainTest(){
		setDate();
		insertTable();
		List<RawMaterialsPackUnit> list = proUnitManager.selectByRawMaterialsCode("PPP1234567");
		list.size();
		selsctPage();
		selectPicnew();
		updateTable();
		List<RawMaterialsPackUnit> list2 = proUnitManager.selectByRawMaterialsCode("PPP1234567");
		list2.size();
		AllRawMaterialssByCriteria();
		selectPic();
		selectGs1();
		deletetable();
	}
	/*
	 * 普通产品inert
	 */
	public void insertTable(){
		productManager.insert(newRawMaterials);
	}

	/*
	 * 分页查询
	 */
	
	public void selsctPage(){
		BigDecimal bigDecimal = new BigDecimal("5");
		PageInfo<RawMaterials> result = productManager.selectListByPage(selectsearchCriteria);
		PageInfo<RawMaterials> result2 = productManager.selectListByPageSecond(selectsearchCriteria);
		//循环结果集---产品
		for(RawMaterials p:result.getResult()){
			prodctId = p.getId();
			assertEquals("PPP1234567", p.getRawMaterialsCode());
			assertEquals("99999", p.getCategoryCode());
			assertEquals("1234567890", p.getRawMaterialsGs1Code());
			assertEquals("test", p.getRawMaterialsName());
			assertEquals("testshow", p.getRawMaterialsShowName());
			assertEquals(bigDecimal, p.getRawMaterialsTerminal());
			assertEquals(5, p.getRawMaterialsStatus().intValue());
			assertEquals(12, p.getSaleFlag().intValue());
			assertEquals(0, p.getRawMaterialsType().intValue());
			assertEquals(true,5f == p.getRawMaterialsSpecificationValue().floatValue());
			assertEquals("kg", p.getRawMaterialsSpecificationUnitFirst());
			assertEquals("slice", p.getRawMaterialsSpecificationUnitSecond());
			assertEquals("testone", p.getCreateUser());
			assertEquals("testonel", p.getLastUpdateUser());
			//循环结果集---产品属性
			for(RawMaterialsProperty b : p.getRawMaterialsProperties()){
				assertEquals("PPP1234567", b.getRawMaterialsCode());
				assertEquals("testpro", b.getRawMaterialsPropertyName());
				assertEquals("testval", b.getRawMaterialsPropertyValue());
				assertEquals(0, b.getCustomerDefineFlag().intValue());
			}
		}
		
	}
	
	/*
	 * insert产品图片查询
	 */
	public void selectPicnew(){
		List<RawMaterialsPicture> list = new ArrayList<RawMaterialsPicture>();
		list = productManager.getRawMaterialsPictureListByRawMaterialsCode("PPP1234567");
		for(RawMaterialsPicture pic : list){
			assertEquals("PPP1234567", pic.getRawMaterialsCode());
			assertEquals("test1.png", pic.getPictureAddress());
			assertEquals("a", pic.getPictureProperty());
			assertEquals(0, pic.getPictureShowOrder().intValue());
			assertEquals(1, pic.getPictureShowPosition().intValue());
			assertEquals("1024", pic.getPictureSize());
		}
	}
	/*
	 * 标准产品更新
	 */
	
	public void updateTable(){
		
		PageInfo<RawMaterials> result = productManager.selectListByPage(selectsearchCriteria);
		//循环结果集---产品
		for(RawMaterials p:result.getResult()){
			prodctId = p.getId();
		}
		upRawMaterials.setId(prodctId);
		int i = productManager.update(upRawMaterials);
		assertEquals(1, i);
	}
	
	/*
	 *标准产品查询 
	 */
	
	public void AllRawMaterialssByCriteria(){
		List<RawMaterials> list = new ArrayList<RawMaterials>();
		BigDecimal bigDecimal = new BigDecimal("6");
		list = productManager.getAllRawMaterialssByCriteria(AfterUpSearchCriteria);
		delRawMaterials = new RawMaterials();
		for(RawMaterials p :list ){
			delRawMaterials = p;
			prodctId = p.getId();			
			assertEquals("PPP1234567", p.getRawMaterialsCode());
			assertEquals("99999", p.getCategoryCode());
			assertEquals("1234567890U", p.getRawMaterialsGs1Code());
			assertEquals("testU", p.getRawMaterialsName());
			assertEquals("testshowU", p.getRawMaterialsShowName());
			assertEquals(bigDecimal, p.getRawMaterialsTerminal());
			assertEquals(1, p.getRawMaterialsStatus().intValue());
			assertEquals(1, p.getSaleFlag().intValue());
			assertEquals(1, p.getRawMaterialsType().intValue());
			assertEquals(true,5f == p.getRawMaterialsSpecificationValue().floatValue());
			assertEquals("kgU", p.getRawMaterialsSpecificationUnitFirst());
			assertEquals("sliceU", p.getRawMaterialsSpecificationUnitSecond());
			assertEquals("testonelU", p.getLastUpdateUser());
			//循环结果集---产品属性
			for(RawMaterialsProperty b : p.getRawMaterialsProperties()){
				assertEquals("PPP1234567", b.getRawMaterialsCode());
				assertEquals("testproU", b.getRawMaterialsPropertyName());
				assertEquals("testvalU", b.getRawMaterialsPropertyValue());
				assertEquals(1, b.getCustomerDefineFlag().intValue());
			}
		}
	}
	
	/*
	 * 更新产品图片查询
	 */
	public void selectPic(){
		List<RawMaterialsPicture> list = new ArrayList<RawMaterialsPicture>();
		list = productManager.getRawMaterialsPictureListByRawMaterialsCode("PPP1234567");
		delRawMaterials.setRawMaterialsPictures(list);
		for(RawMaterialsPicture pic : list){
			assertEquals("PPP1234567", pic.getRawMaterialsCode());
			assertEquals("test1U.png", pic.getPictureAddress());
			assertEquals("aU", pic.getPictureProperty());
			assertEquals(1, pic.getPictureShowOrder().intValue());
			assertEquals(2, pic.getPictureShowPosition().intValue());
			assertEquals("1024U", pic.getPictureSize());
		}
	}
	
	/*
	 * 品类code查询
	 */
	@Test
	@Rollback
	@Transactional
	public void selectCategoryCode(){
		
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
		categoryManager.insertFirstLevelOrSecondLevelCategroy(test1);
		
		ProductCategory prCategory = new ProductCategory();
		prCategory = productManager.selectByCategoryCode("9999");
		assertEquals("9999", prCategory.getCategoryCode());
	
	}
	
	public void selectGs1(){
		RawMaterials product = new RawMaterials();
		product.setRawMaterialsGs1Code("9999999999");
		boolean b = productManager.selectGS1IsExist(product);
		assertEquals(true, b);
	}
	




	/*
	 * 组合产品update
	 */
	
	public void deletetable(){
		//普通产品删除
		productManager.delete(delRawMaterials);
		
		proUnitManager.deleteById("PPP1234567");
		proUnitManager.deleteById("PPP1234567C");
		
		//普通产品删除确认
		List<RawMaterials> list1 = new ArrayList<RawMaterials>();
		list1 = productManager.getAllRawMaterialssByCriteria(AfterUpSearchCriteria);
		assertTrue(list1.size()==0);
		//普通产品图片删除确认
		List<RawMaterialsPicture> list2 = new ArrayList<RawMaterialsPicture>();
		list2 = productManager.getRawMaterialsPictureListByRawMaterialsCode("PPP1234567");
		assertTrue(list2.size()==0);
	}
	@Test
	public void selectRawMaterialsPackUnit() {
		List<String> list = new ArrayList<String>();
		list.add("YLP0000000151");
		list.add("YLP0000000150");
		list.add("YLP0000000149");

		List<Long> listid = new ArrayList<Long>();
		List<RawMaterials> list2 = new ArrayList<RawMaterials>();
		List<RawMaterials> list3 = new ArrayList<RawMaterials>();
		listid.add(113l);
		listid.add(112l);
		listid.add(111l);
		List<RawMaterialsPackUnit> lisPackUnits = new ArrayList<RawMaterialsPackUnit>();
		list2 = productManager.selectPaclUnitListByRawMaterialsCodes(list);
		for (RawMaterials product : list2) {
			lisPackUnits = product.getRawMaterialsPackUnit();
			lisPackUnits.size();
		}
		
		list3 = productdao.selectListByIds(listid);
		list3.size();
	}
	@Test
	public void selectProducePlaceByRawMaterialsCodes(){

		List<String> list = new ArrayList<String>();
		list.add("YLP0000000087");
		list.add("YLP0000000088");
		list.add("YLP0000000140");
		List<RawMaterials> list2 = new ArrayList<RawMaterials>();
		list2 = productManager.selectProducePlaceByRawMaterialsCodes(list);
		if (list2.size() == 0) {
			
		}
	}
	
	@Test
	public void selectWMS(){
		PageInfo<RawMaterials> pageInfo = new PageInfo<RawMaterials>(1, 5);
		RawMaterialsSearchCriteria searchCriteria = new RawMaterialsSearchCriteria();
		String cateString = "04";
		searchCriteria.setPageNum(1);
		searchCriteria.setPageSize(50);
		searchCriteria.setCategoryCode(cateString);
		searchCriteria.setRawMaterialsName("a");
		searchCriteria.setRawMaterialsCode("1");
		PageInfo<RawMaterials> pageInfo2 = productManager.selectListByPageForWMS(searchCriteria);
		pageInfo2.isSelectCount();
	}
}

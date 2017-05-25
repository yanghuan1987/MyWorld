package com.spfood.pms.manager;

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
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.dao.ProductDao;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductItem;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductProperty;
import com.spfood.pms.intf.domain.ProductSearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-context.xml","/local-spring-environment.xml"})
public class ProductManagerTest {

	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private ProductDao productdao;
	
	private long prodctId;
	
	private long CompositeId;
	
	private Product newProduct;
	
	private ProductSearchCriteria selectsearchCriteria;
	
	private Product upProduct;
	
	private	ProductSearchCriteria AfterUpSearchCriteria;
	
	private CompositeProduct newComproduct;
	
	private ProductSearchCriteria selectComNewSearchCriteria;

	private ProductSearchCriteria selectupComNSearchCriteria;
	
	private Product delProduct;
	
	private CompositeProduct delComproduct;
	
	@Resource
	private ProductCategoryManager categoryManager;
	@Resource
	private ProductCategoryPropertyDao productCategoryPropertyDao;

	@Resource
	private ProductPackUnitManager proUnitManager;
	
	
	/*
	 * 标准产品插入
	 */
	
	public void setDate(){
		
		//插入用标准产品数据bean
		newProduct = new Product();
		BigDecimal bigDecimal = new BigDecimal("5");
		// 添加产品以及其关联属性： 图片和产品属性
		List<ProductPicture> productPictures = new ArrayList<ProductPicture>();
		List<ProductProperty> productProperties = new ArrayList<ProductProperty>();
		List<ProductPackUnit> packUnits = new ArrayList<ProductPackUnit>();
		newProduct.setProductCode("PPP1234567");
		newProduct.setCategoryCode("99999");
		newProduct.setProductGs1Code("1234567890");
		newProduct.setProductName("test");
		newProduct.setProductShowName("testshow");
		newProduct.setProductTerminal(bigDecimal);
		newProduct.setProductStatus(5);
		newProduct.setSaleFlag(12);
		newProduct.setCompositeFlag(0);
		newProduct.setProductSpecificationValue(5f);
		newProduct.setProductSpecificationUnitFirst("kg");
		newProduct.setProductSpecificationUnitSecond("slice");
		newProduct.setCreateDate(new Date());
		newProduct.setCreateUser("testone");
		newProduct.setLastUpdateDate(new Date());
		newProduct.setLastUpdateUser("testonel");
		ProductPicture picture = new ProductPicture("PPP1234567", "test1.png");
		picture.setPictureProperty("a");
		picture.setPictureShowOrder(0);
		picture.setPictureShowPosition(1);
		picture.setPictureSize("1024");
		productPictures.add(picture);
		newProduct.setProductPictures(productPictures);
		ProductProperty property = new ProductProperty("PPP1234567", "testpro", "testval");
		property.setCustomerDefineFlag(0);
		productProperties.add(property);
		newProduct.setProductProperties(productProperties);
		ProductPackUnit packUnit = new ProductPackUnit();
		packUnit.setProductCode("PPP1234567");
		packUnit.setUnitCode("code");
		packUnit.setUnitName("name");
		packUnit.setUnitValue(99f);
		packUnits.add(packUnit);
		ProductPackUnit packUnit1 = new ProductPackUnit();
		packUnit1.setProductCode("PPP1234567");
		packUnit1.setUnitCode("code1");
		packUnit1.setUnitName("name1");
		packUnit1.setUnitValue(100f);
		packUnits.add(packUnit1);
		newProduct.setProductPackUnit(packUnits);
		
		//标准产品查询bean
		selectsearchCriteria = new ProductSearchCriteria();
		selectsearchCriteria.setPageNum(1);
		selectsearchCriteria.setPageSize(5);
		selectsearchCriteria.setProductCode("PPP1234567");
		selectsearchCriteria.setCategoryCode("99999");
		selectsearchCriteria.setProductName("test");
		selectsearchCriteria.setCompositeFlag(0);
		selectsearchCriteria.setSaleFlag(12);
		ProductProperty selectproperty = new ProductProperty();
		selectproperty.setProductPropertyName("testpro");
		selectproperty.setProductPropertyValue("testval");
		selectsearchCriteria.setProductProperty(selectproperty);
//		selectsearchCriteria.setCategoryName("no");
		selectsearchCriteria.setIsBuyUnit(false);
		
		//标准产品更新用bean
		upProduct = new Product();
		upProduct.setId(prodctId);
		BigDecimal upbigDecimal = new BigDecimal("6");
		// 添加产品以及其关联属性： 图片和产品属性
		List<ProductPicture> upProductPictures = new ArrayList<ProductPicture>();
		List<ProductProperty> upProductProperties = new ArrayList<ProductProperty>();
		List<ProductPackUnit> uppackUnits = new ArrayList<ProductPackUnit>();
		upProduct.setProductCode("PPP1234567");
		upProduct.setCategoryCode("99999");
		upProduct.setProductGs1Code("1234567890U");
		upProduct.setProductName("testU");
		upProduct.setProductShowName("testshowU");
		upProduct.setProductTerminal(upbigDecimal);
		upProduct.setProductStatus(1);
		upProduct.setSaleFlag(1);
		upProduct.setCompositeFlag(1);
		upProduct.setProductSpecificationValue(5f);
		upProduct.setProductSpecificationUnitFirst("kgU");
		upProduct.setProductSpecificationUnitSecond("sliceU");
		upProduct.setLastUpdateDate(new Date());
		upProduct.setLastUpdateUser("testonelU");
		ProductPicture upPicture = new ProductPicture("PPP1234567", "test1U.png");
		upPicture.setPictureProperty("aU");
		upPicture.setPictureShowOrder(1);
		upPicture.setPictureShowPosition(2);
		upPicture.setPictureSize("1024U");
		upProductPictures.add(upPicture);
		upProduct.setProductPictures(upProductPictures);
		ProductProperty upProperty = new ProductProperty("PPP1234567", "testproU", "testvalU");
		upProperty.setCustomerDefineFlag(1);
		upProductProperties.add(upProperty);
		upProduct.setProductProperties(upProductProperties);
		ProductPackUnit uppackUnit = new ProductPackUnit();
		uppackUnit.setProductCode("PPP1234567");
		uppackUnit.setUnitCode("code3");
		uppackUnit.setUnitName("name3");
		uppackUnit.setUnitValue(991f);
		uppackUnits.add(uppackUnit);
		ProductPackUnit uppackUnit1 = new ProductPackUnit();
		uppackUnit1.setProductCode("PPP1234567");
		uppackUnit1.setUnitCode("code4");
		uppackUnit1.setUnitName("name4");
		uppackUnit1.setUnitValue(1001f);
		uppackUnits.add(uppackUnit1);
		upProduct.setProductPackUnit(uppackUnits);
		
		//更新后再检索条件bean
		AfterUpSearchCriteria = new ProductSearchCriteria();
		AfterUpSearchCriteria.setPageNum(1);
		AfterUpSearchCriteria.setPageSize(5);
		AfterUpSearchCriteria.setProductCode("PPP1234567");
		AfterUpSearchCriteria.setCategoryCode("99999");
		AfterUpSearchCriteria.setCompositeFlag(1);
		AfterUpSearchCriteria.setSaleFlag(1);
		ProductProperty AfterUpproperty = new ProductProperty();
		AfterUpproperty.setProductPropertyName("testproU");
		AfterUpproperty.setProductPropertyValue("testvalU");
		AfterUpSearchCriteria.setProductProperty(AfterUpproperty);
		AfterUpSearchCriteria.setIsBuyUnit(false);
		
		//新增组合产品查询bean
		selectComNewSearchCriteria = new ProductSearchCriteria();
		selectComNewSearchCriteria.setPageNum(1);
		selectComNewSearchCriteria.setPageSize(5);
		selectComNewSearchCriteria.setProductCode("PPP1234567C");
		selectComNewSearchCriteria.setCategoryCode("99999C");
		selectComNewSearchCriteria.setCompositeFlag(1);
		selectComNewSearchCriteria.setSaleFlag(9);
		ProductProperty selectComNewProperty = new ProductProperty();
		selectComNewProperty.setProductPropertyName("testproC");
		selectComNewProperty.setProductPropertyValue("testvalC");
		selectComNewSearchCriteria.setProductProperty(selectComNewProperty);
//		selectsearchCriteria.setCategoryName("no");
		selectComNewSearchCriteria.setIsBuyUnit(false);
		
		//跟新后组合产品bean
		selectupComNSearchCriteria = new ProductSearchCriteria();
		selectupComNSearchCriteria.setPageNum(1);
		selectupComNSearchCriteria.setPageSize(5);
		selectupComNSearchCriteria.setProductCode("PPP1234567C");
		selectupComNSearchCriteria.setCategoryCode("99999C");
		selectupComNSearchCriteria.setCompositeFlag(8);
		selectupComNSearchCriteria.setSaleFlag(8);
		ProductProperty selectupComProperty = new ProductProperty();
		selectupComProperty.setProductPropertyName("testproCU");
		selectupComProperty.setProductPropertyValue("testvalCU");
		selectupComNSearchCriteria.setProductProperty(selectupComProperty);
//		selectsearchCriteria.setCategoryName("no");
		selectupComNSearchCriteria.setIsBuyUnit(false);
		
	}
	
	@Test
	public void mainTest(){
		setDate();
		insertTable();
		List<ProductPackUnit> list = proUnitManager.selectByProductCode("PPP1234567");
		list.size();
		selsctPage();
		selectPicnew();
		updateTable();
		List<ProductPackUnit> list2 = proUnitManager.selectByProductCode("PPP1234567");
		list2.size();
		AllProductsByCriteria();
		selectPic();
		selectGs1();
		insertComproduct();
		selectComNewProduct();
		selectComNewPic();
		upComProduct();
		selectupComProduct();
		selectUpComPic();
		deletetable();
	}
	/*
	 * 普通产品inert
	 */
	public void insertTable(){
		productManager.insert(newProduct);
	}

	/*
	 * 分页查询
	 */
	
	public void selsctPage(){
		BigDecimal bigDecimal = new BigDecimal("5");
		PageInfo<Product> result = productManager.selectListByPage(selectsearchCriteria);
		//循环结果集---产品
		for(Product p:result.getResult()){
			prodctId = p.getId();
			assertEquals("PPP1234567", p.getProductCode());
			assertEquals("99999", p.getCategoryCode());
			assertEquals("1234567890", p.getProductGs1Code());
			assertEquals("test", p.getProductName());
			assertEquals("testshow", p.getProductShowName());
			assertEquals(bigDecimal, p.getProductTerminal());
			assertEquals(5, p.getProductStatus().intValue());
			assertEquals(12, p.getSaleFlag().intValue());
			assertEquals(0, p.getCompositeFlag().intValue());
			assertEquals(true,5f == p.getProductSpecificationValue().floatValue());
			assertEquals("kg", p.getProductSpecificationUnitFirst());
			assertEquals("slice", p.getProductSpecificationUnitSecond());
			assertEquals("testone", p.getCreateUser());
			assertEquals("testonel", p.getLastUpdateUser());
			//循环结果集---产品属性
			for(ProductProperty b : p.getProductProperties()){
				assertEquals("PPP1234567", b.getProductCode());
				assertEquals("testpro", b.getProductPropertyName());
				assertEquals("testval", b.getProductPropertyValue());
				assertEquals(0, b.getCustomerDefineFlag().intValue());
			}
		}
		
	}
	
	/*
	 * insert产品图片查询
	 */
	public void selectPicnew(){
		List<ProductPicture> list = new ArrayList<ProductPicture>();
		list = productManager.getProductPictureListByProductCode("PPP1234567");
		for(ProductPicture pic : list){
			assertEquals("PPP1234567", pic.getProductCode());
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
		
		PageInfo<Product> result = productManager.selectListByPage(selectsearchCriteria);
		//循环结果集---产品
		for(Product p:result.getResult()){
			prodctId = p.getId();
		}
		upProduct.setId(prodctId);
		int i = productManager.update(upProduct);
		assertEquals(1, i);
	}
	
	/*
	 *标准产品查询 
	 */
	
	public void AllProductsByCriteria(){
		List<Product> list = new ArrayList<Product>();
		BigDecimal bigDecimal = new BigDecimal("6");
		list = productManager.getAllProductsByCriteria(AfterUpSearchCriteria);
		delProduct = new Product();
		for(Product p :list ){
			delProduct = p;
			prodctId = p.getId();			
			assertEquals("PPP1234567", p.getProductCode());
			assertEquals("99999", p.getCategoryCode());
			assertEquals("1234567890U", p.getProductGs1Code());
			assertEquals("testU", p.getProductName());
			assertEquals("testshowU", p.getProductShowName());
			assertEquals(bigDecimal, p.getProductTerminal());
			assertEquals(1, p.getProductStatus().intValue());
			assertEquals(1, p.getSaleFlag().intValue());
			assertEquals(1, p.getCompositeFlag().intValue());
			assertEquals(true,5f == p.getProductSpecificationValue().floatValue());
			assertEquals("kgU", p.getProductSpecificationUnitFirst());
			assertEquals("sliceU", p.getProductSpecificationUnitSecond());
			assertEquals("testonelU", p.getLastUpdateUser());
			//循环结果集---产品属性
			for(ProductProperty b : p.getProductProperties()){
				assertEquals("PPP1234567", b.getProductCode());
				assertEquals("testproU", b.getProductPropertyName());
				assertEquals("testvalU", b.getProductPropertyValue());
				assertEquals(1, b.getCustomerDefineFlag().intValue());
			}
		}
	}
	
	/*
	 * 更新产品图片查询
	 */
	public void selectPic(){
		List<ProductPicture> list = new ArrayList<ProductPicture>();
		list = productManager.getProductPictureListByProductCode("PPP1234567");
		delProduct.setProductPictures(list);
		for(ProductPicture pic : list){
			assertEquals("PPP1234567", pic.getProductCode());
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
		Product product = new Product();
		product.setProductGs1Code("9999999999");
		boolean b = productManager.selectGS1IsExist(product);
		assertEquals(true, b);
	}
	

	/*
	 * 组合产品insert
	 */
	public void insertComproduct(){
		List<Product> list = new ArrayList<Product>();
		list = productManager.getAllProductsByCriteria(AfterUpSearchCriteria);
		for(Product p :list ){
			prodctId = p.getId();
		}
		upProduct.setId(prodctId);
		//组合产品inert bean
		newComproduct	= new CompositeProduct();
		List<ProductItem> newproductItems = new ArrayList<ProductItem>();
		ProductItem comNewProductItem = new ProductItem();
		BigDecimal comNewBigDecimal = new BigDecimal("9");
		// 添加产品以及其关联属性： 图片和产品属性
		List<ProductPicture> comNewproductPictures = new ArrayList<ProductPicture>();
		List<ProductProperty> comNewproductProperties = new ArrayList<ProductProperty>();
		Product comNewproduct = new Product();
		comNewproduct.setProductCode("PPP1234567C");
		comNewproduct.setCategoryCode("99999C");
		comNewproduct.setProductGs1Code("1234567890C");
		comNewproduct.setProductName("testC");
		comNewproduct.setProductShowName("testshowC");
		comNewproduct.setProductTerminal(comNewBigDecimal);
		comNewproduct.setProductStatus(9);
		comNewproduct.setSaleFlag(9);
		comNewproduct.setCompositeFlag(1);
		comNewproduct.setProductSpecificationValue(9f);
		comNewproduct.setProductSpecificationUnitFirst("t");
		comNewproduct.setProductSpecificationUnitSecond("sliceC");
		comNewproduct.setCreateDate(new Date());
		comNewproduct.setCreateUser("testoneC");
		comNewproduct.setLastUpdateDate(new Date());
		comNewproduct.setLastUpdateUser("testonelC");
		ProductPicture comNewpicture = new ProductPicture("PPP1234567C", "test1C.png");
		comNewpicture.setPictureProperty("aC");
		comNewpicture.setPictureShowOrder(9);
		comNewpicture.setPictureShowPosition(9);
		comNewpicture.setPictureSize("1024C");
		comNewproductPictures.add(comNewpicture);
		comNewproduct.setProductPictures(comNewproductPictures);
		newComproduct.setProduct(comNewproduct);
		ProductProperty comNewproperty = new ProductProperty("PPP1234567C", "testproC", "testvalC");
		comNewproperty.setCustomerDefineFlag(9);
		comNewproductProperties.add(comNewproperty);
		comNewproduct.setProductProperties(comNewproductProperties);
		comNewProductItem.setProduct(upProduct);
		comNewProductItem.setQuantity(9);
		newproductItems.add(comNewProductItem);
		newComproduct.setProductItems(newproductItems);
		productManager.insertCompositeProduct(newComproduct);
	}
	

	/*
	 * inser组合产品结果查询
	 */
	public void selectComNewProduct(){
		
		PageInfo<Product> result = productManager.selectListByPage(selectComNewSearchCriteria);
		//循环结果集---产品
		for(Product p:result.getResult()){
			CompositeId = p.getId();
		}
		Product comNewProduct = new Product();
		comNewProduct.setId(CompositeId);
		CompositeProduct comNewproProduct = new CompositeProduct();
		List<ProductItem> list = new ArrayList<ProductItem>();
		
		BigDecimal bigDecimal = new BigDecimal("9");
		comNewproProduct = productManager.getCompositeProduct(comNewProduct);
		list = comNewproProduct.getProductItems();
		
		assertEquals("PPP1234567C", comNewproProduct.getProduct().getProductCode());
		assertEquals("99999C", comNewproProduct.getProduct().getCategoryCode());
		assertEquals("1234567890C", comNewproProduct.getProduct().getProductGs1Code());
		assertEquals("testC", comNewproProduct.getProduct().getProductName());
		assertEquals("testshowC", comNewproProduct.getProduct().getProductShowName());
		assertEquals(bigDecimal, comNewproProduct.getProduct().getProductTerminal());
		assertEquals(9, comNewproProduct.getProduct().getProductStatus().intValue());
		assertEquals(9, comNewproProduct.getProduct().getSaleFlag().intValue());
		assertEquals(1, comNewproProduct.getProduct().getCompositeFlag().intValue());
		assertEquals(true,9f == comNewproProduct.getProduct().getProductSpecificationValue().floatValue());
		assertEquals("t", comNewproProduct.getProduct().getProductSpecificationUnitFirst());
		assertEquals("sliceC", comNewproProduct.getProduct().getProductSpecificationUnitSecond());
		assertEquals("testonelC", comNewproProduct.getProduct().getLastUpdateUser());
		//循环结果集---产品属性
		for(ProductProperty b : comNewproProduct.getProduct().getProductProperties()){
			assertEquals("PPP1234567C", b.getProductCode());
			assertEquals("testproC", b.getProductPropertyName());
			assertEquals("testvalC", b.getProductPropertyValue());
			assertEquals(9, b.getCustomerDefineFlag().intValue());
		}
		for(ProductItem p: list){
			assertEquals(9, p.getQuantity().intValue());
		}
	}

	/*
	 * 新增组合产品图片查询
	 */
	public void selectComNewPic(){
		List<ProductPicture> list = new ArrayList<ProductPicture>();
		list = productManager.getProductPictureListByProductCode("PPP1234567C");
		for(ProductPicture pic : list){
			assertEquals("PPP1234567C", pic.getProductCode());
			assertEquals("test1C.png", pic.getPictureAddress());
			assertEquals("aC", pic.getPictureProperty());
			assertEquals(9, pic.getPictureShowOrder().intValue());
			assertEquals(9, pic.getPictureShowPosition().intValue());
			assertEquals("1024C", pic.getPictureSize());
		}
	}


	/*
	 * 组合产品update
	 */
	public void upComProduct(){
		
		List<Product> listp = new ArrayList<Product>();
		listp = productManager.getAllProductsByCriteria(AfterUpSearchCriteria);
		for(Product p :listp ){
			prodctId = p.getId();
		}
		upProduct.setId(prodctId);
		
		PageInfo<Product> result = productManager.selectListByPage(selectComNewSearchCriteria);
		//循环结果集---产品
		for(Product p:result.getResult()){
			CompositeId = p.getId();
		}
		Product upCProduct = new Product();
		upCProduct.setId(CompositeId);
		BigDecimal bigDecimal = new BigDecimal("8");
		CompositeProduct upComproProduct = new CompositeProduct();
		List<ProductItem> list = new ArrayList<ProductItem>();
		ProductItem upComItem = new ProductItem();
		// 添加产品以及其关联属性： 图片和产品属性
		List<ProductPicture> upProductPictures = new ArrayList<ProductPicture>();
		List<ProductProperty> upProductProperties = new ArrayList<ProductProperty>();
		List<ProductPackUnit> upProductPackUnit = new ArrayList<ProductPackUnit>();
		//组合产品基本信息
		upCProduct.setProductCode("PPP1234567C");
		upCProduct.setCategoryCode("99999C");
		upCProduct.setProductGs1Code("1234567890CU");
		upCProduct.setProductName("testCU");
		upCProduct.setProductShowName("testshowCU");
		upCProduct.setProductTerminal(bigDecimal);
		upCProduct.setProductStatus(8);
		upCProduct.setSaleFlag(8);
		upCProduct.setCompositeFlag(8);
		upCProduct.setProductSpecificationValue(8f);
		upCProduct.setProductSpecificationUnitFirst("tCU");
		upCProduct.setProductSpecificationUnitSecond("sliceCU");
		upCProduct.setLastUpdateDate(new Date());
		upCProduct.setLastUpdateUser("testonelCU");
		//组合产品图片
		ProductPicture upPicture = new ProductPicture("PPP1234567C", "test1CU.png");
		upPicture.setPictureProperty("aCU");
		upPicture.setPictureShowOrder(8);
		upPicture.setPictureShowPosition(8);
		upPicture.setPictureSize("1024CU");
		upProductPictures.add(upPicture);
		upCProduct.setProductPictures(upProductPictures);
		//组合产品属性
		ProductProperty upProperty = new ProductProperty("PPP1234567C", "testproCU", "testvalCU");
		upProperty.setCustomerDefineFlag(8);
		upProductProperties.add(upProperty);
		upCProduct.setProductProperties(upProductProperties);
		ProductPackUnit updaPackUnit = new ProductPackUnit();
		updaPackUnit.setProductCode("PPP1234567C");
		updaPackUnit.setUnitCode("testproCU");
		updaPackUnit.setUnitName("testvalCU");
		updaPackUnit.setUnitValue(121f);
		upProductPackUnit.add(updaPackUnit);
		upCProduct.setProductPackUnit(upProductPackUnit);
		upComproProduct.setProduct(upCProduct);
		//组合产品ID
		upComItem.setProduct(upProduct);
		upComItem.setQuantity(8);
		list.add(upComItem);
		upComproProduct.setProductItems(list);
		productManager.updateCompositeProduct(upComproProduct);
	}
	

	/*
	 * update组合产品结果查询
	 */
	public void selectupComProduct(){
		
		PageInfo<Product> result = productManager.selectListByPage(selectupComNSearchCriteria);
		//循环结果集---产品
		delComproduct = new CompositeProduct();
		for(Product p:result.getResult()){
			delComproduct.setProduct(p);
			CompositeId = p.getId();
		}
		Product upComProduct = new Product();
		upComProduct.setId(CompositeId);
		CompositeProduct upComproProduct = new CompositeProduct();
		List<ProductItem> list = new ArrayList<ProductItem>();
		
		BigDecimal bigDecimal = new BigDecimal("8");
		upComproProduct = productManager.getCompositeProduct(upComProduct);
		list = upComproProduct.getProductItems();
		delComproduct.setProductItems(list);
		assertEquals("PPP1234567C", upComproProduct.getProduct().getProductCode());
		assertEquals("99999C", upComproProduct.getProduct().getCategoryCode());
		assertEquals("1234567890CU", upComproProduct.getProduct().getProductGs1Code());
		assertEquals("testCU", upComproProduct.getProduct().getProductName());
		assertEquals("testshowCU", upComproProduct.getProduct().getProductShowName());
		assertEquals(bigDecimal, upComproProduct.getProduct().getProductTerminal());
		assertEquals(8, upComproProduct.getProduct().getProductStatus().intValue());
		assertEquals(8, upComproProduct.getProduct().getSaleFlag().intValue());
		assertEquals(8, upComproProduct.getProduct().getCompositeFlag().intValue());
		assertEquals(true,8f == upComproProduct.getProduct().getProductSpecificationValue().floatValue());
		assertEquals("tCU", upComproProduct.getProduct().getProductSpecificationUnitFirst());
		assertEquals("sliceCU", upComproProduct.getProduct().getProductSpecificationUnitSecond());
		assertEquals("testonelCU", upComproProduct.getProduct().getLastUpdateUser());
		//循环结果集---产品属性
		for(ProductProperty b : upComproProduct.getProduct().getProductProperties()){
			assertEquals("PPP1234567C", b.getProductCode());
			assertEquals("testproCU", b.getProductPropertyName());
			assertEquals("testvalCU", b.getProductPropertyValue());
			assertEquals(8, b.getCustomerDefineFlag().intValue());
		}
		for(ProductItem p: list){
			assertEquals(8, p.getQuantity().intValue());
		}
	}
	
	/*
	 * update组合产品图片查询
	 */
	public void selectUpComPic(){
		List<ProductPicture> list = new ArrayList<ProductPicture>();
		list = productManager.getProductPictureListByProductCode("PPP1234567C");
		delComproduct.getProduct().setProductPictures(list);
		for(ProductPicture pic : list){
			assertEquals("PPP1234567C", pic.getProductCode());
			assertEquals("test1CU.png", pic.getPictureAddress());
			assertEquals("aCU", pic.getPictureProperty());
			assertEquals(8, pic.getPictureShowOrder().intValue());
			assertEquals(8, pic.getPictureShowPosition().intValue());
			assertEquals("1024CU", pic.getPictureSize());
		}
	}
	
	public void deletetable(){
		//普通产品删除
		productManager.delete(delProduct);
		//组合产品删除
		productManager.deleteCompositeProduct(delComproduct);
		
		proUnitManager.deleteById("PPP1234567");
		proUnitManager.deleteById("PPP1234567C");
		
		//普通产品删除确认
		List<Product> list1 = new ArrayList<Product>();
		list1 = productManager.getAllProductsByCriteria(AfterUpSearchCriteria);
		assertTrue(list1.size()==0);
		//普通产品图片删除确认
		List<ProductPicture> list2 = new ArrayList<ProductPicture>();
		list2 = productManager.getProductPictureListByProductCode("PPP1234567");
		assertTrue(list2.size()==0);
		
		//组合产品删除确认
		PageInfo<Product> result1 = productManager.selectListByPage(selectupComNSearchCriteria);
		//循环结果集---产品
		assertTrue(result1.getResult().size()==0);
		//组合产品图片删除确认
		List<ProductPicture> list4 = new ArrayList<ProductPicture>();
		list4 = productManager.getProductPictureListByProductCode("PPP1234567C");
		assertTrue(list4.size()==0);
	}
	@Test
	public void selectProductPackUnit() {
		List<String> list = new ArrayList<String>();
		list.add("YLP0000000151");
		list.add("YLP0000000150");
		list.add("YLP0000000149");

		List<Long> listid = new ArrayList<Long>();
		List<Product> list2 = new ArrayList<Product>();
		List<Product> list3 = new ArrayList<Product>();
		listid.add(113l);
		listid.add(112l);
		listid.add(111l);
		List<ProductPackUnit> lisPackUnits = new ArrayList<ProductPackUnit>();
		list2 = productManager.selectPaclUnitListByProductCodes(list);
		for (Product product : list2) {
			lisPackUnits = product.getProductPackUnit();
			lisPackUnits.size();
		}
		
		list3 = productdao.selectListByIds(listid);
		list3.size();
	}
	@Test
	public void selectProducePlaceByProductCodes(){

		List<String> list = new ArrayList<String>();
		list.add("YLP0000000087");
		list.add("YLP0000000088");
		list.add("YLP0000000140");
		List<Product> list2 = new ArrayList<Product>();
		list2 = productManager.selectProducePlaceByProductCodes(list);
		if (list2.size() == 0) {
			
		}
	}
	
	@Test
	public void selectWMS(){
		PageInfo<Product> pageInfo = new PageInfo<Product>(1, 5);
		ProductSearchCriteria searchCriteria = new ProductSearchCriteria();
		String cateString = "04";
		searchCriteria.setPageNum(1);
		searchCriteria.setPageSize(50);
		searchCriteria.setCategoryCode(cateString);
//		searchCriteria.setProductName("a");
//		searchCriteria.setProductCode("1");
		PageInfo<Product> pageInfo2 = productManager.selectListByPageForWMS(searchCriteria);
		pageInfo2.isSelectCount();
	}
}

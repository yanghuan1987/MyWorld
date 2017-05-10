/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.dao.ProductDao;
import com.spfood.pms.dao.ProductItemDao;
import com.spfood.pms.dao.ProductPackUnitDao;
import com.spfood.pms.dao.ProductPictureDao;
import com.spfood.pms.dao.ProductPropertyDao;
import com.spfood.pms.intf.domain.CompositeProduct;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductItem;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.intf.domain.ProductPicture;
import com.spfood.pms.intf.domain.ProductProperty;
import com.spfood.pms.intf.domain.ProductSearchCriteria;
import com.spfood.pms.manager.ProductManager;

/**
 * Product Manager实现类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
@Service
public class ProductManagerImpl extends BaseManagerImpl<Product> implements
		ProductManager {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductItemDao productItemDao;

	@Autowired
	private ProductPropertyDao productPropertyDao;

	@Autowired
	private ProductPictureDao productPictureDao;

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductPackUnitDao productPackUnitDao;

	@Override
	protected BaseDao<Product> getBaseDao() {
		return productDao;
	}

	// 查询产品分页信息
	public PageInfo<Product> selectListByPage(ProductSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<Product> pageInfo = new PageInfo<Product>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		Product product = new Product();
		// 实现产品编码的模糊查询
		String productCode = searchCriteria.getProductCode();
		if (productCode != null) {
			product.setProductCode("%" + searchCriteria.getProductCode() + "%");
		}
		if(searchCriteria.getProductName() != null)
			product.setProductName("%"+searchCriteria.getProductName()+"%");
		product.setSaleFlag(searchCriteria.getSaleFlag());
		product.setCompositeFlag(searchCriteria.getCompositeFlag());
		product.setPropertySearchCriteria(searchCriteria.getProductProperty());
		product.setCategoryCode(searchCriteria.getCategoryCode());
		//判断是否传入品类相关参数
		if(searchCriteria.getCategoryCode() != null || searchCriteria.getCategoryName() != null){
			ProductCategory category = new ProductCategory();
			category.setCategoryCode(searchCriteria.getCategoryCode());
			if(searchCriteria.getCategoryName() != null)
				category.setCategoryName("%"+searchCriteria.getCategoryName()+"%");
			product.setCategory(category);
		}
		if(searchCriteria.getIsBuyUnit() != null){
			if(searchCriteria.getIsBuyUnit())
				product.setProductBuyUnitCode("true");
			else
				product.setProductBuyUnitCode("false");
		}
			

		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<Product> pageResult = this.selectListByPage(product,
				"selectBase", "selectCount", pageInfo);
		List<Product> tempList = pageResult.getResult();
		List<Product> resultList = new ArrayList<Product>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = productDao.selectListByIds(idList);
		}
		pageResult.setResult(resultList);
		return pageResult;
	}

	@Override
	@Transactional
	public void insert(Product product) {
		// 添加产品以及其关联属性： 图片和产品属性
		List<ProductPicture> productPictures = product.getProductPictures();
		List<ProductProperty> productProperties = product.getProductProperties();
		List<ProductPackUnit> ProductPackUnit = product.getProductPackUnit();
		product.setCreateDate(new Date());
		product.setLastUpdateDate(new Date());
		// 1. 添加产品主要信息
		productDao.insert(product);
		// 2. 添加产品图片信息
		List<ProductPicture> picList = new ArrayList<ProductPicture>();
		if (productPictures != null && productPictures.size() != 0) {
			for(ProductPicture pic : productPictures){
				pic.setProductCode(product.getProductCode());
				picList.add(pic);
			}
			productPictureDao.insertInBatch(picList);
		}
		// 3. 添加产品属性信息
		List<ProductProperty> propertyList = new ArrayList<ProductProperty>();
		if (productProperties != null && productProperties.size() != 0) {
			for(ProductProperty productProperty : productProperties){
				productProperty.setProductCode(product.getProductCode());
				propertyList.add(productProperty);
			}
			productPropertyDao.insertInBatch(propertyList);
		}
		
		// 3. 添加产品属性信息
		List<ProductPackUnit> ProductPackUnitList = new ArrayList<ProductPackUnit>();
		if (ProductPackUnit != null && ProductPackUnit.size() != 0) {
			for(ProductPackUnit packUnit : ProductPackUnit){
				packUnit.setProductCode(product.getProductCode());
				ProductPackUnitList.add(packUnit);
			}
			productPackUnitDao.insertInBatch(ProductPackUnitList);
		}
		
	}

	@Override
	@Transactional
	public int update(Product product) {
		// 更新产品以及其关联属性： 图片和产品属性
		List<ProductPicture> picList = new ArrayList<ProductPicture>();
		for(ProductPicture pic : product.getProductPictures()){
			pic.setProductCode(product.getProductCode());
			picList.add(pic);
		}
		List<ProductProperty> proList = new ArrayList<ProductProperty>();
		for(ProductProperty pro : product.getProductProperties()){
			pro.setProductCode(product.getProductCode());
			proList.add(pro);
		}
		List<ProductPackUnit> packUnits = new ArrayList<ProductPackUnit>();
		for(ProductPackUnit pUnit : product.getProductPackUnit()){
			pUnit.setProductCode(product.getProductCode());
			packUnits.add(pUnit);
		}
		String productCode = product.getProductCode();
		// 1. 更新产品图片信息
		if (picList != null && picList.size() != 0) {
			// 先删除此类产品的所有图片，再添加图片
			deleteAllPictures(productCode);
			productPictureDao.insertInBatch(picList);

		}
		// 2. 更新产品属性信息
		if (proList != null && proList.size() != 0) {
			// 先删除此类产品的所有属性，再添加属性
			deleteAllProductProperties(productCode);
			productPropertyDao.insertInBatch(proList);
		}
		// 先删除此类产品的所有属性，再添加属性
		deleteAllProductPackUnit(productCode);
		// 2. 更新产品属性信息
		if (packUnits != null && packUnits.size() != 0) {
			
			productPackUnitDao.insertInBatch(packUnits);
		}
		// 3. 更新产品主要信息
		product.setLastUpdateDate(new Date());
		return productDao.updateByIdSelective(product);
	}

	// 删除所有的产品图片
	private void deleteAllProductProperties(String productCode) {
		ProductProperty productProperty = new ProductProperty();
		productProperty.setProductCode(productCode);
		productPropertyDao.delete(productProperty);
	}

	// 删除所有的产品属性
	private void deleteAllPictures(String productCode) {
		ProductPicture productPicture = new ProductPicture();
		productPicture.setProductCode(productCode);
		productPictureDao.delete(productPicture);
	}

	// 删除所有的产品图片
	private void deleteAllProductPackUnit(String productCode) {
		productPackUnitDao.deleteById(productCode);
	}
	
	@Override
	@Transactional
	public int delete(Product product) {
		// 删除产品以及其关联属性： 图片和产品属性
		List<ProductPicture> productPictures = product.getProductPictures();
		List<ProductProperty> productProperties = product
				.getProductProperties();

		String productCode = product.getProductCode();

		// 1. 删除产品图片信息
		if (productPictures != null) {
			deleteAllPictures(productCode);
		}
		// 2. 删除产品属性信息
		if (productProperties != null) {
			deleteAllProductProperties(productCode);
		}
		// 3. 删除产品主要信息
		return productDao.delete(product);
	}

	@Override
	public ProductCategory selectByCategoryCode(String categoryCode) {
		return productCategoryDao.selectByCategoryCode(categoryCode);
	}

	// 查询组合产品的详细信息
	@Override
	public CompositeProduct getCompositeProduct(Product product) {
		CompositeProduct compositeProduct = new CompositeProduct();
		//获得主组合产品的信息
		Product primaryProduct = productDao.selectOne(product);
		ProductItem query = new ProductItem();
		query.setCompositeProductId(product.getId());
		List<ProductItem> productItems = productItemDao.selectList(query);
		compositeProduct.setProduct(primaryProduct);
		compositeProduct.setProductItems(productItems);
		return compositeProduct;
	}

	// 修改组合产品
	@Override
	@Transactional
	public void updateCompositeProduct(CompositeProduct compositeProduct) {
		// 组合产品的修改包含：
		// 1. 产品属性和图片的修改
		Product product = compositeProduct.getProduct();
		if (product != null) {
			product.setLastUpdateDate(new Date());
			this.update(product);
		}
		// 2. 产品项的修改,主要是更新产品的ID和数量，先删除再插入数据
		// 2.1删除产品的关系
		ProductItem query = new ProductItem();
		query.setCompositeProductId(product.getId());
		productItemDao.delete(query);
		// 2.2添加产品的关系
		List<ProductItem> productItems = compositeProduct.getProductItems();
		if(productItems!=null){
			for(ProductItem productItem: productItems){
				productItem.setCompositeProductId(product.getId());
				productItem.setProductId(productItem.getProduct().getId());
				productItemDao.insert(productItem);
			}
		}
	}

	// 新增组合产品
	@Override
	@Transactional
	public void insertCompositeProduct(CompositeProduct compositeProduct) {
		// 组合产品的新增包含：
		// 1. 产品属性和图片的新增
		Product product = compositeProduct.getProduct();
		if (product != null) {
			product.setCreateDate(new Date());
			product.setLastUpdateDate(new Date());
			this.insert(product);
		}
		compositeProduct.getProduct().setId(product.getId());
		// 2. 产品项的新增,主要是更新产品的ID和数量
		List<ProductItem> productItems = compositeProduct.getProductItems();
		if(productItems!=null){
			for(ProductItem productItem: productItems){
				productItem.setCompositeProductId(product.getId());
				productItem.setProductId(productItem.getProduct().getId());
				productItemDao.insert(productItem);
			}
		}
	}

	// 删除组合产品,测试时删除数据用
	@Override
	public void deleteCompositeProduct(CompositeProduct compositeProduct) {
		// 删除组合产品
		Product product = compositeProduct.getProduct();
		Product product2 = new Product();
		// 查询组合产品ID
		product2 = productDao.selectOne(product);
		// 2. 产品项的新增,主要是更新产品的ID和数量
		ProductItem query = new ProductItem();
		query.setCompositeProductId(product2.getId());
		productItemDao.delete(query);
		this.delete(product);
	}
	
	//返回符合条件的标准产品
	@Override
	public List<Product> getAllProductsByCriteria(ProductSearchCriteria searchCriteria){
		Product product = new Product();
		//查询条件转换
		product.setProductCode(searchCriteria.getProductCode());
		product.setSaleFlag(searchCriteria.getSaleFlag());
		product.setCompositeFlag(searchCriteria.getCompositeFlag());
		product.setPropertySearchCriteria(searchCriteria.getProductProperty());
		product.setCategoryCode(searchCriteria.getCategoryCode());
		return productDao.selectList(product);
	}

	@Override
	public List<ProductPicture> getProductPictureListByProductCode(String code) {
		return productPictureDao.getPictureByProductCode(code);
	}
	@Override
	public Boolean selectGS1IsExist (Product product){
		return productDao.selectGS1IsExist(product);
	}
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	public List<Product> selectPaclUnitListByProductCodes(List<String> list){
		return productDao.selectPaclUnitListByProductCodes(list);
	}
	
	//WMS接口-查询温区，产地
	@Override
	public List<Product> selectProducePlaceByProductCodes(List<String> list){
		return productDao.selectProducePlaceByProductCodes(list);
	}

	/**
	 * 为SCM提供接口专用
	 * @param searchCriteria 分页查询对象
	 * @return PageInfo 分页对象
	 */
	@Override
	public PageInfo<Product> selectListByPageSecond(ProductSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<Product> pageInfo = new PageInfo<Product>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		Product product = new Product();
		// 实现产品编码的模糊查询
		String productCode = searchCriteria.getProductCode();
		if (productCode != null) {
			product.setProductCode("%" + searchCriteria.getProductCode() + "%");
		}
		if(searchCriteria.getProductName() != null)
			product.setProductName("%"+searchCriteria.getProductName()+"%");
		product.setSaleFlag(searchCriteria.getSaleFlag());
		product.setCompositeFlag(searchCriteria.getCompositeFlag());
		product.setPropertySearchCriteria(searchCriteria.getProductProperty());
		product.setCategoryCode(searchCriteria.getCategoryCode());
		//判断是否传入品类相关参数
		if(searchCriteria.getCategoryCode() != null || searchCriteria.getCategoryName() != null){
			ProductCategory category = new ProductCategory();
			category.setCategoryCode(searchCriteria.getCategoryCode());
			if(searchCriteria.getCategoryName() != null)
				category.setCategoryName("%"+searchCriteria.getCategoryName()+"%");
			product.setCategory(category);
		}
		if(searchCriteria.getIsBuyUnit() != null){
			if(searchCriteria.getIsBuyUnit())
				product.setProductBuyUnitCode("true");
			else
				product.setProductBuyUnitCode("false");
		}
			

		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<Product> pageResult = this.selectListByPage(product,
				"selectBase", "selectCount", pageInfo);
		List<Product> tempList = pageResult.getResult();
		List<Product> resultList = new ArrayList<Product>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = productDao.selectListByIdsSecond(idList);
		}
		pageResult.setResult(resultList);
		return pageResult;
	}
	
	/**
	 * 为WMS提供接口专用
	 * @param searchCriteria 分页查询对象
	 * @return PageInfo 分页对象
	 */
	@Override
	public PageInfo<Product> selectListByPageForWMS(ProductSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<Product> pageInfo = new PageInfo<Product>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		Product product = new Product();
		if (searchCriteria.getCategoryCode() != null) {
			product.setCategoryCode(searchCriteria.getCategoryCode() + "%");
		}
		// 实现产品编码的模糊查询
		if (searchCriteria.getProductCode() != null) {
			product.setProductCode("%" + searchCriteria.getProductCode() + "%");
		}
		if(searchCriteria.getProductName() != null){
			product.setProductName("%"+searchCriteria.getProductName()+"%");
		}
			
		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<Product> pageResult = this.selectListByPage(product,
				"selectBaseForWMS", "selectCountForWMS", pageInfo);
		List<Product> tempList = pageResult.getResult();
		List<Product> resultList = new ArrayList<Product>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = productDao.selectPageForWMS(idList);
		}
		pageResult.setResult(resultList);
		return pageResult;
	}
}

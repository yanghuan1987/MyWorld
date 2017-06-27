/**
 * 
 */
package com.spfood.mms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.MqMsgtempDao;
import com.spfood.mms.dao.ProductCategoryDao;
import com.spfood.mms.dao.ProductCategoryPropertyDao;
import com.spfood.mms.dao.ProductCategoryPropertyValueDao;
import com.spfood.mms.intf.domain.MqMsgtemp;
import com.spfood.mms.intf.domain.ProductCategory;
import com.spfood.mms.intf.domain.ProductCategoryProperty;
import com.spfood.mms.intf.domain.ProductCategoryPropertyValue;
import com.spfood.mms.intf.utils.Constant.itemType;
import com.spfood.mms.manager.ProductCategoryManager;

/**
 * ProductCategory Manager实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Service
public class ProductCategoryManagerImpl extends BaseManagerImpl<ProductCategory> implements ProductCategoryManager {

	@Resource
	private ProductCategoryDao productCategoryDao;
	
	@Resource
	private ProductCategoryPropertyDao propertiesDao;
	
	@Resource
	private ProductCategoryPropertyValueDao propertiesValueDao;
	
	@Resource
	private MqMsgtempDao mqMsgtempDat;

	@Override
	protected BaseDao<ProductCategory> getBaseDao() {
		return productCategoryDao;
	}

	@Override
	@Transactional
	public Long insertOrUpdateThirdLevelCategory(ProductCategory category,List<ProductCategoryProperty> propertiesList) {
		
		if(category.getId() != null){//品类修改
			propertiesDao.deleteByProductCategory(category);//删除原有属性
			category.setLastUpdateDate(new Date());
			productCategoryDao.updateById(category);//品类修改
		}else{//品类新增
			category.setCreateDate(new Date());
			category.setLastUpdateDate(new Date());
			productCategoryDao.insert(category);
		}
		
		//属性处理
		List<ProductCategoryProperty> propertyList = new ArrayList<ProductCategoryProperty>();
		//给属性添加品类信息
		for(ProductCategoryProperty property : propertiesList){
			//更新品类属性的操作者和日期
			property.setLastUpdateDate(new Date());
			property.setCreateDate(new Date());
			//将最新的品类数据赋值给品类属性对象
			property.setProductCategory(category);
			//批量添加属性
			propertiesDao.insert(property);
			//保存品类属性
			propertyList.add(property);
		}
		
		//属性值处理
		List<ProductCategoryPropertyValue> pvList = new ArrayList<ProductCategoryPropertyValue>();
		//给属性值添加属性信息
		for(ProductCategoryProperty property : propertyList){
			//对属性值进行遍历，将保存后的属性重新赋值给属性值，这样属性值在报错到数据库的时候就能和属性建立关联
			for(ProductCategoryPropertyValue pv : property.getProductCategoryPropertyValues()){
				pv.setProductCategoryProperty(property);
				pvList.add(pv);
			}
		}
		//保存属性值数据
		propertiesValueDao.insertProductCategoryPropertyValueList(pvList);
		//更新MQ临时表
		MqMsgtemp mqMsgtemp = new MqMsgtemp();
		mqMsgtemp.setTypeCode(category.getCategoryCode());
		mqMsgtemp.setType(itemType.category.getValue());
		mqMsgtempDat.insert(mqMsgtemp);
		
		return category.getId();
	}

	@Override
	public Boolean selectProductCategoryNameIsHave(
			ProductCategory productCategory) {
		return productCategoryDao.selectProductCategoryNameIsHave(productCategory);
	}

	@Override
	public Boolean selectProductCategoryCodeIsHave(
			ProductCategory productCategory) {
		return productCategoryDao.selectProductCategoryCodeIsHave(productCategory);
	}

	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		return productCategoryDao.selectCategoryChildrenByCode(categoryCode);
	}

	@Override
	public ProductCategory insertFirstLevelOrSecondLevelCategroy(ProductCategory category) {
		category.setCreateDate(new Date());
		category.setLastUpdateDate(new Date());
		productCategoryDao.insert(category);
		//更新MQ临时表
		MqMsgtemp mqMsgtemp = new MqMsgtemp();
		mqMsgtemp.setTypeCode(category.getCategoryCode());
		mqMsgtemp.setType(itemType.category.getValue());
		mqMsgtempDat.insert(mqMsgtemp);
		return category;
	}

	@Override
	public int updateFirstLevelOrSecondLevelCategroy(ProductCategory category) {
		category.setLastUpdateDate(new Date());
		//更新MQ临时表
		MqMsgtemp mqMsgtemp = new MqMsgtemp();
		mqMsgtemp.setTypeCode(category.getCategoryCode());
		mqMsgtemp.setType(itemType.category.getValue());
		mqMsgtempDat.insert(mqMsgtemp);
		return productCategoryDao.updateById(category);
	}

	@Override
//	@Transactional( readOnly = true )
	public List<ProductCategory> selectCategoryAll() {
		return productCategoryDao.selectCategoryAll();
	}

}

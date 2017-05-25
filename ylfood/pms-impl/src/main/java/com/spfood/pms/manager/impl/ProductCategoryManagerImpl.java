/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.PmsCommodityMsgTempDao;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.dao.ProductCategoryPropertyValueDao;
import com.spfood.pms.dao.impl.ProductCategorySqlIds;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.intf.domain.ProductCategoryPropertyValue;
import com.spfood.pms.intf.utils.Constant.CategoryCodeSize;
import com.spfood.pms.intf.utils.Constant.itemType;
import com.spfood.pms.manager.ProductCategoryManager;

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
	private PmsCommodityMsgTempDao pmsCommodityMsgTempDat;

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
		PmsCommodityMsgTemp pmsCommodityMsgTemp = new PmsCommodityMsgTemp();
		pmsCommodityMsgTemp.setCommdityCode(category.getCategoryCode());
		pmsCommodityMsgTemp.setType(itemType.category.getValue());
		pmsCommodityMsgTempDat.insert(pmsCommodityMsgTemp);
		
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
		PmsCommodityMsgTemp pmsCommodityMsgTemp = new PmsCommodityMsgTemp();
		pmsCommodityMsgTemp.setCommdityCode(category.getCategoryCode());
		pmsCommodityMsgTemp.setType(itemType.category.getValue());
		pmsCommodityMsgTempDat.insert(pmsCommodityMsgTemp);
		return category;
	}

	@Override
	public int updateFirstLevelOrSecondLevelCategroy(ProductCategory category) {
		category.setLastUpdateDate(new Date());
		//更新MQ临时表
		PmsCommodityMsgTemp pmsCommodityMsgTemp = new PmsCommodityMsgTemp();
		pmsCommodityMsgTemp.setCommdityCode(category.getCategoryCode());
		pmsCommodityMsgTemp.setType(itemType.category.getValue());
		pmsCommodityMsgTempDat.insert(pmsCommodityMsgTemp);
		return productCategoryDao.updateById(category);
	}

	@Override
//	@Transactional( readOnly = true )
	public List<ProductCategory> selectCategoryAll() {
		return productCategoryDao.selectCategoryAll();
	}

    @Override
    public PageInfo<ProductCategory> selectCommodityCommentByPage(PageInfo<ProductCategory> pageInfo, ProductCategory productCategory) {
        if(pageInfo == null) return null;
        
        //参数组合
        //获取商品id数组，用于获取商品
        String sqlIdPage = ProductCategorySqlIds.SELECT_PAGE_IN_CATEGORY_ONE_FOR_COMMENT;
        String sqlIdinfo = ProductCategorySqlIds.SELECT_LIST_BY_IDS_ONE;
        int LenCategoryCodeSize = CategoryCodeSize.categorycodelevelone.getValue();
        if (null != productCategory.getParentCode() && !"".equals(productCategory.getParentCode())) {
        	if (productCategory.getParentCode().length() ==  CategoryCodeSize.categorycodelevelone.getValue()) {
        		LenCategoryCodeSize = CategoryCodeSize.categorycodeleveltwo.getValue();
                sqlIdinfo = ProductCategorySqlIds.SELECT_LIST_BY_IDS_TWO;
        	}else if (productCategory.getParentCode().length() ==  CategoryCodeSize.categorycodeleveltwo.getValue()) {
        		LenCategoryCodeSize = CategoryCodeSize.categorycodelevelthree.getValue();
                sqlIdinfo = ProductCategorySqlIds.SELECT_LIST_BY_IDS_THREE;
        	}
		}
        if (null == productCategory.getCommodityComment()) {
        	CommodityComment commodityComment = new CommodityComment();
        	commodityComment.setSubStringSize(LenCategoryCodeSize);
        	productCategory.setCommodityComment(commodityComment);
		}else {
			productCategory.getCommodityComment().setSubStringSize(LenCategoryCodeSize);
		}
        PageInfo<ProductCategory> pageInfos = productCategoryDao.selectListByPage(productCategory,sqlIdPage,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>(15);
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(ProductCategory productCategoryL : pageInfos.getResult()){
                idList.add(productCategoryL.getId());
            }
            List<ProductCategory> clist = productCategoryDao.selectListByIds(sqlIdinfo,idList);
            //得到结果按查询ID排序
            List<ProductCategory> result = new ArrayList<ProductCategory>(15);
            for (int i = 0; i < idList.size(); i++) {
            	for (ProductCategory productCategory2 : clist) {
            		if (idList.get(i).equals(productCategory2.getId())) {
            			result.add(productCategory2);
            		}
				}
			}
            pageInfos.setResult(result);
        }else
            pageInfos.setResult(null);
            
        return pageInfos;
    }
}

/**
 * 
 */
package com.spfood.mms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.MqMsgtempDao;
import com.spfood.mms.dao.ProductCategoryDao;
import com.spfood.mms.dao.RawMaterialsDao;
import com.spfood.mms.dao.RawMaterialsPackUnitDao;
import com.spfood.mms.dao.RawMaterialsPictureDao;
import com.spfood.mms.dao.RawMaterialsPropertyDao;
import com.spfood.mms.intf.domain.MqMsgtemp;
import com.spfood.mms.intf.domain.ProductCategory;
import com.spfood.mms.intf.domain.RawMaterials;
import com.spfood.mms.intf.domain.RawMaterialsPackUnit;
import com.spfood.mms.intf.domain.RawMaterialsPicture;
import com.spfood.mms.intf.domain.RawMaterialsProperty;
import com.spfood.mms.intf.domain.RawMaterialsSearchCriteria;
import com.spfood.mms.intf.utils.Constant.itemType;
import com.spfood.mms.manager.RawMaterialsManager;

/**
 * RawMaterials Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:12:55
 *
 */
@Service
public class RawMaterialsManagerImpl extends BaseManagerImpl<RawMaterials> implements RawMaterialsManager {

	@Autowired
	private RawMaterialsDao dao;

	@Autowired
	private RawMaterialsPropertyDao rawMaterialsPropertyDao;

	@Autowired
	private RawMaterialsPictureDao rawMaterialsPictureDao;

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private RawMaterialsPackUnitDao rawMaterialsPackUnitDao;
	
	@Autowired
	private MqMsgtempDao mqMsgtempDat;
	
	@Override
	protected BaseDao<RawMaterials> getBaseDao() {
		return dao;
	}


	// 查询产品分页信息
	public PageInfo<RawMaterials> selectListByPage(RawMaterialsSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<RawMaterials> pageInfo = new PageInfo<RawMaterials>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		RawMaterials rawMaterials = new RawMaterials();
		// 实现产品编码的模糊查询
		String rawMaterialsCode = searchCriteria.getRawMaterialsCode();
		if (rawMaterialsCode != null && !"".equals(rawMaterialsCode)) {
			rawMaterials.setRawMaterialsCode("%" + searchCriteria.getRawMaterialsCode() + "%");
		}
		if(searchCriteria.getRawMaterialsName() != null && !"".equals(searchCriteria.getRawMaterialsName()))
			rawMaterials.setRawMaterialsName("%"+searchCriteria.getRawMaterialsName()+"%");
		rawMaterials.setSaleFlag(searchCriteria.getSaleFlag());
		rawMaterials.setRawMaterialsType(searchCriteria.getRawMaterialsType());
		rawMaterials.setPropertySearchCriteria(searchCriteria.getRawMaterialsProperty());
		rawMaterials.setCategoryCode(searchCriteria.getCategoryCode());
		//判断是否传入品类相关参数
		if(searchCriteria.getCategoryCode() != null || searchCriteria.getCategoryName() != null){
			ProductCategory category = new ProductCategory();
			category.setCategoryCode(searchCriteria.getCategoryCode());
			if(searchCriteria.getCategoryName() != null)
				category.setCategoryName("%"+searchCriteria.getCategoryName()+"%");
			rawMaterials.setCategory(category);
		}
		if(searchCriteria.getIsBuyUnit() != null){
			if(searchCriteria.getIsBuyUnit())
				rawMaterials.setRawMaterialsBuyUnitCode("true");
			else
				rawMaterials.setRawMaterialsBuyUnitCode("false");
		}
		//产品GS1编码
		if (searchCriteria.getRawMaterialsGs1Code() != null && !"".equals(searchCriteria.getRawMaterialsGs1Code())) {
			rawMaterials.setRawMaterialsGs1Code("%" + searchCriteria.getRawMaterialsGs1Code() + "%");
		}
		//产品状态
		if (searchCriteria.getRawMaterialsStatus() != null) {
			rawMaterials.setRawMaterialsStatus(searchCriteria.getRawMaterialsStatus());
		}
		//产品是否可售
		if (searchCriteria.getSaleFlag() != null) {
			rawMaterials.setSaleFlag(searchCriteria.getSaleFlag());
		}
		//产品温区
		if (searchCriteria.getRawMaterialsTemperatureZoneCode() != null && !"".equals(searchCriteria.getRawMaterialsTemperatureZoneCode())) {
			rawMaterials.setRawMaterialsTemperatureZoneCode(searchCriteria.getRawMaterialsTemperatureZoneCode());
		}
		//是否组合产品
		if (searchCriteria.getRawMaterialsType() != null) {
			rawMaterials.setRawMaterialsType(searchCriteria.getRawMaterialsType());
		}

		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<RawMaterials> pageResult = this.selectListByPage(rawMaterials,
				"selectBase", "selectCount", pageInfo);
		List<RawMaterials> tempList = pageResult.getResult();
		List<RawMaterials> resultList = new ArrayList<RawMaterials>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = dao.selectListByIds(idList);
		}
		pageResult.setResult(resultList);
		return pageResult;
	}

	@Override
	@Transactional
	public void insert(RawMaterials rawMaterials) {
		// 添加产品以及其关联属性： 图片和产品属性
		List<RawMaterialsPicture> rawMaterialsPictures = rawMaterials.getRawMaterialsPictures();
		List<RawMaterialsProperty> rawMaterialsProperties = rawMaterials.getRawMaterialsProperties();
		List<RawMaterialsPackUnit> RawMaterialsPackUnit = rawMaterials.getRawMaterialsPackUnit();
		rawMaterials.setCreateDate(new Date());
		rawMaterials.setLastUpdateDate(new Date());
		// 1. 添加产品主要信息
		dao.insert(rawMaterials);
		// 2. 添加产品图片信息
		List<RawMaterialsPicture> picList = new ArrayList<RawMaterialsPicture>();
		if (rawMaterialsPictures != null && rawMaterialsPictures.size() != 0) {
			for(RawMaterialsPicture pic : rawMaterialsPictures){
				pic.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
				picList.add(pic);
			}
			rawMaterialsPictureDao.insertInBatch(picList);
		}
		// 3. 添加产品属性信息
		List<RawMaterialsProperty> propertyList = new ArrayList<RawMaterialsProperty>();
		if (rawMaterialsProperties != null && rawMaterialsProperties.size() != 0) {
			for(RawMaterialsProperty rawMaterialsProperty : rawMaterialsProperties){
				rawMaterialsProperty.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
				propertyList.add(rawMaterialsProperty);
			}
			rawMaterialsPropertyDao.insertInBatch(propertyList);
		}
		
		// 3. 添加产品属性信息
		List<RawMaterialsPackUnit> RawMaterialsPackUnitList = new ArrayList<RawMaterialsPackUnit>();
		if (RawMaterialsPackUnit != null && RawMaterialsPackUnit.size() != 0) {
			for(RawMaterialsPackUnit packUnit : RawMaterialsPackUnit){
				packUnit.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
				RawMaterialsPackUnitList.add(packUnit);
			}
			rawMaterialsPackUnitDao.insertInBatch(RawMaterialsPackUnitList);
		}

		MqMsgtemp mqMsgtemp = new MqMsgtemp();
		mqMsgtemp.setTypeCode(rawMaterials.getRawMaterialsCode());
		mqMsgtemp.setType(itemType.rawMaterials.getValue());
		mqMsgtempDat.insert(mqMsgtemp);
	}

	@Override
	@Transactional
	public int update(RawMaterials rawMaterials) {
		// 更新产品以及其关联属性： 图片和产品属性
		List<RawMaterialsPicture> picList = new ArrayList<RawMaterialsPicture>();
		for(RawMaterialsPicture pic : rawMaterials.getRawMaterialsPictures()){
			pic.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
			picList.add(pic);
		}
		List<RawMaterialsProperty> proList = new ArrayList<RawMaterialsProperty>();
		for(RawMaterialsProperty pro : rawMaterials.getRawMaterialsProperties()){
			pro.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
			proList.add(pro);
		}
		List<RawMaterialsPackUnit> packUnits = new ArrayList<RawMaterialsPackUnit>();
		for(RawMaterialsPackUnit pUnit : rawMaterials.getRawMaterialsPackUnit()){
			pUnit.setRawMaterialsCode(rawMaterials.getRawMaterialsCode());
			packUnits.add(pUnit);
		}
		String rawMaterialsCode = rawMaterials.getRawMaterialsCode();
		// 1. 更新产品图片信息
		if (picList != null && picList.size() != 0) {
			// 先删除此类产品的所有图片，再添加图片
			deleteAllPictures(rawMaterialsCode);
			rawMaterialsPictureDao.insertInBatch(picList);

		}
		// 2. 更新产品属性信息
		if (proList != null && proList.size() != 0) {
			// 先删除此类产品的所有属性，再添加属性
			deleteAllRawMaterialsProperties(rawMaterialsCode);
			rawMaterialsPropertyDao.insertInBatch(proList);
		}
		// 先删除此类产品的所有属性，再添加属性
		deleteAllRawMaterialsPackUnit(rawMaterialsCode);
		// 2. 更新产品属性信息
		if (packUnits != null && packUnits.size() != 0) {
			
			rawMaterialsPackUnitDao.insertInBatch(packUnits);
		}
		// 3. 更新产品主要信息
		rawMaterials.setLastUpdateDate(new Date());

		MqMsgtemp mqMsgtemp = new MqMsgtemp();
		mqMsgtemp.setTypeCode(rawMaterials.getRawMaterialsCode());
		mqMsgtemp.setType(itemType.rawMaterials.getValue());
		mqMsgtempDat.insert(mqMsgtemp);
		return dao.updateByIdSelective(rawMaterials);
	}

	// 删除所有的产品图片
	private void deleteAllRawMaterialsProperties(String rawMaterialsCode) {
		RawMaterialsProperty rawMaterialsProperty = new RawMaterialsProperty();
		rawMaterialsProperty.setRawMaterialsCode(rawMaterialsCode);
		rawMaterialsPropertyDao.delete(rawMaterialsProperty);
	}

	// 删除所有的产品属性
	private void deleteAllPictures(String rawMaterialsCode) {
		RawMaterialsPicture rawMaterialsPicture = new RawMaterialsPicture();
		rawMaterialsPicture.setRawMaterialsCode(rawMaterialsCode);
		rawMaterialsPictureDao.delete(rawMaterialsPicture);
	}

	// 删除所有的产品图片
	private void deleteAllRawMaterialsPackUnit(String rawMaterialsCode) {
		rawMaterialsPackUnitDao.deleteById(rawMaterialsCode);
	}
	
	@Override
	@Transactional
	public int delete(RawMaterials rawMaterials) {
		// 删除产品以及其关联属性： 图片和产品属性
		List<RawMaterialsPicture> rawMaterialsPictures = rawMaterials.getRawMaterialsPictures();
		List<RawMaterialsProperty> rawMaterialsProperties = rawMaterials
				.getRawMaterialsProperties();

		String rawMaterialsCode = rawMaterials.getRawMaterialsCode();

		// 1. 删除产品图片信息
		if (rawMaterialsPictures != null) {
			deleteAllPictures(rawMaterialsCode);
		}
		// 2. 删除产品属性信息
		if (rawMaterialsProperties != null) {
			deleteAllRawMaterialsProperties(rawMaterialsCode);
		}
		// 3. 删除产品主要信息
		return dao.delete(rawMaterials);
	}

	@Override
	public ProductCategory selectByCategoryCode(String categoryCode) {
		return productCategoryDao.selectByCategoryCode(categoryCode);
	}

	//返回符合条件的标准产品
	@Override
	public List<RawMaterials> getAllRawMaterialssByCriteria(RawMaterialsSearchCriteria searchCriteria){
		RawMaterials rawMaterials = new RawMaterials();
		//查询条件转换
		rawMaterials.setRawMaterialsCode(searchCriteria.getRawMaterialsCode());
		rawMaterials.setSaleFlag(searchCriteria.getSaleFlag());
		rawMaterials.setRawMaterialsType(searchCriteria.getRawMaterialsType());
		rawMaterials.setPropertySearchCriteria(searchCriteria.getRawMaterialsProperty());
		rawMaterials.setCategoryCode(searchCriteria.getCategoryCode());
		return dao.selectList(rawMaterials);
	}

	@Override
	public List<RawMaterialsPicture> getRawMaterialsPictureListByRawMaterialsCode(String code) {
		return rawMaterialsPictureDao.getPictureByRawMaterialsCode(code);
	}
	@Override
	public Boolean selectGS1IsExist (RawMaterials rawMaterials){
		return dao.selectGS1IsExist(rawMaterials);
	}
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	public List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list){
		return dao.selectPaclUnitListByRawMaterialsCodes(list);
	}
	
	//WMS接口-查询温区，产地
	@Override
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list){
		return dao.selectProducePlaceByRawMaterialsCodes(list);
	}

	/**
	 * 为SCM提供接口专用
	 * @param searchCriteria 分页查询对象
	 * @return PageInfo 分页对象
	 */
	@Override
	public PageInfo<RawMaterials> selectListByPageSecond(RawMaterialsSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<RawMaterials> pageInfo = new PageInfo<RawMaterials>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		RawMaterials rawMaterials = new RawMaterials();
		// 实现产品编码的模糊查询
		String rawMaterialsCode = searchCriteria.getRawMaterialsCode();
		if (rawMaterialsCode != null) {
			rawMaterials.setRawMaterialsCode("%" + searchCriteria.getRawMaterialsCode() + "%");
		}
		if(searchCriteria.getRawMaterialsName() != null)
			rawMaterials.setRawMaterialsName("%"+searchCriteria.getRawMaterialsName()+"%");
		rawMaterials.setSaleFlag(searchCriteria.getSaleFlag());
		rawMaterials.setRawMaterialsType(searchCriteria.getRawMaterialsType());
		rawMaterials.setPropertySearchCriteria(searchCriteria.getRawMaterialsProperty());
		rawMaterials.setCategoryCode(searchCriteria.getCategoryCode());
		//判断是否传入品类相关参数
		if(searchCriteria.getCategoryCode() != null || searchCriteria.getCategoryName() != null){
			ProductCategory category = new ProductCategory();
			category.setCategoryCode(searchCriteria.getCategoryCode());
			if(searchCriteria.getCategoryName() != null)
				category.setCategoryName("%"+searchCriteria.getCategoryName()+"%");
			rawMaterials.setCategory(category);
		}
		if(searchCriteria.getIsBuyUnit() != null){
			if(searchCriteria.getIsBuyUnit())
				rawMaterials.setRawMaterialsBuyUnitCode("true");
			else
				rawMaterials.setRawMaterialsBuyUnitCode("false");
		}
			

		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<RawMaterials> pageResult = this.selectListByPage(rawMaterials,
				"selectBase", "selectCount", pageInfo);
		List<RawMaterials> tempList = pageResult.getResult();
		List<RawMaterials> resultList = new ArrayList<RawMaterials>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = dao.selectListByIdsSecond(idList);
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
	public PageInfo<RawMaterials> selectListByPageForWMS(RawMaterialsSearchCriteria searchCriteria) {
		// 分页参数：
		PageInfo<RawMaterials> pageInfo = new PageInfo<RawMaterials>(
				searchCriteria.getPageNum()<1?1:searchCriteria.getPageNum(), searchCriteria.getPageSize());
		// 产品属性参数
		RawMaterials rawMaterials = new RawMaterials();
		if (searchCriteria.getCategoryCode() != null) {
			rawMaterials.setCategoryCode(searchCriteria.getCategoryCode() + "%");
		}
		// 实现产品编码的模糊查询
		if (searchCriteria.getRawMaterialsCode() != null) {
			rawMaterials.setRawMaterialsCode("%" + searchCriteria.getRawMaterialsCode() + "%");
		}
		if(searchCriteria.getRawMaterialsName() != null){
			rawMaterials.setRawMaterialsName("%"+searchCriteria.getRawMaterialsName()+"%");
		}
			
		// 先查询主表信息，在设置从表信息，实现关联分页查询
		PageInfo<RawMaterials> pageResult = this.selectListByPage(rawMaterials,
				"selectBaseForWMS", "selectCountForWMS", pageInfo);
		List<RawMaterials> tempList = pageResult.getResult();
		List<RawMaterials> resultList = new ArrayList<RawMaterials>();
		if (tempList.size() > 0) {
			// 获得所有的产品ID
			List<Long> idList = new ArrayList<Long>();
			for (int i = 0; i < tempList.size(); i++) {
				idList.add(tempList.get(i).getId());
			}

			resultList = dao.selectPageForWMS(idList);
		}
		pageResult.setResult(resultList);
		return pageResult;
	}
}

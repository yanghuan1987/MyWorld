package com.spfood.pms.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.RawMaterialsService;
import com.spfood.pms.intf.domain.RawMaterials;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.RawMaterialsPackUnit;
import com.spfood.pms.intf.domain.RawMaterialsPicture;
import com.spfood.pms.intf.domain.RawMaterialsSearchCriteria;
import com.spfood.pms.manager.RawMaterialsManager;
import com.spfood.pms.manager.RawMaterialsPackUnitManager;
/**
 * RawMaterials Service
 * @author qinbenzhong 2016-12-11
 *
 */
public class RawMaterialsServiceImpl implements RawMaterialsService {
	Logger logger = Logger.getLogger(RawMaterialsServiceImpl.class);
	@Autowired
	private RawMaterialsManager rawMaterialsManager;
	@Autowired
	private RawMaterialsPackUnitManager rawMaterialsPackUnitManager;
	

	@Override
	public PageInfo<RawMaterials> selectListByPage(
			RawMaterialsSearchCriteria rawMaterialsSearchCriteria) {
		logger.debug("Start select by page");
		PageInfo<RawMaterials> result = rawMaterialsManager.selectListByPage(rawMaterialsSearchCriteria);
		logger.debug("end select by page");
		return result;
	}

	@Override
	public ProductCategory selectByCategoryCode(String categoryCode) {
		return rawMaterialsManager.selectByCategoryCode(categoryCode);
	}

	@Override
	public Long addRawMaterials(RawMaterials RawMaterials){
		rawMaterialsManager.insert(RawMaterials);
		return RawMaterials.getId();
	}
	
	@Override
	public Long updateRawMaterials(RawMaterials RawMaterials){
		rawMaterialsManager.update(RawMaterials);
		return RawMaterials.getId();
	}

	//验证产品编码是否重复
	@Override
	public boolean isRawMaterialsCodeDuplicated(RawMaterials RawMaterials){
		Long count = rawMaterialsManager.selectCount(RawMaterials);
		return count>0 ? true: false;
	}

	@Override
	public List<RawMaterials> getAllRawMaterialssByCriteria(RawMaterialsSearchCriteria rawMaterialsSearchCriteria){
		return rawMaterialsManager.getAllRawMaterialssByCriteria(rawMaterialsSearchCriteria);
	}
	
	@Override
	public List<RawMaterialsPicture> getRawMaterialsPictureListByRawMaterialsCode(
			String rawMaterialsCode) {
		return rawMaterialsManager.getRawMaterialsPictureListByRawMaterialsCode(rawMaterialsCode);
	}

	@Override
	public boolean updateRawMaterialsBuyUnit(RawMaterials RawMaterials) throws Exception{
		if(RawMaterials == null || RawMaterials.getId() == null || RawMaterials.getRawMaterialsBuyUnitName() == null)
			return false;
		//创建新的产品对象，将需要更新的产品购买单位名称和购买单位编码赋值
		RawMaterials p = new RawMaterials();
		p.setId(RawMaterials.getId());//ID赋值
		p.setRawMaterialsBuyUnitCode(RawMaterials.getRawMaterialsBuyUnitCode());//编码赋值
		p.setRawMaterialsBuyUnitName(RawMaterials.getRawMaterialsBuyUnitName());//名称赋值
		if(rawMaterialsManager.updateByIdSelective(p) > 0)
			return true;
		return false;
	}
	@Override
	public Boolean selectGS1IsExist(RawMaterials RawMaterials){
		return rawMaterialsManager.selectGS1IsExist(RawMaterials);
	}
	@Override
	public List<RawMaterialsPackUnit> selectRawMaterialsPackUnitByRawMaterialsCode(String rawMaterialsCode){
		return rawMaterialsPackUnitManager.selectByRawMaterialsCode(rawMaterialsCode);
	};
	
	@Override
	public List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list){
		return rawMaterialsManager.selectPaclUnitListByRawMaterialsCodes(list);
	}
	
	@Override
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list){
		return rawMaterialsManager.selectProducePlaceByRawMaterialsCodes(list);
	}

	/**
	 * 为SCM提供接口
	 * @param rawMaterialsSearchCriteria 有分页条件的搜索对象
	 * @return PageInfo 分页对象
	 */
	@Override
	public PageInfo<RawMaterials> selectListByPageSecond(RawMaterialsSearchCriteria rawMaterialsSearchCriteria) {
		logger.debug("Start select second category by page");
		PageInfo<RawMaterials> result = rawMaterialsManager.selectListByPageSecond(rawMaterialsSearchCriteria);
		logger.debug("end select second category by page");
		return result;
	}
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<RawMaterials> selectListByPageForWMS(RawMaterialsSearchCriteria rawMaterialsSearchCriteria) {
		logger.debug("Start select by pageForWMS");
		PageInfo<RawMaterials> result = rawMaterialsManager.selectListByPageForWMS(rawMaterialsSearchCriteria);
		logger.debug("end select by pageForWMS");
		return result;
	}
}

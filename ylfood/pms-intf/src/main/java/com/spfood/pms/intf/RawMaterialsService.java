package com.spfood.pms.intf;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.RawMaterials;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.RawMaterialsPackUnit;
import com.spfood.pms.intf.domain.RawMaterialsPicture;
import com.spfood.pms.intf.domain.RawMaterialsSearchCriteria;

public interface RawMaterialsService {

	public PageInfo<RawMaterials> selectListByPage(RawMaterialsSearchCriteria rawMaterialsSearchCriteria); 
	
	public ProductCategory selectByCategoryCode(String categoryCode);

	public Long addRawMaterials(RawMaterials RawMaterials);
	
	public Long updateRawMaterials(RawMaterials RawMaterials);

	public boolean isRawMaterialsCodeDuplicated(RawMaterials RawMaterials);
	
	public List<RawMaterials> getAllRawMaterialssByCriteria(RawMaterialsSearchCriteria rawMaterialsSearchCriteria);
	
	public List<RawMaterialsPicture> getRawMaterialsPictureListByRawMaterialsCode(String rawMaterialsCode);
	
	public boolean updateRawMaterialsBuyUnit(RawMaterials RawMaterials) throws Exception;

	public Boolean selectGS1IsExist(RawMaterials RawMaterials);
	
	public List<RawMaterialsPackUnit> selectRawMaterialsPackUnitByRawMaterialsCode(String rawMaterialsCode);
	
	public List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list);
	//WMS接口-查询温区，产地
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list);
	
	/**
	 * 为SCM提供接口
	 * @param rawMaterialsSearchCriteria 有分页条件的搜索对象
	 * @return PageInfo 分页对象
	 */
	PageInfo<RawMaterials> selectListByPageSecond(RawMaterialsSearchCriteria rawMaterialsSearchCriteria);
	
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<RawMaterials> selectListByPageForWMS(RawMaterialsSearchCriteria rawMaterialsSearchCriteria); 
}

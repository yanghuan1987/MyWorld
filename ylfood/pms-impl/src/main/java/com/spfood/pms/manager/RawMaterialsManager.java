/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.RawMaterials;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.RawMaterialsPicture;
import com.spfood.pms.intf.domain.RawMaterialsSearchCriteria;

/**
 * RawMaterials Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:12:55
 *
 */
public interface RawMaterialsManager extends BaseManager<RawMaterials> {

	public PageInfo<RawMaterials> selectListByPage(RawMaterialsSearchCriteria searchCriteria);
	
	public int update(RawMaterials RawMaterials);
	
	public ProductCategory selectByCategoryCode(String categoryCode);
	
	public List<RawMaterials> getAllRawMaterialssByCriteria(RawMaterialsSearchCriteria searchCriteria);
	
	public List<RawMaterialsPicture> getRawMaterialsPictureListByRawMaterialsCode(String code);
	
	public Boolean selectGS1IsExist(RawMaterials RawMaterials);
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list);
	
	//WMS接口-查询温区，产地
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list);
	
	/**
	 * 为SCM提供接口专用
	 * @param searchCriteria 分页查询对象
	 * @return PageInfo 分页对象
	 */
	PageInfo<RawMaterials> selectListByPageSecond(RawMaterialsSearchCriteria searchCriteria);
	
	
	/**
	 * 为WMS提供接口
	 * @param categoryCode 三级品类code
	 * @return PageInfo 分页对象
	 */
	public PageInfo<RawMaterials> selectListByPageForWMS(RawMaterialsSearchCriteria searchCriteria); 


}

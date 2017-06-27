/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.RawMaterials;

/**
 *
 * RawMaterials DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:12:55
 *
 */
public interface RawMaterialsDao extends BaseDao<RawMaterials>{
	public List<RawMaterials> selectListByIds(List<Long> idList);
	/*
	 * 检查GS1编码是否重复
	 */
	public Boolean selectGS1IsExist(RawMaterials RawMaterials);
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list);
	
	RawMaterials selectByRawMaterialsCode(RawMaterials RawMaterials);
	
	//WMS接口-查询温区，产地
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list);
	
	/**
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	List<RawMaterials> selectListByIdsSecond(List<Long> idList);
	
	/**
	 * WMS接口
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	List<RawMaterials> selectPageForWMS(List<Long> idList);


}
/**
 * 
 */
package com.spfood.mms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.mms.dao.RawMaterialsDao;
import com.spfood.mms.intf.domain.RawMaterials;

/**
 *
 * RawMaterials DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:12:55
 *
 */
@Repository
public class RawMaterialsDaoImpl extends BaseDaoImpl<RawMaterials> implements RawMaterialsDao {

	//根据ID的集合查询所有的产品
	public List<RawMaterials> selectListByIds(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIds"), idList);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectListByIds", e,getSqlName("selectListByIds"), idList);
		}
	}
	
	//检查GS1编码是否重复
	public Boolean selectGS1IsExist(RawMaterials RawMaterials){
		Assert.notNull(RawMaterials.getRawMaterialsGs1Code());
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectGS1IsExist"), RawMaterials);
		} catch (Exception e) {
			throw new PersistenceException("selectGS1IsExist", e, null, getSqlName("selectbyGS1CodeIsExist"), RawMaterials);
		}
	}
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	public List<RawMaterials> selectPaclUnitListByRawMaterialsCodes(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectPaclUnitListByRawMaterialsCodes"), list);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectPaclUnitListByRawMaterialsCodes", e,getSqlName("selectListByIds"), list);
		}
	}
	
	public RawMaterials selectByRawMaterialsCode(RawMaterials RawMaterials){
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByRawMaterialsCode"), RawMaterials);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectByRawMaterialsCode", e,getSqlName("selectByRawMaterialsCode"), RawMaterials);
		}
	}
	
	/*
	 * WMS接口-查询温区，产地
	 */
	public List<RawMaterials> selectProducePlaceByRawMaterialsCodes(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectProducePlaceByRawMaterialsCodes"), list);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectProducePlaceByRawMaterialsCodes", e,getSqlName("selectProducePlaceByRawMaterialsCodes"), list);
		}
	}

	/**
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	@Override
	public List<RawMaterials> selectListByIdsSecond(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIdsSecond"), idList);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectListByIdsSecond", e,getSqlName("selectListByIdsSecond"), idList);
		}
	}
	
	/**
	 * WMS接口
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	@Override
	public List<RawMaterials> selectPageForWMS(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectPageForWMS"), idList);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterials.dao.selectPageForWMS", e,getSqlName("selectPageForWMS"), idList);
		}
	}
}

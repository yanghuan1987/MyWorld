/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductDao;
import com.spfood.pms.intf.domain.Product;

/**
 *
 * Product DAO实现类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	//根据ID的集合查询所有的产品
	public List<Product> selectListByIds(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIds"), idList);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectListByIds", e,getSqlName("selectListByIds"), idList);
		}
	}
	
	//检查GS1编码是否重复
	public Boolean selectGS1IsExist(Product product){
		Assert.notNull(product.getProductGs1Code());
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectGS1IsExist"), product);
		} catch (Exception e) {
			throw new PersistenceException("selectGS1IsExist", e, null, getSqlName("selectbyGS1CodeIsExist"), product);
		}
	}
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	public List<Product> selectPaclUnitListByProductCodes(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectPaclUnitListByProductCodes"), list);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectPaclUnitListByProductCodes", e,getSqlName("selectListByIds"), list);
		}
	}
	
	public Product selectByProductCode(Product product){
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByProductCode"), product);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectByProductCode", e,getSqlName("selectByProductCode"), product);
		}
	}
	
	/*
	 * WMS接口-查询温区，产地
	 */
	public List<Product> selectProducePlaceByProductCodes(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectProducePlaceByProductCodes"), list);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectProducePlaceByProductCodes", e,getSqlName("selectProducePlaceByProductCodes"), list);
		}
	}

	/**
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	@Override
	public List<Product> selectListByIdsSecond(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIdsSecond"), idList);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectListByIdsSecond", e,getSqlName("selectListByIdsSecond"), idList);
		}
	}
	
	/**
	 * WMS接口
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	@Override
	public List<Product> selectPageForWMS(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectPageForWMS"), idList);
		} catch (Exception e) {
			throw new PersistenceException("product.dao.selectPageForWMS", e,getSqlName("selectPageForWMS"), idList);
		}
	}
}

/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.Product;

/**
 *
 * Product DAO接口类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
public interface ProductDao extends BaseDao<Product>{
	public List<Product> selectListByIds(List<Long> idList);
	/*
	 * 检查GS1编码是否重复
	 */
	public Boolean selectGS1IsExist(Product product);
	/*
	 * 根据产品CODELIST查询产品单位list--对外接口
	 */
	List<Product> selectPaclUnitListByProductCodes(List<String> list);
	
	Product selectByProductCode(Product product);
	
	//WMS接口-查询温区，产地
	public List<Product> selectProducePlaceByProductCodes(List<String> list);
	
	/**
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	List<Product> selectListByIdsSecond(List<Long> idList);
	
	/**
	 * WMS接口
	 * 通过传入的id集合,能够获得二级品类
	 * @param idList 传入的id集合
	 * @return List 产品集合
	 */
	List<Product> selectPageForWMS(List<Long> idList);
}
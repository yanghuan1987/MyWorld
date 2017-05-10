package com.spfood.pms.search.intf;

import java.util.List;

import com.spfood.pms.search.intf.domain.ProductCategory;

/**
 * 品类服务接口
 * @author fengjunchao 2017-02-18
 *
 */
public interface CategorySearchService {

	/**
	 * 根据品类编码获取品类及其父类集合
	 * 集合顺序为：一级、二级、三级
	 * @param commodityCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryByCategoryCode(String categoryCode);

}

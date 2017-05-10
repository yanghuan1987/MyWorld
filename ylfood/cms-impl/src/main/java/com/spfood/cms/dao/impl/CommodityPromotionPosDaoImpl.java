/**
 * 
 */
package com.spfood.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.cms.dao.CommodityPromotionPosDao;
import com.spfood.cms.intf.domain.CommodityPromotionPos;

/**
 *
 * commodityPromotionPos DAO
 *
 * @author zhangpei
 * @createTime 2017-02-06 13:11:29
 *
 */
@Repository
public class CommodityPromotionPosDaoImpl extends BaseDaoImpl<CommodityPromotionPos> implements CommodityPromotionPosDao {

    /**
     * 查询推荐位列表
     */
    @Override
    public List<CommodityPromotionPos> getCommPromotePosList(
            CommodityPromotionPos commodityPromotionPos) {
        try {
          //执行查询
            return sqlSessionTemplate
                    .selectList(getSqlName(CmsSqlIds.POS_SELECT_ALL),commodityPromotionPos);
        } catch (Exception e) {
            throw new PersistenceException(
                    "CommodityPromotionPos.Dao.selectPosAndCommotidy", e,
                    getSqlName(CmsSqlIds.POS_SELECT_ALL),commodityPromotionPos);
        }
    }

}

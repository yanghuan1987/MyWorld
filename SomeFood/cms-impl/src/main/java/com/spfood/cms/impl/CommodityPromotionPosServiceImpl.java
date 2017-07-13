package com.spfood.cms.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.basicservice.intf.ConfigureOptionDTO;
import com.spfood.cms.intf.CommodityPromotionPosService;
import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.intf.utils.CmsConfiguration;
import com.spfood.cms.manager.CommodityPromotionPosManager;
import com.spfood.cms.manager.PromotedCommodityManager;

public class CommodityPromotionPosServiceImpl implements CommodityPromotionPosService{

    @Resource
    private CommodityPromotionPosManager commodityPromotionPosManager;
    
    @Resource
    private PromotedCommodityManager promotedCommodityManager;
    @Resource
    private BasicConfigurationService basicConfigurationService;
    private static final Log log = LogFactory.getLog(CommodityPromotionPosServiceImpl.class);
    /**
     * 通过ID查询推荐位
     * @param id 推荐位ID，不能为空
     */
    
    @Override
    public CommodityPromotionPos selectById(Long id) {
        return commodityPromotionPosManager.selectById(id);
    }
    /**
     * 查询所有推荐位
     */
    @Override
    public List<CommodityPromotionPos> selectAll() {
        //定义所有推荐位list
        List<CommodityPromotionPos> CommodityPromotionPosList=new ArrayList<CommodityPromotionPos>();
        try {
            //查询出所有推荐位信息
            List<ConfigureOptionDTO> list=basicConfigurationService.getConfigureOptionsByName(CmsConfiguration.PROMOTEDCOMMODITY.getValue());
            //定义list储存所有页面
            List<ConfigureOptionDTO> listPos=new ArrayList<ConfigureOptionDTO>();
            //将所有页面添加到listPos
            for (ConfigureOptionDTO configureOptionDTO : list) {
                if (configureOptionDTO.getConfigureItemName().equals(CmsConfiguration.PROMOTEDCOMMODITY.getValue())) {
                    listPos.add(configureOptionDTO);
                }
            }
            //将页面详情(推荐位的最大最小值)信息添加到对应页面
            for (ConfigureOptionDTO configureOptionDTO : listPos) {
                CommodityPromotionPos commodityPromotionPos=new CommodityPromotionPos();
                commodityPromotionPos.setId(configureOptionDTO.getOptionValue());
                commodityPromotionPos.setName(configureOptionDTO.getOptionName());
                for (ConfigureOptionDTO configureOptionDTO1 : list) {
                    if (configureOptionDTO.getOptionValue().equals(configureOptionDTO1.getConfigureItemName())) {
                        if (configureOptionDTO1.getOptionName().equals(CmsConfiguration.MAX.getValue())) {
                            commodityPromotionPos.setMaxSlots(configureOptionDTO1.getOptionValue());
                        }else if (configureOptionDTO1.getOptionName().equals(CmsConfiguration.MIN.getValue())) {
                            commodityPromotionPos.setMinSlots(configureOptionDTO1.getOptionValue());
                        }
                    }
                }
                //将推荐页面对象添加到list
                CommodityPromotionPosList.add(commodityPromotionPos);
            }
        } catch (ParseException e) {
           return null;
        }
        return CommodityPromotionPosList;
    }
    /**
     * 查询指定推荐位的推荐商品
     * @param name 推荐页面名称，不能为空
     */
    @Override
    public List<PromotedCommodity> getCommPromotePosList(String name) {
        log.info("getCommPromotePosList for B2C start");
        return promotedCommodityManager.getPromotedCommoditys(name);
    }

}

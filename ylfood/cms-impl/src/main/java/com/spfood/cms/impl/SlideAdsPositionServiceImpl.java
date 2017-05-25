package com.spfood.cms.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.basicservice.intf.ConfigureOptionDTO;
import com.spfood.cms.intf.SlideAdsPositionService;
import com.spfood.cms.intf.domain.Advertisement;
import com.spfood.cms.intf.domain.SlideAdsPosition;
import com.spfood.cms.intf.utils.CmsConfiguration;
import com.spfood.cms.intf.utils.SlideAdsPositionName;
import com.spfood.cms.manager.AdvertisementManager;

public class SlideAdsPositionServiceImpl implements SlideAdsPositionService {

	
	@Autowired
	private AdvertisementManager advertisementManager;
	
	@Autowired
	private BasicConfigurationService basicConfigurationService;
	
	/**
	 * 获得所有广告位
	 * @return 所有广告位集合
	 */
	@Override
	public List<SlideAdsPosition> selectAll() {
		try {
			// 拿到配置的所有数据
			List<ConfigureOptionDTO> configureOptionDTOs  = basicConfigurationService.getConfigureOptionsByName(CmsConfiguration.BANNER.getValue());
			if (configureOptionDTOs == null) {
				return null;
			} else {
				// 创建放广告位的集合
				List<SlideAdsPosition> slideAdsPositions = new ArrayList<SlideAdsPosition>();
				// 拿到所有的轮播图
				List<Advertisement> advertisements = advertisementManager.selectAll();
				// 遍历集合给广告位对象赋值
				for (ConfigureOptionDTO configureOptionDTO : configureOptionDTOs) {
					// 在配置数据中拿到广告位名称和value并赋值
					if (CmsConfiguration.BANNER.getValue().equals(configureOptionDTO.getConfigureItemName())) {
						SlideAdsPosition slideAdsPosition = new SlideAdsPosition();
						slideAdsPosition.setName(configureOptionDTO.getOptionName());
						slideAdsPosition.setId(configureOptionDTO.getOptionValue());
						// 创建放轮播图集合
						List<Advertisement> advertisementList = new ArrayList<Advertisement>();
						// 遍历拿到的所有轮播图比较是手机端还是PC端放入集合
						for (Advertisement advertisement : advertisements) {
							if (configureOptionDTO.getOptionValue().equals(advertisement.getSlideAdsPosId())) {
								advertisementList.add(advertisement);
							}
						}
						// 把得到的轮播图放入广告位对象
						slideAdsPosition.setAdvertisements(advertisementList);
						// 遍历集合拿到最大值和最小值并赋值
						for (ConfigureOptionDTO configureOptionDTO2 : configureOptionDTOs) {
							if (configureOptionDTO.getOptionValue().equals(configureOptionDTO2.getConfigureItemName())) {
								// 判断是最大值还是最小值
								if (CmsConfiguration.MAX.getValue().equals(configureOptionDTO2.getOptionName())) {
									slideAdsPosition.setMaxSlot(configureOptionDTO2.getOptionValue());
								} else if (CmsConfiguration.MIN.getValue().equals(configureOptionDTO2.getOptionName())) {
									slideAdsPosition.setMinSlot(configureOptionDTO2.getOptionValue());
								}
							}
						}
						slideAdsPositions.add(slideAdsPosition);
					}
				}
				return slideAdsPositions;
			}
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 *  为B2C提供接口,通过名称获得广告位
	 * @param slideAdsPositionName 传入的广告位名称,枚举
	 * @return SlideAdsPosition
	 */
	@Override
	public SlideAdsPosition selectOne(SlideAdsPositionName slideAdsPositionName) {
		// 创建查询用的轮播图对象,并设置查询值
		Advertisement advertisement = new Advertisement();
		advertisement.setSlideAdsPosId(slideAdsPositionName.getValue());
		// 获得对应的轮播图集合
		List<Advertisement> advertisements = advertisementManager.selectList(advertisement);
		// 创建SlideAdsPosition对象用于放返回的轮播图集合
		SlideAdsPosition slideAdsPosition = new SlideAdsPosition();
		slideAdsPosition.setAdvertisements(advertisements);
		return slideAdsPosition;
	}

}

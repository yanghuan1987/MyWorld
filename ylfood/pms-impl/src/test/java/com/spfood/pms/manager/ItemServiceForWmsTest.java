package com.spfood.pms.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml","/spring-dubbo.xml"})
public class ItemServiceForWmsTest {

	@Resource
	private ItemServiceForWmsManager itemServiceForWmsManager;
	
	@SuppressWarnings("unused")
	@Transactional
	@Rollback
	@Test
	public void select(){
		List<ItemServiceForWms> aForWms = itemServiceForWmsManager.SelectAllInfo();
		ItemServiceForWms bForWms = itemServiceForWmsManager.SelectByCommodityCode("YLC0000000122");
		ItemServiceForWms cForWms = itemServiceForWmsManager.SelectByProductCode("YLP0000000181");
		List<String> listC = new ArrayList<String>(3);
		List<String> listP = new ArrayList<String>(3);
		listC.add("YLC0000000122");
		listC.add("YLC0000000124");
		listC.add("YLC0000000027");
		listP.add("YLP0000000206");
		listP.add("YLP0000000207");
		listP.add("YLP0000000208");
		List<ItemServiceForWms> dForWms = itemServiceForWmsManager.SelectByCommodityCodeList(listC);
		List<ItemServiceForWms> eForWms = itemServiceForWmsManager.SelectByProductCodeList(listP);
		List<ItemServiceForWms> mq = itemServiceForWmsManager.sendDate();
		eForWms.size();
	}
}

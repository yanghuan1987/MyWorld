package com.spfood.pms.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class ItemServiceForWmsTest {

	@Resource
	private ItemServiceForWmsManager itemServiceForWmsManager;
	
	@Test
	public void select(){
		List<ItemServiceForWms> aForWms = itemServiceForWmsManager.SelectAllInfo();
		ItemServiceForWms bForWms = itemServiceForWmsManager.SelectByCommodityCode("YLC0000000094");
		ItemServiceForWms cForWms = itemServiceForWmsManager.SelectByProductCode("YLP0000000144");
		List<String> listC = new ArrayList<String>(3);
		List<String> listP = new ArrayList<String>(3);
		listC.add("YLC0000000025");
		listC.add("YLC0000000026");
		listC.add("YLC0000000027");
		listP.add("YLP0000000149");
		listP.add("YLP0000000150");
		listP.add("YLP0000000151");
		List<ItemServiceForWms> dForWms = itemServiceForWmsManager.SelectByCommodityCodeList(listC);
		List<ItemServiceForWms> eForWms = itemServiceForWmsManager.SelectByProductCodeList(listP);
		eForWms.size();
	}
}

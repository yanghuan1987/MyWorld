package com.spfood.mms.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.mms.manager.ItemServiceForWmsManager;

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
		ItemServiceForWms cForWms = itemServiceForWmsManager.SelectByRawMaterialsCode("YLP0000000212");
		List<String> listP = new ArrayList<String>(3);
		listP.add("YLP0000000212");
		listP.add("YLP0000000212");
		listP.add("YLP0000000212");
		List<ItemServiceForWms> eForWms = itemServiceForWmsManager.SelectByRawMaterialsCodeList(listP);
		List<ItemServiceForWms> mq = itemServiceForWmsManager.sendDate();
		eForWms.size();
	}
}

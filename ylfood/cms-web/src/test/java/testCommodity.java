

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.cms.utils.CommodityStatus;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.ProductService;
//import com.spfood.pms.intf.CommoditySearchService;
//import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.Commodity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-context.xml"})
public class testCommodity {

//	@Resource
//	private CommoditySearchService commodityManager;
	
	@Resource
	private CommodityService commodityService;
	
	@Resource
	private ProductService productService;
	/**
     * 根据编码集合查询商品集合
     * @param codeList
     * @return
     */
	@Test
	public void selectCommodityByCodelist() {
	    List<String> list=new ArrayList<String>();
	    list.add("YLP0000000070");
	    list.add("YLP0000000071");
	    Commodity commodity = new Commodity();
//	    List<Commodity> commodities=commodityManager.selectCommodityByCodelist(list);
	    productService.selectProducePlaceByProductCodes(list);
//	    assertEquals(2,commodities.size());
//	    commodity = commodityManager.selectOneByCommodityCode("YLC0000000037");
//	    List<Commodity> list2 = commodityManager.selectCommodityByCategory("04001001");
    }
	
	@Test
	public void selectpage(){
//		Commodity commodity = new Commodity(); 
//		//查询上架的商品
//		commodity.setCommodityStatus(CommodityStatus.UP.getValue());

		Commodity commodity1 = new Commodity();
		commodity1.setCommodityStatus(3);
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(1, 100);
		commodity1.setCommodityStatus(3);
		commodity1.setCommodityShowPlace("C端");
		PageInfo<Commodity> pageInfo2 = 
				commodityService.selectCommodityByCategory("01001001",commodity1,pageInfo);
		List<Commodity> list = new ArrayList<Commodity>();
		list = pageInfo2.getResult();
//		PageInfo<Commodity> pageInfoResult = commodityService.selectCommodityByCategory(null,commodity, new PageInfo<Commodity>(1, 5));
	}
}

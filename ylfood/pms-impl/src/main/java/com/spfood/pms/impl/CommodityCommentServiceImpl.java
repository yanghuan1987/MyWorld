/**
 * 
 */
package com.spfood.pms.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.crm.customer.domain.Customer;
import com.spfood.crm.customer.service.CustomerService;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityCommentService;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.manager.CommodityCommentManager;

/**
 * @author Administrator
 *
 */
public class CommodityCommentServiceImpl implements CommodityCommentService {

	@Autowired
	private CommodityCommentManager commodityCommentManager;
	
    @Autowired 
    private CustomerService customerService;
	
	/* (non-Javadoc)
	 * @see com.spfood.pms.intf.CommodityCommentService#selectCommodityCommentByPage(com.spfood.kernel.dao.PageInfo, com.spfood.pms.intf.domain.CommodityComment)
	 */
	@Override
	public PageInfo<CommodityComment> selectCommodityCommentByPage(
			PageInfo<CommodityComment> pageInfo,
			CommodityComment commodityComment) {
		
		//根据页面的电话查找用户编码，根据编码查询
		if (null != commodityComment.getCommentUserTel() &&
				!"".equals(commodityComment.getCommentUserTel())) {
			//调用CRM接口
			List<Customer> list = customerService.getCustomerByNumber(commodityComment.getCommentUserTel());
			List<String> userCodeList = new ArrayList<String>(300);
			//获取用户编码
			for (Customer customer : list) {
				userCodeList.add(customer.getCustomerId().toString());
			}
			commodityComment.setCommentUsercodeList(userCodeList);
		}
		//查询结果集
		PageInfo<CommodityComment> pageInfo2 = commodityCommentManager.selectCommodityCommentByPage(pageInfo, commodityComment);
		
		 if(pageInfo2.getResult() != null && pageInfo2.getResult().size() > 0){
			 List<CommodityComment> commodityComments = pageInfo2.getResult();
			 // 装用户编码集合
			 List<String> userCodes = new ArrayList<String>(15);
			 for (CommodityComment commodityComment1 : commodityComments) {
				 // 将得到的用户编码放入集合中
				 userCodes.add(commodityComment1.getCommentUsercode());
			 }
			 // 通过用户编码集合获得所有的对象
			Map<String, Customer> customerMap = customerService.getCustomerToMap(userCodes);
			// 遍历集合个商品评级对象赋值
			for (int i = 0; i < commodityComments.size(); i++) {
				String usercode = commodityComments.get(i).getCommentUsercode();
				for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
					if (usercode.equals(entry.getKey())) {
						commodityComments.get(i).setCommentUserTel(entry.getValue().getMobile());
					}
				}
			}
			 pageInfo2.setResult(commodityComments);
		 }
		return pageInfo2;
	}

	/* (non-Javadoc)
	 * @see com.spfood.pms.intf.CommodityCommentService#selectCommodityCommentCount(com.spfood.pms.intf.domain.CommodityComment)
	 */
	@Override
	public CommodityComment selectCommodityCommentCount(
			CommodityComment commodityComment) {
		//根据页面的电话查找用户编码，根据编码查询
		if (null != commodityComment.getCommentUserTel() &&
				!"".equals(commodityComment.getCommentUserTel())) {
			//调用CRM接口
			List<Customer> list = customerService.getCustomerByNumber(commodityComment.getCommentUserTel());
			List<String> userCodeList = new ArrayList<String>(300);
			//获取用户编码
			for (Customer customer : list) {
				userCodeList.add(customer.getCustomerId().toString());
			}
			commodityComment.setCommentUsercodeList(userCodeList);
		}
		return commodityCommentManager.selectCommodityCommentCount(commodityComment);
	}

	/* (non-Javadoc)
	 * @see com.spfood.pms.intf.CommodityCommentService#updateCommodityCommentCountStatus(com.spfood.pms.intf.domain.CommodityComment)
	 */
	@Override
	public boolean updateCommodityCommentCountStatus(
			CommodityComment commodityComment) {
		
		//根据页面的电话查找用户编码，根据编码查询
		if (null != commodityComment.getCommentUserTel() &&
				!"".equals(commodityComment.getCommentUserTel())) {
			//调用CRM接口
			List<Customer> list = customerService.getCustomerByNumber(commodityComment.getCommentUserTel());
			List<String> userCodeList = new ArrayList<String>(300);
			//获取用户编码
			for (Customer customer : list) {
				userCodeList.add(customer.getCustomerId().toString());
			}
			commodityComment.setCommentUsercodeList(userCodeList);
		}
		
		return commodityCommentManager.updateCommodityCommentCountStatus(commodityComment);
	}

}

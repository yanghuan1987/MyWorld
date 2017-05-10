package com.spfood.pms.search.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.crm.customer.domain.Customer;
import com.spfood.crm.customer.service.CustomerService;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.CommodityCommentService;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.CommodityComment;
import com.spfood.pms.search.intf.utils.CommentGrade;
import com.spfood.pms.search.intf.utils.CommentType;
import com.spfood.pms.search.manager.CommodityCommentManager;
import com.spfood.pms.search.manager.CommodityManager;

public class CommodityCommentServiceImpl implements CommodityCommentService {

    @Autowired
    private CommodityCommentManager commodityCommentManager;
    @Autowired
    private CommodityManager commodityManager;
    
    @Autowired 
    private CustomerService customerService;
    
    /**
     * 录入评价信息
     * comments 评价对象集合
     */
    @Override
    public boolean insertComments(List<CommodityComment> comments) {
        Date date=new Date();
        List<String> list =new ArrayList<String>();//储存评价的商品编号
        
        //遍历评价集合
        for (CommodityComment commodityComment : comments) {
            //将当前时间作为评价时间
            commodityComment.setCommentTime(date);
            //设置显示评价
            commodityComment.setCommentStatus(CommentType.SHOW.getValue());
            //将评价的商品编号添加到list
            list.add(commodityComment.getCommodityCode());
        }
        
        //通过商品编号集合查询商品集合
        List<Commodity> commodities=commodityManager.selectCommodityByCodelist(list);
        
        //将品类编码设置到评价对象中
        for (int i = 0; i < commodities.size(); i++) {
            comments.get(i).setCategoryCode(commodities.get(i).getCategory().getCategoryCode());
        }
        
        try {
            //执行批量新增
            commodityCommentManager.insertInBatch(comments);
            return true;
        } catch (Exception e) {
            //返回false新增失败
            return false;
        }
    }
    
    
    /**
     * 为B2C提供查询指定商品评价总条数接口
     * @param commodityCode 商品编码
     * @return 查询到的总条数
     */
    @Override
    public long selectCommodityCommentCount(String commodityCode) {
        return commodityCommentManager.selectCommodityCommentCount(commodityCode);
    }


    /**
     * 为B2C提供查询指定商品评价的分页查询
     * @param commodityCode commodityCode
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @param commentType 评价类型:如 好评
     * @return 分页对象
     */
    @Override
    public PageInfo<CommodityComment> selectCommodityCommentByPage(
            String commodityCode, int pageNum, int pageSize, CommentGrade commentType) {
        PageInfo<CommodityComment> pageInfo = commodityCommentManager.selectCommodityCommentByPage(commodityCode, pageNum, pageSize, commentType);
        if (pageInfo == null) {
            return null;
        }
        if (pageInfo.getResult().size() == 0) {
            return pageInfo;
        }
        List<CommodityComment> commodityComments = pageInfo.getResult();
        // 装用户编码集合
        List<String> userCodes = new ArrayList<String>(); 
        for (CommodityComment commodityComment : commodityComments) {
            // 将得到的用户编码放入集合中
            userCodes.add(commodityComment.getCommentUsercode());
        }
        // 通过用户编码集合获得所有的对象
        Map<String, Customer> customerMap = customerService.getCustomerToMap(userCodes);
        // 遍历集合个商品评级对象赋值
        for (CommodityComment comment : commodityComments) {
            String usercode = comment.getCommentUsercode();
            for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
                if (usercode.equals(entry.getKey())) {
                    comment.setNickname(entry.getValue().getUserName());
                    comment.setHeadUrl(entry.getValue().getProfilePhoto());
                    comment.setMobilePhone(entry.getValue().getMobile());
                }
            }
        }
        pageInfo.setResult(commodityComments);
        return pageInfo;
    }
    
    /**
     * 通过 商品编码获得好评,中评,差评各自总条数
     * @param commodityCode 商品编码
     * @return 对应的map键值对集合  好评:条数
     */
    @Override
    public Map<String, Long> selectCommodityCommentTypeCount(
            String commodityCode) {
        return commodityCommentManager.selectCommodityCommentTypeCount(commodityCode);
    }


    /**
     * B2C提供通过订单编码查询商品评价
     * @param orderNo 订单编号
     * @return 商品评价集合
     */
    @Override
    public List<CommodityComment> selectCommodityCommentByOrderNo(String orderNo) {
        return commodityCommentManager.selectCommodityCommentByOrderNo(orderNo);
    }
}

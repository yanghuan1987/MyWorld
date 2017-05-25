/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.CommodityCommentDao;
import com.spfood.pms.search.intf.domain.CommodityComment;
import com.spfood.pms.search.intf.utils.CommentGrade;
import com.spfood.pms.search.manager.CommodityCommentManager;

/**
 * CommodityComment Manager
 *
 * @author zhangpei
 * @createTime 2017-04-13 13:22:59
 *
 */
@Service
public class CommodityCommentManagerImpl extends BaseManagerImpl<CommodityComment> implements CommodityCommentManager {

	
	private static final Log log = LogFactory.getLog(CommodityCommentManagerImpl.class);
	
	@Autowired
	private CommodityCommentDao dao;
	
	@Override
	protected BaseDao<CommodityComment> getBaseDao() {
		return dao;
	}

	
	/**
	 * 为B2C提供查询指定商品评价总条数接口
	 * @param commodityCode 商品编码
	 * @return 查询到的总条数
	 */
	@Override
	public Long selectCommodityCommentCount(String commodityCode) {
		log.info("get commodityComment count");
		if (commodityCode == null || "".equals(commodityCode.trim())) {
			return 0L;
		}
		return dao.selectCommodityCommentCount(commodityCode.trim());
	}


	/**
	 * 为B2C提供查询指定商品评价的分页查询
	 * @param commodityCode commodityCode
	 * @param pageNum 页数
	 * @param pageSize 每页条数
	 * @return 分页对象
	 */
	@Override
	public PageInfo<CommodityComment> selectCommodityCommentByPage(
			String commodityCode, int pageNum, int pageSize, CommentGrade commentType) {
		log.info("select commodityComment page start...");
		if (commodityCode == null || "".equals(commodityCode.trim())) {
			return null;
		}
		// 创建分页对象
		PageInfo<CommodityComment> pageInfo = new PageInfo<CommodityComment>(pageNum < 1 ? 1 : pageNum, pageSize);
		// 创建查询对象
		CommodityComment commodityComment = new CommodityComment();
		commodityComment.setCommodityCode(commodityCode.trim());
		commodityComment.setCommentTypeCode(3);
		commodityComment.setCommentStatus(1);
		// 根据评价类型设置查询对象值
		if (commentType == CommentGrade.GOOD) {
			commodityComment.setCommentGrade(CommentGrade.GOOD.getValue());
		} else if (commentType == CommentGrade.MEDIUM) {
			commodityComment.setCommentGrade(CommentGrade.MEDIUM.getValue());
		} else if (commentType == CommentGrade.BAD) {
			commodityComment.setCommentGrade(CommentGrade.BAD.getValue());
		}
		// 分页查询
		PageInfo<CommodityComment> page = selectListByPage(commodityComment, "selectCommentByPage", pageInfo);
		// 通过查询得到的id集合查询所有评价集合
		List<CommodityComment> resultTemp = page.getResult();
		// 放所有查询得到的评价对象
		List<CommodityComment> commodityComments = new ArrayList<CommodityComment>();
		if (resultTemp.size() > 0) {
			// 放查询得到的所有id
			List<Long> ids = new ArrayList<Long>();
			for (int i = 0; i < resultTemp.size(); i++) {
				ids.add(resultTemp.get(i).getId());
			}
			// 根据id集合查询评价集合
			commodityComments = dao.selectCommodityCommentByIds(ids);
		}
		page.setResult(commodityComments);
		log.info("select commodityComment page end...");
		return page;
	}


	/**
	 * 通过 商品编码获得好评,中评,差评各自总条数
	 * @param commodityCode 商品编码
	 * @return 对应的map键值对集合  好评:条数
	 */
	@Override
	public Map<String, Long> selectCommodityCommentTypeCount(
			String commodityCode) {
		log.info("get commodityComment type count");
		if (commodityCode == null || "".equals(commodityCode.trim())) {
			return null;
		}
		// 从数据库拿到数据
		List<Map<String, Object>> list = dao.selectCommodityCommentTypeCount(commodityCode.trim());
		// 创建返回的map
		Map<String, Long> typeCount = new HashMap<String, Long>();
		// 初始化好评,中评,差评值
		Long good = 0L;
		Long medium = 0L;
		Long bad = 0L;
		// 遍历集合拿出键值对并赋值
		for (Map<String, Object> map : list) {
			if (CommentGrade.GOOD.getComment().equals(map.get("comments"))) {
				good = (Long)map.get("counts");
			} else if (CommentGrade.MEDIUM.getComment().equals(map.get("comments"))) {
				medium = (Long)map.get("counts");
			} else {
				bad = (Long)map.get("counts");
			}
		}
		// 键值对放入map中
		typeCount.put(CommentGrade.GOOD.getComment(), good);
		typeCount.put(CommentGrade.MEDIUM.getComment(), medium);
		typeCount.put(CommentGrade.BAD.getComment(), bad);
		typeCount.put(CommentGrade.COUNT.getComment(), good + medium + bad);
		return typeCount;
	}


	/**
	 * B2C提供通过订单编码查询商品评价
	 * @param orderNo 订单编号
	 * @return 商品评价集合
	 */
	@Override
	public List<CommodityComment> selectCommodityCommentByOrderNo(String orderNo) {
		log.info("get commodityComment by order no");
		if (orderNo == null || "".equals(orderNo.trim())) {
			return null;
		}
		// 通过订单编号从数据库查询商品评价
		return dao.selectCommodityCommentByOrderNo(orderNo.trim());
	}


	/**
	 * B2C通过商品编码集合获得对应的好评数,评价总条数
	 * @param commodityCodes 商品编码可变数组
	 * @return 对应的商品评价集合
	 */
	@Override
	public List<CommodityComment> selectGoodCommentPercent(
			String... commodityCodes) {
		if (commodityCodes == null || commodityCodes.length == 0) {
			return null;
		}
		// 从数据库拿到数据
		return dao.selectGoodCommentPercent(commodityCodes);
	}

}

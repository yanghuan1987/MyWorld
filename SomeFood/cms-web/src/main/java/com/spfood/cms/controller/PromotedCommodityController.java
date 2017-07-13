package com.spfood.cms.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.cms.intf.CommodityPromotionPosService;
import com.spfood.cms.intf.PromotedCommodityService;
import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.utils.CommodityStatus;
import com.spfood.cms.utils.JsonResult;
import com.spfood.cms.utils.MessagesUtil;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.domain.Commodity;

@Controller
@RequestMapping(value = "promotedCommodity")
public class PromotedCommodityController {

    @Resource
    private PromotedCommodityService promotedCommodityService;
    @Resource
    private CommodityPromotionPosService commodityPromotionPosService;
    
    @Resource
    private CommodityService commodityService;
    @Autowired
    private Validator validator;
    private static final Log log = LogFactory.getLog(PromotedCommodityController.class);
    //分页查询的每页条数
    private static final int PAGESIZE = 5;
    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping(value = "promotedMain")
    @RequiresPermissions("cms:promotedCommodity:showPage")
    public String promotedMian() {
        log.info("Access to recommend commodities homepage");
        return "promoted/promotedMain";
    }
    
    /**
     * 验证该商品对象在同一推荐页面是否已存在
     * @param promotedCommodity     商品对象
     * @return
     */
    public boolean vilidateExist(PromotedCommodity promotedCommodity) {
        log.info("Check the goods isExist");
        //获取同一推荐页面的所有推荐商品
        List<PromotedCommodity> promotedCommodities = promotedCommodityService.getPromotedCommoditys(promotedCommodity.getCommPromotePos());
        //通过商品编码对比判断商品是否存在
        for (PromotedCommodity promotedCommodity2 : promotedCommodities) {
            if (promotedCommodity.getCommodityCode().equals(promotedCommodity2.getCommodityCode())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 查询所有推荐位
     * 
     * @return
     */
    @RequiresPermissions("cms:promotedCommodity:showPage")
    @RequestMapping(method=RequestMethod.POST,value="AllPos",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult selectAll() {
        List<CommodityPromotionPos> commodityPromotionPoses = commodityPromotionPosService
                .selectAll();
        log.info("Get all the recommended position");
        //查询结果为空
        if(commodityPromotionPoses==null){
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.query.error"), null);
        }
        return new JsonResult(MessageTransferObject.SUCCESS, null, commodityPromotionPoses);
    }

   /**
     * 查询指定推荐位的推荐商品
     * @param commPromotePos  推荐位编号，非空
     * @return
     */
    @RequestMapping(method=RequestMethod.POST,value="promotion",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult PromotionByPos(@RequestParam Long commPromotePos) {
        List<PromotedCommodity> promotedCommodities = promotedCommodityService
                .getPromotedCommoditys(commPromotePos);
        log.info("Access to recommend commodities");
        //查询结果为空
        if(promotedCommodities==null){
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.query.error"), null);  
        }
        return new JsonResult(MessageTransferObject.SUCCESS, null, promotedCommodities);
    }
    /**
     * 查询商品
     * @param pageNum   页码，不能为空
     * @param commodityCode 商品编码，可以为空
     * @param commodityName 商品名称，可以为空
     * @return
     */
    @RequestMapping(method=RequestMethod.POST,value="allCommodity",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult selectCommodityList(@RequestParam Integer pageNum,@RequestBody Commodity commodity) {
        PageInfo<Commodity> pageInfo=new PageInfo<Commodity>(pageNum,PAGESIZE);//实例商品分页
        commodity.setCommodityStatus(CommodityStatus.UP.getValue());//查询上架的商品
        //pageCInfo,查询结果
        PageInfo<Commodity> pageCInfo=commodityService.selectCommodityByCategory(null,commodity, pageInfo);
        log.info("Query the goods");
        //查询结果为空
        if(pageCInfo.getResult()==null){
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.query.error"), pageCInfo);  
        }
        return new JsonResult(MessageTransferObject.SUCCESS, null, pageCInfo);
    }
    /**
     * 修改展示顺序
     * @param commdityId    推荐商品ID，不能为空
     * @param newOrder      新的排序位置，不能为空
     * @return
     */
    @RequiresPermissions("cms:promotedCommodity:showPage")
    @RequestMapping(method=RequestMethod.POST,value="changOrder",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult changOrder(@RequestParam Long commdityId,@RequestParam Integer newOrder) {
        //isSuccess，修改结果
        boolean isSuccess=promotedCommodityService.changeOrder(commdityId, newOrder);
        log.info("Modify the display order");
        //修改成功
        if(isSuccess){
            return new JsonResult(MessageTransferObject.SUCCESS, MessagesUtil.getMessageByKey("promotedCommodity.update.success"), null);
        }
        return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.update.error"), null);
        
    }
    //验证promotedCommodity是否符合规则
    public boolean vilidate(PromotedCommodity promotedCommodity) {
        //收集失败信息
        Set<ConstraintViolation<PromotedCommodity>> validate = validator.validate(promotedCommodity);
        //失败信息条数大于0，则数据验证失败，返回false
        if (validate.size() > 0){
            //数据验证不通过
            return false;
        }
        return true;
    }
    /**
     * 新增
     * @param promotedCommodity  推荐商品，ID必须为空，其余参数不能为空
     * @return
     */
    @RequiresPermissions("cms:promotedCommodity:showPage")
    @RequestMapping(method=RequestMethod.POST,value="add",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult add(@RequestBody PromotedCommodity promotedCommodity) {
        //验证promotedCommodity是否符合规则
        boolean isRight=vilidate(promotedCommodity);
        //验证失败
        if (!isRight) {
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.check.error"), null);
        }
        //验证商品是否存在
        boolean isExist=vilidateExist(promotedCommodity);
        //已经存在该商品
        if (isExist) {
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.check.exist"), null);
        }
        //执行新增，得到带有ID的对象
        promotedCommodity=promotedCommodityService.add(promotedCommodity);
        
        log.info("Add the recommended goods");
        //为空表示新增失败
        if(promotedCommodity.getId()==null){
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.add.error"), null);
        }
        return new JsonResult(MessageTransferObject.SUCCESS, MessagesUtil.getMessageByKey("promotedCommodity.add.success"), promotedCommodity);
    }
    
    /**
     * 修改
     * @param promotedCommodity 推荐商品，不能为空
     * @return
     */
    @RequiresPermissions("cms:promotedCommodity:showPage")
    @RequestMapping(method=RequestMethod.POST,value="update",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult update(@RequestBody PromotedCommodity promotedCommodity) {
        //验证promotedCommodity是否符合规则
        boolean isRight=vilidate(promotedCommodity);
        //验证失败
        if (!isRight) {
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.check.error"), null);
        }
        //isSuccess，修改结果，true：成功，false:失败
        boolean isSuccess=promotedCommodityService.update(promotedCommodity);
        log.info("The editors recommend products");
        if(isSuccess){
            return new JsonResult(MessageTransferObject.SUCCESS, MessagesUtil.getMessageByKey("promotedCommodity.update.success"), "isSuccess");
        }
        return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("promotedCommodity.update.error"), "fail");
    }
    
    /**
     * 推荐位列表（含推荐商品）
     * @param name  页面名称，不能为空
     * @return
     */
    @RequestMapping(method=RequestMethod.POST,value="getCommPromotePosList",produces={"application/xml", "application/json"})
    @ResponseBody
    public JsonResult getCommPromotePosList(String name) {
        log.info("Query recommend position and recommend commodities");
        //comList，推荐位列表
        List<PromotedCommodity> comList=commodityPromotionPosService.getCommPromotePosList(name);
        //查询结果为空
        if(comList==null){
            return new JsonResult(MessageTransferObject.ERROR,MessagesUtil.getMessageByKey("promotedCommodity.query.error"), null);
        }
        return new JsonResult(MessageTransferObject.SUCCESS, null, comList);
    }
}

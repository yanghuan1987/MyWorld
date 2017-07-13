package com.spfood.cms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

import com.spfood.cms.intf.CatPromotePosService;
import com.spfood.cms.intf.domain.CatPromotePos;
import com.spfood.cms.intf.domain.CatPromotedCommodity;
import com.spfood.cms.utils.CommodityStatus;
import com.spfood.cms.utils.JsonResult;
import com.spfood.cms.utils.MessagesUtil;
import com.spfood.cms.utils.ReadInputStream;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.imageupload.intf.ImageUploadService;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.domain.Commodity;

/**
 * 菜单表示控制器
 * 
 * @author yanghuan
 *
 */
@Controller
@RequestMapping(value = "catPromotePos")
public class CatPromotePosController {

	@Resource
	private CatPromotePosService catPromotePosService;

	@Resource
	private CommodityService commodityService;
	
	@Autowired
	private ImageUploadService imageUploadService;
	
	@Autowired
    private Validator validator;
	private static final Log log = LogFactory.getLog(CatPromotePosController.class);

	/**
	 * cms菜单管理主界面
	 * 
	 * @return
	 */
	@RequiresPermissions("cms:catPromotePos:showPage")
	@RequestMapping(value = "/showMain")
	public String showMain() {
		log.info("Enter the commodity group layout configuration");
		return "catPromotePos/catPromotePos";
	}

	/**
	 * cms菜单管理新增页面
	 * 
	 * @return
	 */
	@RequiresPermissions("cms:catPromotePos:showPage")
	@RequestMapping(value = "/showNewMain")
	public String showNewMain() {
		log.info("Enter the commodity group layout configuration");
		return "catPromotePos/catPromotePosNew";
	}

	/**
	 * cms菜单管理新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showEditMain")
	@RequiresPermissions("cms:catPromotePos:showPage")
	public String showEditMain(HttpServletRequest request) {
	    log.info("Enter the commodity group layout configuration");
        request.setAttribute("id", request.getParameter("catPromotePosid"));
        return "catPromotePos/catPromotePosEdit";
	}
	
	//判断string是否为数字
	public boolean isNumeric(String str){
	    if (str==null||str.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	/**
	 * 商品组版面获取
	 * @param pageState    查询状态，可以为空
	 * @param catPromotePosid  商品组ID，可以为空
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("cms:catPromotePos:showPage")
	@RequestMapping(method=RequestMethod.POST,value="/getCatPromotePosInfo",produces={"application/xml", "application/json"})
	public JsonResult getCatPromotePosInfo(@RequestParam String pageState,@RequestParam String catPromotePosid) {
	    //list 查询结果
        List<CatPromotePos> list = new ArrayList<CatPromotePos>();
        //pageState为空查询全部
	    if (pageState.isEmpty()) {
	        list = catPromotePosService.getCatPromotePosInfo(null);
        }else {
            //判断catPromotePosid是否为数字
            boolean isNumeric=isNumeric(catPromotePosid);
            if (isNumeric) {
                //实例商品组对象
                CatPromotePos caPos = new CatPromotePos();
                //pageState为空则查询全部，否则将catPromotePosid设置到caPos对象作为查询条件
                caPos.setId(Long.valueOf(catPromotePosid));
                list = catPromotePosService.getCatPromotePosInfo(caPos);
            }
        }
	    //打印日志
		log.info("Get product group information");
		//提示信息
		String msgcode;
		//请求状态
		String status;
		//查询结果不为空
		if (list.size() != 0) {
		    msgcode = null;
			status = MessageTransferObject.SUCCESS;
		}
		//查询结果为空
		else {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.query.error");//没有获取信息
			status = MessageTransferObject.ERROR;
		}
		return new JsonResult(status, msgcode,list);
	}
	
	//验证catPromotePos对象是否符合要求
	public boolean validate(CatPromotePos catPromotePos) {
	    //验证商品组数据，validate收集验证失败的信息
        Set<ConstraintViolation<CatPromotePos>> validate = validator.validate(catPromotePos);
        //如果验证失败的信息条数大于0则表示验证未通过
        if (validate.size() > 0){
            return false;
        }
        //验证商品数据，catPromotedCommodity:商品对象
        for (CatPromotedCommodity catPromotedCommodity : catPromotePos.getCatPromotedCommodityList()) {
            //validate1收集验证失败的信息
            Set<ConstraintViolation<CatPromotedCommodity>> validate1 = validator.validate(catPromotedCommodity);
            //如果验证失败的信息条数大于0则表示验证未通过
            if (validate1.size() > 0){
                //数据验证不通过
                return false;
            }
        }
        return true;
    }

	/**
	 * 商品组版面更新
	 * @param catPromotePos    商品组对象，不能为空
	 *
	 **/
	@ResponseBody
	@RequiresPermissions("cms:catPromotePos:showPage")
	@RequestMapping(method=RequestMethod.POST,value="/updateCatPromotePos",produces={"application/xml", "application/json"})
	public JsonResult updateCatPromotePos(@RequestBody CatPromotePos catPromotePos) {
	    //验证参数是否符合要求
	    boolean isRight=validate(catPromotePos);
	    //验证失败返回提示信息
	    if(!isRight){
	        return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("catPromotePos.check.error"), null); 
	    }
        //打印日志
		log.info("Commodity group page updates");
		//isSuccess修改结果
		boolean isSuccess = catPromotePosService.updateOne(catPromotePos);
		//提示信息
		String msgcode;
		//请求状态
		String status;
		//isSuccess为true，修改成功
		if (isSuccess) {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.update.success");//获取到信息
			status = MessageTransferObject.SUCCESS;
		}
		//isSuccess为false，修改失败
		else {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.update.error");//没有获取信息
			status = MessageTransferObject.ERROR;
		}
		return new JsonResult(status, msgcode,isSuccess);
	}

	/**pms-商品信息获取
	 * @param commodityCode    商品编码，可以为空
	 * @param commodityName    商品名称，可以为空
	 *            
	 **/
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/getCommodityInfo",produces={"application/xml", "application/json"})
	public JsonResult getCommodityInfo(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Commodity commodity) {
		log.info("Get Commodities data");
		//String categorycodeString = "";
		//查询上架的商品
		commodity.setCommodityStatus(CommodityStatus.UP.getValue());
		PageInfo<Commodity> pageInfoResult = commodityService.selectCommodityByCategory(null,commodity, new PageInfo<Commodity>(pageNum, pageSize));
		//list 查询到的商品集合
		List<Commodity> list = pageInfoResult.getResult();
		//提示信息
		String msgcode;
		//请求状态
		String status;
		//查询结果为空
		if (null == list || list.size() == 0) {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.query.error");//没有获取信息
			status = MessageTransferObject.ERROR;
		}
		//查询结果不为空
		else {
			msgcode = null;//获取到信息
			status = MessageTransferObject.SUCCESS;
		}
		return new JsonResult(status, msgcode,pageInfoResult);
	}

	/**
	 * 新增商品组版面信息
	 * @param catPromotPos     商品对象，不能为空
	 *            
	 **/
	@RequiresPermissions("cms:catPromotePos:showPage")
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/addCatPromotePosList",produces={"application/xml", "application/json"})
	public JsonResult addCatPromotePosList(@RequestBody CatPromotePos catPromotePos) {
	    //验证参数是否符合要求
        boolean isRight=validate(catPromotePos);
        //验证失败返回提示信息
        if(!isRight){
            return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("catPromotePos.check.error"), null); 
        }
		log.info("New commodity group layout information");
		//执行新增
		catPromotePosService.addOne(catPromotePos);
		return new JsonResult(MessageTransferObject.SUCCESS, MessagesUtil.getMessageByKey("catPromotePos.add.success"),null);
	}

	/**删除商品组版面
	 * @param catPromotPos     商品组对象，ID不能为空
	 *            
	 **/
	@RequiresPermissions("cms:catPromotePos:showPage")
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/deleteCatpromotePos",produces={"application/xml", "application/json"})
	public JsonResult deleteCatpromotePos(@RequestBody CatPromotePos catPromotePos) {
		log.info("Delete the commodity group pages");
		//isSuccess删除结果，布尔值
		boolean isSuccess = catPromotePosService.deleteOne(catPromotePos);
		//请求状态
		String status;
		//提示信息
		String msgcode;
		//删除成功
		if (isSuccess) {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.delete.success");//获取到信息
			status = MessageTransferObject.SUCCESS;
		}
		//删除失败
		else {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.delete.error");//没有获取信息
			status = MessageTransferObject.ERROR;
		}
		return new JsonResult(status, msgcode,isSuccess);
	}
	
	/**商品组排序重复检查
	 * @param catPromotPos 商品组对象，不能为空
	 *            
	 **/
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/selectBydisplayOrderIsHave",produces={"application/xml", "application/json"})
	public JsonResult selectBydisplayOrderIsHave(@RequestBody CatPromotePos catPromotePos) {
		log.info("Repeat check goods group order");
		//isHave,为true表示排序不重复
		boolean isHave = catPromotePosService.selectBydisplayOrderIsHave(catPromotePos);
		//msgcode提示信息
		String msgcode = MessagesUtil.getMessageByKey("catPromotePos.check.success");
		return new JsonResult(MessageTransferObject.SUCCESS, msgcode,isHave);
	}
	
	/**图片上传服务
	 * @param catPromotPos 商品组对象，不能为空
	 *            
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/imageUpload")
	public @ResponseBody JsonResult uploadImage(HttpServletRequest request)
			throws IOException, ServletException, Exception {
		log.info("Image upload");
		//请求状态
		String status;
		//提示信息
		String msgcode;
		// 初始化上传信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> files = upload.parseRequest(request);
		// 初始化图片地址
		String imageAddress = null;
		for(int i = 0; i<files.size();i++){
			//模拟图片服务器路径
			String fileType1 = files.get(i).getContentType();
			InputStream is = files.get(i).getInputStream();
			
			int index = fileType1.lastIndexOf("/");
			String fileType = fileType1.substring(index+1);

			byte[] data = ReadInputStream.readInputStream(is);
			//文件上传
			imageAddress = imageUploadService.uploadImage(data, fileType);
		}
		//上传失败
		if (null == imageAddress) {
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.upload.error");//获取到信息
			status = MessageTransferObject.ERROR;
		}
		//上传成功
		else {
			status = MessageTransferObject.SUCCESS;
			msgcode = MessagesUtil.getMessageByKey("catPromotePos.update.success");
		}
		return new JsonResult(status, msgcode,imageAddress);
	}
	
}

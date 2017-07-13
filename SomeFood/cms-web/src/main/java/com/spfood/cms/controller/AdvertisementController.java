package com.spfood.cms.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.cms.intf.AdvertisementService;
import com.spfood.cms.intf.SlideAdsPositionService;
import com.spfood.cms.intf.domain.Advertisement;
import com.spfood.cms.utils.JsonResult;
import com.spfood.cms.utils.MessagesUtil;
import com.spfood.cms.utils.ReadInputStream;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.imageupload.intf.ImageUploadService;

@Controller
@RequestMapping(value="advertisement")
public class AdvertisementController {

	private static final Log log = LogFactory.getLog(AdvertisementController.class);
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private SlideAdsPositionService slideAdsPositionService;
	
	@Autowired
	private ImageUploadService imageUploadService;
	
	@Autowired
	private Validator validator;
	
	
	/**
	 * cms跳转到banner页面
	 * @return jsp页面指定路径
	 */
	@RequiresPermissions("cms:advertisement:showPage")
	@RequestMapping(value="/showPage")
	public String Main(){
		log.info("To access the CMS page banner");
		return "advertisement/advertisement";
	}
	
	/**
	 * cms展示轮播图数据
	 * @return JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:advertisement:showPage")
	@RequestMapping(value="/list")
	public JsonResult list(){
		log.info("Show the CMS page banner");
		return new JsonResult(MessageTransferObject.SUCCESS, null, slideAdsPositionService.selectAll());
	}
	

	/**
	 * cms保存轮播图
	 * @param advertisement 轮播图对象,不能为空
	 * @return JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:advertisement:showPage")
	@RequestMapping(method=RequestMethod.POST, value="/saveOrUpdate", produces={"application/xml", "application/json"})
	public JsonResult saveOrUpdate(@RequestBody Advertisement advertisement){
		log.info("Modify the CMS banner");
		// 通过注入的验证对象对advertisement中的字段进行验证,不满足的字段信息放入set集合中
		Set<ConstraintViolation<Advertisement>> validate = validator.validate(advertisement);
		if (validate.size() > 0){
			//数据验证不通过
			return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("advertisement.validate.error"), null);
		}
		// 根据Id判断是新增还是修改
		if (advertisement.getId() == null) {
			Advertisement insertAdvertisement = advertisementService.insert(advertisement);
			return insertAdvertisement != null ? new JsonResult(MessageTransferObject.SUCCESS, null, insertAdvertisement)
				: new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("advertisement.update.error"), null);
		} else {
			boolean update = advertisementService.update(advertisement);
			return update == true ? new JsonResult(MessageTransferObject.SUCCESS, null, null) 
				: new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("advertisement.update.error"), null);
		}
	}
	
	/**
	 * cms删除轮播图
	 * @param id 轮播图id
	 * @return JsonResult
	 */
	@ResponseBody
	@RequiresPermissions("cms:advertisement:showPage")
	@RequestMapping(method=RequestMethod.POST, value="/delete")
	public JsonResult delete(Long id){
		log.info("Delete the CMS banner");
		// 判断Id是否为空
		if (id != null) {
			boolean delete = advertisementService.delete(id);
			return delete == true ? new JsonResult(MessageTransferObject.SUCCESS, null, null) 
				: new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("advertisement.delete.error"), null);
		}
		return new JsonResult(MessageTransferObject.ERROR, MessagesUtil.getMessageByKey("advertisement.delete.error"), null);
	}
	
	/**
	 * 图片上传服务
	 * @param request 请求对象,不为空
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/imageUpload")
	public @ResponseBody JsonResult uploadImage(HttpServletRequest request)
			throws IOException, ServletException, Exception {
		log.info("image upload");
		// 初始化上传成功或失败状态
		String status = null;
		// 初始化上传信息
		String msgcode = null;
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
		// 上传失败或成功赋值不同
		if (null == imageAddress) {
			msgcode = MessagesUtil.getMessageByKey("advertisement.upload.error"); //获取到信息
			status = MessageTransferObject.ERROR;
		}else {
			status = MessageTransferObject.SUCCESS;
			msgcode = MessagesUtil.getMessageByKey("advertisement.upload.success");
		}
		return new JsonResult(status, msgcode,imageAddress);
	}
	
}

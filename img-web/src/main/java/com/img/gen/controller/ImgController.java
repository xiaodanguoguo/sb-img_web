package com.img.gen.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.img.gen.conmon.*;
import com.img.gen.conmon.parser.GetImgUtil;
import com.img.gen.dao.model.ImgMenu;
import com.img.gen.pungin.PageView;
import com.img.gen.service.ImgMenuService;
import com.img.gen.service.QiniuUploadService;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.img.gen.conmon.cache.CacheService;
import com.img.gen.conmon.thread.AssertContext;
import com.img.gen.controller.dto.ImgResourceDTO;
import com.img.gen.controller.dto.UserCollectionDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.dao.model.UserCollection;
import com.img.gen.service.ImgResourceService;
import com.img.gen.service.UserCollectionService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("img/")
public class ImgController {
	@Autowired
	private ImgResourceService imgResourceService; 
	@Autowired
	private UserCollectionService userCollectionService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private GetImgUtil imgUtil;
	@Autowired
	private QiniuUploadService qiniuUploadService;//七牛云上传下载
	@Autowired
	private ImgMenuService imgMenuService;

	/**
	 * 跳转页面
	 * @return
     */
	@RequestMapping("upload")
	public ModelAndView upload(){
		ModelAndView modelAndView = new ModelAndView("upload");
		List<ImgMenu> menuList = new ArrayList<ImgMenu>();
		menuList = imgMenuService.findAll();
		modelAndView.addObject("menuList",menuList);
		return modelAndView;

	}


	/**
	 * 上传图片
	 * @param files
	 * @param imgmenu
	 * @param request
     * @return
     */
	@RequestMapping("doupload")
	@ResponseBody
	public JSONObject doupload(@RequestParam("file") MultipartFile[] files,String name,String imgmenu,HttpServletRequest request){
		JSONObject retObj = new JSONObject();
		String imgFolderPath = request.getRealPath("/") + File.separator + "temp" + File.separator + "img";//图片文件夹名称
		String uuid = UUID.randomUUID().toString();
		String targetImgName = uuid+".jpg";
		try {
			byte[] bytes = files[0].getBytes();//
			File uploadFile = new File(imgFolderPath +File.separator+ targetImgName);
			if(!uploadFile.exists()){
				uploadFile.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
			fileOutputStream.write(bytes);//写到零时文件夹
			fileOutputStream.flush();
			fileOutputStream.close();//释放资源
			//上传到七牛云
			qiniuUploadService.upload(uploadFile,targetImgName);
			ImgResource record = new ImgResource();
			record = getResourceValue(targetImgName,name,imgmenu,files[0].getSize());
			imgResourceService.createImgResource(record);
			//删除文件
			FileUtils.deleteFile(uploadFile);

			retObj.put("success",true);
			retObj.put("retMsg","上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			retObj.put("success",false);
			retObj.put("retMsg","上传失败");
		}
		return retObj;
	}

	/**
	 * 设置对象的直
	 * @param targetImgName 图片的url地址
	 * @param name 图片名称
	 * @param imgmenu 类目编号
	 * @param size 图片大小
     * @return
     */
	private ImgResource getResourceValue(String targetImgName, String name, String imgmenu, long size) {
		ImgResource retRecord = new ImgResource();
		retRecord.setGenerate(0);
		retRecord.setImgName(name);
		retRecord.setImgSize((int)size);
		retRecord.setImgType(imgmenu);
		retRecord.setLastGenTime(new Date());
		retRecord.setLikeCnt(0);
		retRecord.setPageView(0);
		retRecord.setImgUrl(targetImgName);
		retRecord.setCreateTime(new Date());
		return  retRecord;
	}


	/**
	 * 分页查询图片
	 * @param pageNo
	 * @param pageSize
	 * @param imgName
	 * @return
	 */
	@RequestMapping("get/page/{pageNo}/{pageSize}/{imgName}")
	@ResponseBody
	public JsonResult<Page<ImgResourceDTO>> getImgByPage(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, @PathVariable("imgName") String imgName) {
		JsonResult<Page<ImgResourceDTO>> result = new JsonResult<>(JsonResult.SUCCESS);
		Page<ImgResourceDTO> imgPage = new Page<>(pageNo, pageSize);
		try {
			List<ImgResource> imgResources = imgResourceService.getImgByPage(imgPage.getStartRow(), pageSize, imgName);
			BeanUtils.copyPropertieses(imgResources, new ArrayList<ImgResourceDTO>(), ImgResourceDTO.class);
			Long total = imgResourceService.getImgCount(imgName);
			imgPage.setTotal(total);
			result.setResults(imgPage);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}

	/**
	 * 默认第一次加载图片资源
	 * @return
     */
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("index");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		PageView pageMode = imgResourceService.queryByPage(1,30,paramMap);//默认30条记录
		modelAndView.addObject("pageModel",pageMode);
		return  modelAndView;
	}


	/**
	 * 分页查询
	 * @param pageNo 当前页
	 * @param pageSize 页数
	 * @param type 类型
	 * @param keys 关键词
	 * @return
	 */
	@RequestMapping("queryByPage")
	@ResponseBody
	public ModelAndView queryByPage(String pageNo,String pageSize,String type,String keys){
		ModelAndView modelAndView = new ModelAndView("index");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		PageView pageMode = imgResourceService.queryByPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize),paramMap);
		modelAndView.addObject("pageModel",pageMode);
		return modelAndView;
	}


	/**
	 * 详细信息
	 * @param id 主键
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(String id){
		ModelAndView modelAndView = new ModelAndView("detail");
		ImgResource imgResource = imgResourceService.getImgResourceByPrimaryKey(id);
		modelAndView.addObject("imgResource",imgResource);
		return  modelAndView ;
	}

	/**
	 * @param id 主键
	 * @param text 文字
	 * @param x x位置
	 * @param y y位置
	 * @param img 原图片地址
	 * @param color 颜色
	 * @param fontSize 字体大小
	 * @param  width 图片宽度
	 * @param  height 图片高度
	 * @return
	 */
	@RequestMapping("generatorImg")
	@ResponseBody
	public JSONObject generatorImg(String id,String fontSize, String text, String color, String x, String y, String img, String width, String height, HttpServletRequest request) throws  Exception{
		JSONObject retObj = new JSONObject();

		String imgFolderPath = request.getRealPath("/") + File.separator + "temp" + File.separator + "img";//图片文件夹名称
		String uuid = UUID.randomUUID().toString();
		String srcImgName = uuid+"temp"+".jpg";
		String targetImgName = uuid+".jpg";
		//下载图片
		imgUtil.downloadImg(img,imgFolderPath , srcImgName);
		//生成图片
		ImageUtils.convertImg((imgFolderPath +File.separator+ srcImgName) , text,color,Integer.valueOf(x),Integer.valueOf(y),Integer.valueOf(width),Integer.valueOf(height),Integer.valueOf(fontSize),imgFolderPath +File.separator+ targetImgName);

		File uploadFile = new File((imgFolderPath +File.separator+ targetImgName));
		//上传到七牛云
		qiniuUploadService.upload(uploadFile,targetImgName);
		//TODO 新增图片到数据库
		ImgResource imgResource =  imgResourceService.getImgResourceByPrimaryKey(id);
		imgResource.setImgUrl(uuid);
		imgResource.setImgId(null);
		imgResource.setLastGenTime(new Date());
		imgResourceService.createImgResource(imgResource);
		retObj.put("imgUrl",targetImgName);//回显
		retObj.put("success",true);
		//删除文件
		File resourceFile = new File((imgFolderPath +File.separator+ srcImgName));
		FileUtils.deleteFile(resourceFile);
		FileUtils.deleteFile(uploadFile);


		return retObj;
	}



	/**
	 * 获取单个图片
	 * @param imgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{imgId}")
	public JsonResult<ImgResourceDTO> getImg(@PathVariable("imgId") Integer imgId) {
		JsonResult<ImgResourceDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			Integer pageView = cacheService.incrPageView(imgId);
			
			ImgResource imgResource = imgResourceService.getImgResourceByPrimaryKey(imgId);
			ImgResourceDTO imgResourceDTO = new ImgResourceDTO();
			BeanUtils.copyProperties(imgResource, imgResourceDTO);
			imgResourceDTO.setPageView(pageView);
			result.setResults(imgResourceDTO);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	/**
	 * 查询火爆图片列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/hot")
	public JsonResult<List<ImgResourceDTO>> getImgByHot() {
		JsonResult<List<ImgResourceDTO>> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			List<ImgResource> imgResources = imgResourceService.getImgResourceByHot();
			result.setResults(
					BeanUtils.copyPropertieses(
							imgResources, new ArrayList<ImgResourceDTO>(), ImgResourceDTO.class));
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	/**
	 * 点赞
	 * @return
	 */
	@ResponseBody
	@RequestMapping("like/{imgId}")
	public JsonResult<Integer> likeImg(@PathVariable("imgId") Integer imgId) {
		JsonResult<Integer> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			result.setResults(cacheService.incrLikeCnt(imgId));
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	/**
	 * 收藏
	 * @param imgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("coll/{imgId}")
	public JsonResult<Integer> collImg(@PathVariable("imgId") Long imgId) {
		JsonResult<Integer> result = new JsonResult<>(JsonResult.SUCCESS);
		try {

			UserCollection userCollection = new UserCollection();
			userCollection.setCollectId(IdHelper.generateLongUUID());
			userCollection.setCollectTime(new Date());
			userCollection.setUserId(AssertContext.getUserId());
			userCollection.setImgId(imgId);
			userCollectionService.createUserCollection(userCollection);
			
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	/**
	 * 获取收藏
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/coll")
	public JsonResult<List<UserCollectionDTO>> getCollImg() {
		JsonResult<List<UserCollectionDTO>> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserCollection userCollection = new UserCollection();
			userCollection.setUserId(AssertContext.getUserId());
			List<UserCollection> userCollections = userCollectionService.findUserCollections(userCollection);
			result.setResults(BeanUtils.copyPropertieses(userCollections, new ArrayList<UserCollectionDTO>(), UserCollectionDTO.class));
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
}

package com.img.gen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.IdHelper;
import com.img.gen.conmon.JsonResult;
import com.img.gen.conmon.Page;
import com.img.gen.conmon.thread.AssertContext;
import com.img.gen.controller.dto.ImgResourceDTO;
import com.img.gen.controller.dto.UserCollectionDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.dao.model.UserCollection;
import com.img.gen.service.ImgResourceService;
import com.img.gen.service.UserCollectionService;

@Controller
@RequestMapping("img/")
public class ImgController {
	@Autowired
	private ImgResourceService imgResourceService; 
	@Autowired
	private UserCollectionService userCollectionService;
	
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
	
	@ResponseBody
	@RequestMapping("get/{imgId}")
	public JsonResult<ImgResourceDTO> getImg(@PathVariable("imgId") String imgId) {
		JsonResult<ImgResourceDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			ImgResource imgResource = imgResourceService.getImgResourceByPrimaryKey(imgId);
			ImgResourceDTO imgResourceDTO = new ImgResourceDTO();
			BeanUtils.copyProperties(imgResource, imgResourceDTO);
			result.setResults(imgResourceDTO);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
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
	
	@ResponseBody
	@RequestMapping("like")
	public JsonResult<Integer> likeImg() {
		JsonResult<Integer> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			int i = 0; //TODO 从缓存取
			if (i < 10) {
				//TODO 如果点赞数大于一定数量，再同步数据库，减少服务器压力
				//其余时刻点赞数量去本地缓存操作，缓存待添加
			} else {
				ImgResource imgResource = new ImgResource();
				imgResource.setLikeCnt(i);
				result.setResults(10000);
			}
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("coll/{imgId}")
	public JsonResult<Integer> collImg(@PathVariable("imgId") String imgId) {
		JsonResult<Integer> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			int i = 0; //TODO 从缓存取
			if (i < 10) {
				//TODO 如果点赞数大于一定数量，再同步数据库，减少服务器压力
				//其余时刻点赞数量去本地缓存操作，缓存待添加   
			} else {
				ImgResource imgResource = new ImgResource();
				imgResource.setLikeCnt(i);
				
				List<ImgResource> imgResources = imgResourceService.getImgResourceByHot();
				result.setResults(10000);
			}
			
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

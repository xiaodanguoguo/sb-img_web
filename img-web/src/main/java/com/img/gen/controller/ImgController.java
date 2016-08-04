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
import com.img.gen.conmon.cache.CacheService;
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
	@Autowired
	private CacheService cacheService;
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
	public JsonResult<Integer> collImg(@PathVariable("imgId") String imgId) {
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

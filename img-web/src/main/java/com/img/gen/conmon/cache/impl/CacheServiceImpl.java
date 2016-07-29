package com.img.gen.conmon.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.cache.CacheService;
import com.img.gen.conmon.cache.model.ImgCacheDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.service.ImgResourceService;

public class CacheServiceImpl implements CacheService{

	private Map<String, ImgCacheDTO> imgCache;
	
	@Autowired
	private ImgResourceService imgResourceService;
	
	private static final Integer PAGE_VIEW = 50;
	
	private static final Integer SHARE_CNT = 10;
	private static final Integer LIKE_CNT = 10;
	private static final Integer GENERATE = 30;
	
	
	private void init() {
		this.imgCache = new HashMap<>();
		try {
			List<ImgResource> imgResources = imgResourceService.findAll();
			List<ImgCacheDTO> imgCacheDTOs;
			imgCacheDTOs = BeanUtils.copyPropertieses(imgResources, new ArrayList<ImgCacheDTO>(imgResources.size()), ImgCacheDTO.class);
			//imgCacheDTOs.forEach((final ImgCacheDTO imgCacheDTO) -> imgCache.put(imgCacheDTO.getImgId(), imgCacheDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过ID获取图片
	 * @param imgId  图片ID
	 * @return
	 */
	@Override
	public ImgCacheDTO getImgById(String imgId) {
		if (BeanUtils.isNotNull(imgCache)) init();
		ImgCacheDTO imgCacheDTO = imgCache.get(imgId);
		if (BeanUtils.isNotNull(imgCacheDTO))
			return imgCacheDTO;
		else {
			ImgResource imgResource = imgResourceService.getImgResourceByPrimaryKey(imgId);
			if (BeanUtils.isNotNull(imgResource)) {
				imgCacheDTO = new ImgCacheDTO();
				BeanUtils.copyProperties(imgResource, imgCacheDTO);
				imgCache.put(imgId, imgCacheDTO);
				return imgCacheDTO;
			}
		}
		return null;
	}

	@Override
	public Integer incrLikeCnt(String imgId) {
		ImgCacheDTO imgObj = getImgById(imgId);
		if (!BeanUtils.isNotNull(imgObj)) 
			return null;
		
		Integer likeCnt = imgObj.getLikeCnt();
		
		//每点赞20次入一次库
		if (++likeCnt % LIKE_CNT != 0) 
			imgObj.setLikeCnt(likeCnt);
		else {
			ImgResource imgResource = new ImgResource();
			imgResource.setLikeCnt(likeCnt);
			updateImg(imgId, imgResource);
		}
		return likeCnt;
	}

	@Override
	public Integer incrPageView(String imgId) {
		ImgCacheDTO imgObj = getImgById(imgId);
		if (!BeanUtils.isNotNull(imgObj)) 
			return null;
		
		Integer pageView = imgObj.getPageView();
		
		if (++pageView % PAGE_VIEW != 0) 
			imgObj.setPageView(pageView);
		else {
			ImgResource imgResource = new ImgResource();
			imgResource.setPageView(pageView);
			updateImg(imgId, imgResource);
		}
		return pageView;
	}

	@Override
	public Integer incrGenerate(String imgId) {
		ImgCacheDTO imgObj = getImgById(imgId);
		if (!BeanUtils.isNotNull(imgObj)) 
			return null;
		
		Integer generate = imgObj.getGenerate();
		
		if (++generate % GENERATE != 0) 
			imgObj.setGenerate(generate);
		else {
			ImgResource imgResource = new ImgResource();
			imgResource.setGenerate(generate);
			updateImg(imgId, imgResource);
		}
		return generate;
	}

	@Override
	public Integer incrShareCnt(String imgId) {
		ImgCacheDTO imgObj = getImgById(imgId);
		if (!BeanUtils.isNotNull(imgObj)) 
			return null;
		
		Integer shareCnt = imgObj.getShareCnt();
		
		if (++shareCnt % SHARE_CNT != 0) 
			imgObj.setShareCnt(shareCnt);
		else {
			ImgResource imgResource = new ImgResource();
			imgResource.setShareCnt(shareCnt);
			updateImg(imgId, imgResource);
		}
		return shareCnt;
	}
	
	private void updateImg(String imgId, ImgResource imgResource) {
		imgResource.setImgId(imgId);
		imgResourceService.updateImgResourceByPrimaryKey(imgResource);
	}
}

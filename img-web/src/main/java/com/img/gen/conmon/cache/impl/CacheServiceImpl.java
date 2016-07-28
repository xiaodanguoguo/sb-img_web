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
	
//	private static final Integer PAGE_VIEW = 10;
	private void init() {
		this.imgCache = new HashMap<>();
		try {
			List<ImgResource> imgResources = imgResourceService.findAll();
			List<ImgCacheDTO> imgCacheDTOs;
			imgCacheDTOs = BeanUtils.copyPropertieses(imgResources, new ArrayList<ImgCacheDTO>(imgResources.size()), ImgCacheDTO.class);
			imgCacheDTOs.forEach((final ImgCacheDTO imgCacheDTO) -> imgCache.put(imgCacheDTO.getImgId(), imgCacheDTO));
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
}

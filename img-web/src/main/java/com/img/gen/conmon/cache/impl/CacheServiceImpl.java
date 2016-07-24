package com.img.gen.conmon.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.img.gen.conmon.cache.CacheService;
import com.img.gen.conmon.cache.model.ImgCacheDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.service.ImgResourceService;

public class CacheServiceImpl implements CacheService{

	private Map<String, ImgCacheDTO> imgCache;
	
	@Autowired
	private ImgResourceService imgResourceService;
	
	@SuppressWarnings("unused")
	private void init() {
		this.imgCache = new HashMap<>();
//		List<ImgResource> imgResource = imgResourceService.findImgByPV();
	}
	
	@Override
	public Integer getLikeCnt(String imgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getShareCnt(String imgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPageView(String imgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getGenerateCnt(String imgId) {
		// TODO Auto-generated method stub
		return null;
	}

}

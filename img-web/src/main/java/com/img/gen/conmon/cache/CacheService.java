package com.img.gen.conmon.cache;

import com.img.gen.conmon.cache.model.ImgCacheDTO;

public interface CacheService {
	/**
	 * 通过ID获取图片
	 * @param imgId  图片ID
	 * @return
	 */
	public ImgCacheDTO getImgById(String imgId);
}

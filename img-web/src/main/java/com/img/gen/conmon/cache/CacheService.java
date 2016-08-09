package com.img.gen.conmon.cache;

import com.img.gen.conmon.cache.model.ImgCacheDTO;

public interface CacheService {
	/**
	 * 通过ID获取图片
	 * @param imgId  图片ID
	 * @return
	 */
	public ImgCacheDTO getImgById(Long imgId);
	
	/**
	 * 点赞数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrLikeCnt(Long imgId);
	
	/**
	 * 浏览数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrPageView(Long imgId);
	
	/**
	 * 生成数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrGenerate(Long imgId);
	
	/**
	 * 分享数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrShareCnt(Long imgId);
}

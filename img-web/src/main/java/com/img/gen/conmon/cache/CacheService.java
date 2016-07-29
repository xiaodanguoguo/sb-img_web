package com.img.gen.conmon.cache;

import com.img.gen.conmon.cache.model.ImgCacheDTO;

public interface CacheService {
	/**
	 * 通过ID获取图片
	 * @param imgId  图片ID
	 * @return
	 */
	public ImgCacheDTO getImgById(String imgId);
	
	/**
	 * 点赞数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrLikeCnt(String imgId); 
	
	/**
	 * 浏览数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrPageView(String imgId); 
	
	/**
	 * 生成数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrGenerate(String imgId); 
	
	/**
	 * 分享数+1
	 * @param imgId
	 * @return
	 */
	public Integer incrShareCnt(String imgId); 
}

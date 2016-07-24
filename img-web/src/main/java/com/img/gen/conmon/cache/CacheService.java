package com.img.gen.conmon.cache;

public interface CacheService {
	public Integer getLikeCnt(String imgId);
	
	public Integer getShareCnt(String imgId);
	
	public Integer getPageView(String imgId);
	
	public Integer getGenerateCnt(String imgId);
}

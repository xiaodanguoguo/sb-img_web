package com.img.gen.conmon.cache.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;


/**
 * DTO:ImgResource
 * 
 * @author kim
 * @date 2016-6-18
 */
public class ImgCacheDTO implements Serializable {
	
	private String	imgId;		
	private Integer	pageView;		
	private Integer	likeCnt;		
	private Integer	generate;		
	private Integer	shareCnt;		
	private Date lastGenTime;
	// Constructor
	public ImgCacheDTO() {
	}

	public Date getLastGenTime() {
		return lastGenTime;
	}

	public void setLastGenTime(Date lastGenTime) {
		this.lastGenTime = lastGenTime;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public Integer getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(Integer likeCnt) {
		this.likeCnt = likeCnt;
	}

	public Integer getGenerate() {
		return generate;
	}

	public void setGenerate(Integer generate) {
		this.generate = generate;
	}

	public Integer getShareCnt() {
		return shareCnt;
	}

	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

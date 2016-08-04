package com.img.gen.dao.model;

import java.io.Serializable;
import java.util.Date;


/**
 * entity:ImgResource
 * 
 * @author kim
 * @date 2016-6-18
 */
public class ImgResource implements Serializable {
	
	private Integer	imgId;
	private String	imgUrl;		
	private Integer	imgSize;		
	private Integer	userId;		
	private Boolean	imgType;		
	private Integer	pageView;		
	private Integer	likeCnt;		
	private String	imgName;		
	private Integer	generate;		
	private Date	lastGenTime;		
	private Integer	shareCnt;		
	private String smallimgUrl;/*小图url*/

	public String getSmallimgUrl() {
		return smallimgUrl;
	}

	public void setSmallimgUrl(String smallimgUrl) {
		this.smallimgUrl = smallimgUrl;
	}

	// Constructor
	public ImgResource() {
	}

	/**
	 * full Constructor
	 */
	public ImgResource(Integer imgId, String imgUrl, Integer imgSize, Integer userId, Boolean imgType, Integer pageView, Integer likeCnt, String imgName, Integer generate, Date lastGenTime, Integer shareCnt) {
		this.imgId = imgId;
		this.imgUrl = imgUrl;
		this.imgSize = imgSize;
		this.userId = userId;
		this.imgType = imgType;
		this.pageView = pageView;
		this.likeCnt = likeCnt;
		this.imgName = imgName;
		this.generate = generate;
		this.lastGenTime = lastGenTime;
		this.shareCnt = shareCnt;
	}


	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	public Integer getImgSize() {
		return imgSize;
	}

	public void setImgSize(Integer imgSize) {
		this.imgSize = imgSize;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Boolean getImgType() {
		return imgType;
	}

	public void setImgType(Boolean imgType) {
		this.imgType = imgType;
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

	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	
	public Integer getGenerate() {
		return generate;
	}

	public void setGenerate(Integer generate) {
		this.generate = generate;
	}

	public Date getLastGenTime() {
		return lastGenTime;
	}

	public void setLastGenTime(Date lastGenTime) {
		this.lastGenTime = lastGenTime;
	}

	
	public Integer getShareCnt() {
		return shareCnt;
	}

	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}

	@Override
	public String toString() {
		return "ImgResource [" + "imgId=" + imgId + ", imgUrl=" + imgUrl + ", imgSize=" + imgSize + ", userId=" + userId + ", imgType=" + imgType + ", pageView=" + pageView + ", likeCnt=" + likeCnt + ", imgName=" + imgName + ", generate=" + generate + ", lastGenTime=" + lastGenTime + ", shareCnt=" + shareCnt +  "]";
	}
}

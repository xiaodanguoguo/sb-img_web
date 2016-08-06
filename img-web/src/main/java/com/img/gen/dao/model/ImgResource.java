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
	private Integer	imgType = 1;
	private Integer	pageView = 0;
	private Integer	likeCnt = 0;
	private String	imgName;		
	private Integer	generate = 0;
	private Date	lastGenTime;		
	private Integer	shareCnt = 0;
	private String smallImgUrl;/*小图url*/
	private Integer samllImgSize;  /* 小图大小 */

	public ImgResource() {
	}

	public ImgResource(Integer imgId, String imgUrl, Integer imgSize, Integer userId, Integer imgType, Integer pageView, Integer likeCnt, String imgName, Integer generate, Date lastGenTime, Integer shareCnt, String smallImgUrl, Integer samllImgSize) {
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
		this.smallImgUrl = smallImgUrl;
		this.samllImgSize = samllImgSize;
	}

	public Integer getSamllImgSize() {
		return samllImgSize;
	}

	public void setSamllImgSize(Integer samllImgSize) {
		this.samllImgSize = samllImgSize;
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

	public Integer getImgType() {
		return imgType;
	}

	public void setImgType(Integer imgType) {
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

	public String getSmallImgUrl() {
		return smallImgUrl;
	}

	public void setSmallImgUrl(String smallImgUrl) {
		this.smallImgUrl = smallImgUrl;
	}


}

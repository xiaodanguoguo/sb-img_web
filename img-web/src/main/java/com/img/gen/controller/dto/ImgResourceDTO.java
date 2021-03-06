package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:ImgResource
 * 
 * @author kim
 * @date 2016-6-18
 */
public class ImgResourceDTO implements Serializable {
	
	private Long	imgId;
	private String	imgUrl;
	private Integer	imgSize;
	private Long	userId;
	private String	imgType;
	private Integer	pageView = 0;
	private Integer	likeCnt = 0;
	private String	imgName;
	private Integer	generate = 0;
	private Date	lastGenTime;		
	private Integer	shareCnt = 0;
	private String smallImgUrl;/*小图url*/
	private Integer samilImgSize;  /* 小图大小 */

	// Constructor
	public ImgResourceDTO() {
	}

	/**
	 * full Constructor
	 */
	public ImgResourceDTO(Long imgId, String imgUrl, Integer imgSize, Long userId, String imgType, Integer pageView, Integer likeCnt, String imgName, Integer generate, Date lastGenTime, Integer shareCnt) {
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

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getSmallImgUrl() {
		return smallImgUrl;
	}

	public void setSmallImgUrl(String smallImgUrl) {
		this.smallImgUrl = smallImgUrl;
	}

	public Integer getSamilImgSize() {
		return samilImgSize;
	}

	public void setSamilImgSize(Integer samilImgSize) {
		this.samilImgSize = samilImgSize;
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
		return "ImgResourceDTO [" + "imgId=" + imgId + ", imgUrl=" + imgUrl + ", imgSize=" + imgSize + ", userId=" + userId + ", imgType=" + imgType + ", pageView=" + pageView + ", likeCnt=" + likeCnt + ", imgName=" + imgName + ", generate=" + generate + ", lastGenTime=" + lastGenTime + ", shareCnt=" + shareCnt +  "]";
	}
}

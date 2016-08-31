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
	
	private Integer	imgId;/*主键*/
	private String	imgUrl;	/*图片url*/
	private Integer	imgSize;/*图片大小*/
	private Long	userId;/*用户id*/
	private String	imgType;/*图片类目*/
	private Integer	pageView = 0;/*查看次数*/
	private Integer	likeCnt = 0;/*收藏次数*/
	private String	imgName;/*图片名称*/
	private Integer	generate = 0;/*生成次数*/
	private Date	lastGenTime;/*最后一次生成时间*/
	private Integer	shareCnt = 0;/*分享次数*/
	private String smallImgUrl;/*小图url*/
	private Integer smallImgSize;  /* 小图大小 */
	private Date createTime;/*生成时间*/


	public String getSmallImgUrl() {
		return smallImgUrl;
	}

	public void setSmallImgUrl(String smallImgUrl) {
		this.smallImgUrl = smallImgUrl;
	}

	public Integer getSmallImgSize() {
		return smallImgSize;
	}

	public void setSmallImgSize(Integer smallImgSize) {
		this.smallImgSize = smallImgSize;
	}

	// Constructor
	public ImgResource() {
	}

	public ImgResource(Integer imgId, String imgUrl, Integer imgSize, Long userId, String imgType, Integer pageView, Integer likeCnt, String imgName, Integer generate, Date lastGenTime, Integer shareCnt, String smallImgUrl, Integer smallImgSize, Date createTime) {
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
		this.smallImgSize = smallImgSize;
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
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

	public static ImgResource getInstance() {
		return new ImgResource();
	}
}

package com.img.gen.service;

import java.util.List;
import java.util.Map;

import com.img.gen.dao.model.ImgResource;
import com.img.gen.pungin.PageView;

/**
 * dal Interface:ImgResource
 * @author kim
 * @date 2016-6-18
 */
public interface ImgResourceService {

    public List<ImgResource> findAll();

    public List<ImgResource> findImgResources(ImgResource record);

    public ImgResource getImgResourceByPrimaryKey(Object key);

    public Integer createImgResource(ImgResource record);

    public Integer deleteImgResource(ImgResource record);

    public Integer removeImgResource(ImgResource record);

    public Integer updateImgResourceByPrimaryKey(ImgResource record);

    /**
     * 分页查询图片(带条件)
     * @author kim
     * @param pageNo
     * @param pageSize
     * @param imgResource
     * @return
     */
	public List<ImgResource> getImgByPage(Integer pageNo, Integer pageSize, String imgName);

	/**
	 * 条件查询图片总记录条数
	 * @author kim
	 * @param imgResource
	 * @return
	 */
	public Long getImgCount(String imgName);

	/**
	 * 查询最火爆表情
	 * @author kim
	 * @return
	 */
	public List<ImgResource> getImgResourceByHot();

	/**
	 * 分页查询图片
	 * @param pageNo
	 * @param pageSize
	 * @param paramMap
     * @return
     */
	PageView queryByPage(String pageNo, String pageSize, Map<String, Object> paramMap);
}
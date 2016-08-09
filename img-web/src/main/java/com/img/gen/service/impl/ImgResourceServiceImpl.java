package com.img.gen.service.impl;

import java.util.List;
import java.util.Map;

import com.img.gen.pungin.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.dao.ImgResourceDao;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.service.ImgResourceService;

/**
 * dal Interface:ImgResource
 * @author kim
 * @date 2016-6-18
 */
@Service
public class ImgResourceServiceImpl implements ImgResourceService{

    @Autowired
    private ImgResourceDao imgResourceDao;

    public List<ImgResource> findAll() {
       return imgResourceDao.selectAll();
    }

    public List<ImgResource> findImgResources(ImgResource record){
       return imgResourceDao.select(record);
    }

    public ImgResource getImgResourceByPrimaryKey(Object key){
        return imgResourceDao.selectByPrimaryKey(key);
    }

    public Integer createImgResource(ImgResource record){
        return imgResourceDao.insertSelective(record);
    }

    public Integer deleteImgResource(ImgResource record){
        return imgResourceDao.delete(record);
    }

    public Integer removeImgResource(ImgResource record){
        return imgResourceDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateImgResourceByPrimaryKey(ImgResource record){
        return imgResourceDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 分页查询图片(带条件)
     * @author kim
     * @param pageNo
     * @param pageSize
     * @param imgResource
     * @return
     */
	@Override
	public List<ImgResource> getImgByPage(Integer pageNo, Integer pageSize, String imgName) {
		return imgResourceDao.selectImgByPage(pageNo, pageSize, imgName);
	}

	/**
	 * 条件查询图片总记录条数
	 * @author kim
	 * @param imgResource
	 * @return
	 */
	@Override
	public Long getImgCount(String imgName) {
		return imgResourceDao.selectImgCount(imgName);
	}

	/**
	 * 查询最火爆表情
	 * @author kim
	 * @return
	 */
	@Override
	public List<ImgResource> getImgResourceByHot() {
		return imgResourceDao.selectImgResourceByHot();
	}

    @Override
    public PageView queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap) {
        PageView pageView = new PageView();
        pageView.setPageNow(pageNo);
        pageView.setPageSize(pageSize);
        paramMap.put("paging",pageView);
        List<ImgResource> listResources = imgResourceDao.queryByPage(paramMap);
        pageView.setRecords(listResources);
        return pageView;
    }
}
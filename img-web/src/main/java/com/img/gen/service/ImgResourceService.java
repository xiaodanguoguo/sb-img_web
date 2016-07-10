package com.img.gen.service;

import java.util.List;

import com.img.gen.dao.model.ImgResource;

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

}
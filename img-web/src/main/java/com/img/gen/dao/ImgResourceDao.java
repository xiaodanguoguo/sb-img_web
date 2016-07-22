package com.img.gen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.img.gen.dao.model.ImgResource;

/**
 * dal Interface:ImgResource
 * @author kim
 * @date 2016-6-18
 */
public interface ImgResourceDao {

    List<ImgResource> selectAll();

    List<ImgResource> select(ImgResource record);

    Integer getCount(ImgResource record);

    ImgResource selectByPrimaryKey(Object key);


    Integer insert(ImgResource record);

    Integer insertSelective(ImgResource record);


    Integer delete(ImgResource record);

    Integer deleteByPrimaryKey(Object key);


    Integer updateByPrimaryKey(ImgResource record);

    Integer updateByPrimaryKeySelective(ImgResource record);
    
    /**
     * 分页查询图片(带条件)
     * @author kim
     * @param pageNo
     * @param pageSize
     * @param imgResource
     * @return
     */
    List<ImgResource> selectImgByPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("imgName") String imgName);

    /**
	 * 条件查询图片总记录条数
	 * @author kim
	 * @param imgResource
	 * @return
	 */
	Long selectImgCount(@Param("imgName") String imgName);

}
package com.img.gen.dao;

import java.util.List;

import com.img.gen.dao.model.ImgComment;

/**
 * dal Interface:ImgComment
 * @author kim
 * @date 2016-6-18
 */
public interface ImgCommentDao {

    List<ImgComment> selectAll();

    List<ImgComment> select(ImgComment record);

    Integer selectCount(ImgComment record);

    ImgComment selectByPrimaryKey(Object key);


    Integer insert(ImgComment record);

    Integer insertSelective(ImgComment record);


    Integer delete(ImgComment record);

    Integer deleteByPrimaryKey(Object key);


    Integer updateByPrimaryKey(ImgComment record);

    Integer updateByPrimaryKeySelective(ImgComment record);

}
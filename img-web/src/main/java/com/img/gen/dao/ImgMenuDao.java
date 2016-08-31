package com.img.gen.dao;

import java.util.List;

import com.img.gen.dao.model.ImgMenu;
import org.springframework.stereotype.Repository;

/**
 * dal Interface:ImgMenu
 * @author king
 * @date 2016-8-31
 */
public interface ImgMenuDao {

    List<ImgMenu> findAll();

    List<ImgMenu> find(ImgMenu record);

    Integer getCount(ImgMenu record);

    ImgMenu getByPrimaryKey(Object key);


    Integer insert(ImgMenu record);

    Integer insertSelective(ImgMenu record);


    Integer delete(ImgMenu record);

    Integer deleteByPrimaryKey(Object key);


    Integer updateByPrimaryKey(ImgMenu record);

    Integer updateByPrimaryKeySelective(ImgMenu record);

}
package com.img.gen.service;

import java.util.List;

import com.img.gen.dao.model.ImgMenu;
import org.springframework.stereotype.Repository;

/**
 * dal Interface:ImgMenu
 * @author king
 * @date 2016-8-31
 */
public interface ImgMenuService {

    public List<ImgMenu> findAll();

    public List<ImgMenu> findImgMenus(ImgMenu record);

    public ImgMenu getImgMenuByPrimaryKey(Object key);

    public Integer createImgMenu(ImgMenu record);

    public Integer deleteImgMenu(ImgMenu record);

    public Integer removeImgMenu(ImgMenu record);

    public Integer updateImgMenuByPrimaryKey(ImgMenu record);

}
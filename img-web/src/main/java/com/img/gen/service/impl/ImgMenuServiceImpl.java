package com.img.gen.service.impl;

import java.util.List;

import com.img.gen.dao.ImgMenuDao;
import com.img.gen.dao.model.ImgMenu;
import com.img.gen.service.ImgMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * dal Interface:ImgMenu
 * @author king
 * @date 2016-8-31
 */
@Service
public class ImgMenuServiceImpl implements ImgMenuService {

    @Autowired
    private ImgMenuDao imgMenuDao;

    public List<ImgMenu> findAll(){
        return  imgMenuDao.findAll();
    }

    public List<ImgMenu> findImgMenus(ImgMenu record){
       return  imgMenuDao.find(record);
    }

    public ImgMenu getImgMenuByPrimaryKey(Object key){
        return imgMenuDao.getByPrimaryKey(key);
    }

    public Integer createImgMenu(ImgMenu record){
        return imgMenuDao.insert(record);

    }

    public Integer deleteImgMenu(ImgMenu record){
        return imgMenuDao.delete(record);
    }

    public Integer removeImgMenu(ImgMenu record){
        return imgMenuDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateImgMenuByPrimaryKey(ImgMenu record){
        return imgMenuDao.updateByPrimaryKeySelective(record);
    }

}
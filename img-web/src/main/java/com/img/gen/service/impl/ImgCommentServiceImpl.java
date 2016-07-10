package com.img.gen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.dao.ImgCommentDao;
import com.img.gen.dao.model.ImgComment;
import com.img.gen.service.ImgCommentService;

/**
 * dal Interface:ImgComment
 * @author kim
 * @date 2016-6-18
 */
@Service
public class ImgCommentServiceImpl implements ImgCommentService{

    @Autowired
    private ImgCommentDao imgCommentDao;

    public List<ImgComment> findAll (){
       return imgCommentDao.selectAll();
	}

    public List<ImgComment> findImgComments(ImgComment record){
       return imgCommentDao.select(record);
    }

    public ImgComment getImgCommentByPrimaryKey(Object key){
        return imgCommentDao.selectByPrimaryKey(key);
    }

    public Integer createImgComment(ImgComment record){
        return imgCommentDao.insert(record);

    }

    public Integer deleteImgComment(ImgComment record){
        return imgCommentDao.delete(record);
    }

    public Integer removeImgComment(ImgComment record){
        return imgCommentDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateImgCommentByPrimaryKey(ImgComment record){
        return imgCommentDao.updateByPrimaryKeySelective(record);
    }

}
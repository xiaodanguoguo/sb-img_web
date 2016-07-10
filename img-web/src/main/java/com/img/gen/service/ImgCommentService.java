package com.img.gen.service;

import java.util.List;

import com.img.gen.dao.model.ImgComment;

/**
 * dal Interface:ImgComment
 * @author kim
 * @date 2016-6-18
 */
public interface ImgCommentService {

    public List<ImgComment> findAll();

    public List<ImgComment> findImgComments(ImgComment record);

    public ImgComment getImgCommentByPrimaryKey(Object key);

    public Integer createImgComment(ImgComment record);

    public Integer deleteImgComment(ImgComment record);

    public Integer removeImgComment(ImgComment record);

    public Integer updateImgCommentByPrimaryKey(ImgComment record);

}
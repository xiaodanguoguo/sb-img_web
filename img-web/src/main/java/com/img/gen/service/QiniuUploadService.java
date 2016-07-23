package com.img.gen.service;

import java.io.File;

/**
 * Created by Zhengpeng on 2016/7/20.
 */
public interface QiniuUploadService {


    /**
     * 上传文件
     * @param file
     * @return
     */
    public Integer upload(File file);


    /**
     * 上传文件
     * @param file
     * @param fileName
     * @return
     */
    public Integer upload(File file, String fileName);


    /**
     * 上传文件
     * @param bytes
     * @param fileName
     * @return
     */
    public Integer upload(byte[] bytes,String fileName);


}

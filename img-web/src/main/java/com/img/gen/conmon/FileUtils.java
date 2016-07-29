package com.img.gen.conmon;

import java.io.File;

/**
 * Created by Zhengpeng on 2016/7/29.
 * 文件工具类
 * */
public class FileUtils {

    /**
     * 删除文件
     * @param file
     * @return
     */
    public static Integer deleteFile(File file){
        if(file.isFile() && file.exists()){
            file.delete();
            return 1;
        }
        return 0;

    }
}

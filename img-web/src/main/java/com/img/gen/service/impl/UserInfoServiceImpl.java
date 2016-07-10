package com.img.gen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.dao.UserInfoDao;
import com.img.gen.dao.model.UserInfo;
import com.img.gen.service.UserInfoService;

/**
 * dal Interface:UserInfo
 * @author kim
 * @date 2016-6-18
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoDao userInfoDao;

    public List<UserInfo> findAll() {
       return userInfoDao.selectAll();
    }

    public List<UserInfo> findUserInfos(UserInfo record){
       return userInfoDao.select(record);
    }

    public UserInfo getUserInfoByPrimaryKey(Object key){
        return userInfoDao.selectByPrimaryKey(key);
    }

    public Integer createUserInfo(UserInfo record){
        return userInfoDao.insert(record);

    }

    public Integer deleteUserInfo(UserInfo record){
        return userInfoDao.delete(record);
    }

    public Integer removeUserInfo(UserInfo record){
        return userInfoDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateUserInfoByPrimaryKey(UserInfo record){
        return userInfoDao.updateByPrimaryKeySelective(record);
    }

}
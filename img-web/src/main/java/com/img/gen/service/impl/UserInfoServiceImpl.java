package com.img.gen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.MD5CncryptHelper;
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

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
	@Override
	public UserInfo login(String userName, String password) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		List<UserInfo> userInfos = userInfoDao.select(userInfo);
		if (BeanUtils.isNotNull(userInfos)) {
			UserInfo user = userInfos.get(0);
			if (MD5CncryptHelper.cncryptMD5(password).equals(user.getPassword()));
			return user;
		} else 
			return null;
	}
}
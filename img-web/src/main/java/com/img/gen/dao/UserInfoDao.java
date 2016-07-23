package com.img.gen.dao;

import java.util.List;

import com.img.gen.dao.model.UserInfo;

/**
 * dal Interface:UserInfo
 * @author kim
 * @date 2016-6-18
 */
public interface UserInfoDao {

    List<UserInfo> selectAll();

    List<UserInfo> select(UserInfo record);

    Integer selectCount(UserInfo record);

    UserInfo selectByPrimaryKey(Object key);

    Integer insert(UserInfo record);

    Integer insertSelective(UserInfo record);

    Integer delete(UserInfo record);

    Integer deleteByPrimaryKey(Object key);

    Integer updateByPrimaryKey(UserInfo record);

    Integer updateByPrimaryKeySelective(UserInfo record);

}
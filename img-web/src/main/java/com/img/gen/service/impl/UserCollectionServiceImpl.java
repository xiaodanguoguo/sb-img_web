package com.img.gen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.dao.UserCollectionDao;
import com.img.gen.dao.model.UserCollection;
import com.img.gen.service.UserCollectionService;

/**
 * dal Interface:UserCollection
 * @author kim
 * @date 2016-6-18
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService{

    @Autowired
    private UserCollectionDao userCollectionDao;

    public List<UserCollection> findAll() {
       return userCollectionDao.selectAll();
    }

    public List<UserCollection> findUserCollections(UserCollection record){
       return userCollectionDao.select(record);
    }

    public UserCollection getUserCollectionByPrimaryKey(Object key){
        return userCollectionDao.selectByPrimaryKey(key);
    }

    public Integer createUserCollection(UserCollection record){
        return userCollectionDao.insert(record);

    }

    public Integer deleteUserCollection(UserCollection record){
        return userCollectionDao.delete(record);
    }

    public Integer removeUserCollection(UserCollection record){
        return userCollectionDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateUserCollectionByPrimaryKey(UserCollection record){
        return userCollectionDao.updateByPrimaryKeySelective(record);
    }

}
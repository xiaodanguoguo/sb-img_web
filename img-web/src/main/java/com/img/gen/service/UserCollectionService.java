package com.img.gen.service;

import java.util.List;

import com.img.gen.dao.model.UserCollection;

/**
 * dal Interface:UserCollection
 * @author kim
 * @date 2016-6-18
 */
public interface UserCollectionService {

    public List<UserCollection> findAll();

    public List<UserCollection> findUserCollections(UserCollection record);

    public UserCollection getUserCollectionByPrimaryKey(Object key);

    public Integer createUserCollection(UserCollection record);

    public Integer deleteUserCollection(UserCollection record);

    public Integer removeUserCollection(UserCollection record);

    public Integer updateUserCollectionByPrimaryKey(UserCollection record);

}
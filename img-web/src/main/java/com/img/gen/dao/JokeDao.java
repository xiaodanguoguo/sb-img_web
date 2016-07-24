package com.img.gen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.img.gen.dao.model.Joke;

/**
 * dal Interface:Joke
 * @author kim
 * @date 2016-6-18
 */
public interface JokeDao {

    List<Joke> selectAll();

    List<Joke> select(Joke record);

    Integer selectCount(Joke record);

    Joke selectByPrimaryKey(Object key);


    Integer insert(Joke record);

    Integer insertSelective(Joke record);


    Integer delete(Joke record);

    Integer deleteByPrimaryKey(Object key);


    Integer updateByPrimaryKey(Joke record);

    Integer updateByPrimaryKeySelective(Joke record);

    /**
     * 分页查询段子列表(按热度排序)
     * @param pageNo
     * @param pageSize
     * @return
     */
	List<Joke> selectByPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 批量插入段子
	 * @param jokes
	 * @return
	 */
	Integer batchInsert(List<Joke> jokes);

}
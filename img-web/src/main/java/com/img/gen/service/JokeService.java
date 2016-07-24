package com.img.gen.service;

import java.util.ArrayList;
import java.util.List;

import com.img.gen.dao.model.Joke;

/**
 * dal Interface:Joke
 * @author kim
 * @date 2016-6-18
 */
public interface JokeService {

    public List<Joke> findAll();

    public List<Joke> findJokes(Joke record);

    public Joke getJokeByPrimaryKey(Object key);

    public Integer createJoke(Joke record);

    public Integer deleteJoke(Joke record);

    public Integer removeJoke(Joke record);

    public Integer updateJokeByPrimaryKey(Joke record);

    /**
     * 分页查询段子列表(按热度排序)
     * @param pageNo
     * @param pageSize
     * @return
     */
	public List<Joke> findByPage(Integer pageNo, Integer pageSize);

	/**
	 * 批量导入笑话
	 * @param copyPropertieses
	 * @return
	 */
	public Integer batchAdd(List<Joke> jokes);

}
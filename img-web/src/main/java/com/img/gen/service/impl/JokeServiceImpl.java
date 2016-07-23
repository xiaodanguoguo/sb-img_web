package com.img.gen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.gen.dao.JokeDao;
import com.img.gen.dao.model.Joke;
import com.img.gen.service.JokeService;

/**
 * dal Interface:Joke
 * @author kim
 * @date 2016-6-18
 */
@Service
public class JokeServiceImpl implements JokeService{

    @Autowired
    private JokeDao jokeDao;

    public List<Joke> findAll() {
       return jokeDao.selectAll();
    }

    public List<Joke> findJokes(Joke record){
       return jokeDao.select(record);
    }

    public Joke getJokeByPrimaryKey(Object key){
        return jokeDao.selectByPrimaryKey(key);
    }

    public Integer createJoke(Joke record){
        return jokeDao.insert(record);

    }

    public Integer deleteJoke(Joke record){
        return jokeDao.delete(record);
    }

    public Integer removeJoke(Joke record){
        return jokeDao.updateByPrimaryKeySelective(record);
    }

    public Integer updateJokeByPrimaryKey(Joke record){
        return jokeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 分页查询段子列表(按热度排序)
     * @param pageNo
     * @param pageSize
     * @return
     */
	@Override
	public List<Joke> findByPage(Integer pageNo, Integer pageSize) {
		return jokeDao.selectByPage(pageNo, pageSize);
	}

}
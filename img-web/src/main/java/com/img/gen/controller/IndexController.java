package com.img.gen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.JsonMapper;
import com.img.gen.controller.dto.JokeDTO;
import com.img.gen.dao.model.ImgComment;
import com.img.gen.dao.model.Joke;
import com.img.gen.service.ImgCommentService;
import com.img.gen.service.JokeService;

@Controller
public class IndexController {
	@Autowired
	private ImgCommentService imgCommentService;
	
	@Autowired
	private JokeService JokeService;
	
	@RequestMapping("index")
	public String test() {
		List<ImgComment> imgComents = imgCommentService.findAll();
		for (ImgComment imgComment : imgComents) {
			System.out.println(imgComment.getCommentContent());
		}
		return "index";
	}

	@RequestMapping("file")
	public String  file() {
		return "file";
	}
	
	@RequestMapping("test1")
	public String test1(JokeDTO jokeDTO) {
		Joke joke = new Joke();
		BeanUtils.copyProperties(jokeDTO, joke);
		JokeService.createJoke(joke);
		JsonMapper nonEmptyMapper = JsonMapper.nonEmptyMapper();
		nonEmptyMapper.toJson(null);
		Joke fromJson = nonEmptyMapper.fromJson("{asdf:dasfd}", Joke.class);
		return null;
	}
	
	public void test2() throws Exception {
		List<JokeDTO> jokeList = new ArrayList<>();
		jokeList.add(new JokeDTO());
		
		List<Joke> copyPropertieses = BeanUtils.copyPropertieses(jokeList, new ArrayList<Joke>(), Joke.class);
		
	}
}

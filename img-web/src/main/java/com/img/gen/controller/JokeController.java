package com.img.gen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.IdHelper;
import com.img.gen.conmon.JsonResult;
import com.img.gen.conmon.thread.AssertContext;
import com.img.gen.controller.dto.JokeDTO;
import com.img.gen.dao.model.Joke;
import com.img.gen.service.JokeService;

@RequestMapping("joke/")
@Controller
public class JokeController {
	@Autowired
	private JokeService jokeService;
	
	@ResponseBody
	@RequestMapping("joke/get/page/{pageNO}/{pageSize}")
	public JsonResult<List<JokeDTO>> getJokeByPage(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
		JsonResult<List<JokeDTO>> result = new JsonResult<>(JsonResult.SUCCESS);
		pageNo = pageNo > 0 ? (pageNo - 1) * pageSize : 0;
		try {
			List<Joke> jokes = jokeService.findByPage(pageNo, pageSize);
			result.setResults(
					BeanUtils.copyPropertieses(
							jokes, new ArrayList<JokeDTO>(), JokeDTO.class));
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("joke/get/{jokeId}")
	public JsonResult<JokeDTO> getJoke(@PathVariable("jokeId") Integer jokeId) {
		JsonResult<JokeDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			Joke joke = jokeService.getJokeByPrimaryKey(jokeId);
			if (BeanUtils.isNotNull(joke)) {
				JokeDTO jokeDTO = new JokeDTO();
				BeanUtils.copyProperties(joke, jokeDTO);
				result.setResults(jokeDTO);
			}
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("joke/add")
	public JsonResult<JokeDTO> addJoke(JokeDTO jokeDTO) {
		JsonResult<JokeDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			jokeDTO.setUserId(AssertContext.getUserId());
			jokeDTO.setCreateTime(new Date());
			jokeDTO.setJokeId(IdHelper.generateLongUUID());
			Joke joke = new Joke();
			BeanUtils.copyProperties(jokeDTO, joke);
			jokeService.createJoke(joke);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		}
		return result;
	}
}

package com.img.gen.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.img.gen.service.QiniuUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.JsonMapper;
import com.img.gen.controller.dto.JokeDTO;
import com.img.gen.dao.model.ImgComment;
import com.img.gen.dao.model.Joke;
import com.img.gen.service.ImgCommentService;
import com.img.gen.service.JokeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	@Autowired
	private ImgCommentService imgCommentService;
	
	@Autowired
	private JokeService JokeService;

	@Autowired
	private QiniuUploadService qiniuUploadService;

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


	@RequestMapping("faslJsonTest")
	@ResponseBody
	public List<Map<String,Object>> faslJsonTest(){
		List retList = new ArrayList();
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("name","zhangsan");
		retMap.put("age",22);
		retMap.put("sex","male");
		retMap.put("address","望京soho");
		retList.add(retMap);
		return retList;
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


	/**
	 * 七牛云文件上传测试
	 * @return
     */
	@RequestMapping("qiniuFileTest")
	/************此方法能够实现上传文件到七牛云，但是在tomcat里面产生了一个垃圾文件********************/
	public ModelAndView qiniuFileTest(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		try {
			String path = request.getSession().getServletContext().getRealPath("upload");
			String fileName = file.getOriginalFilename();
			File uploadFile = new File(path, fileName);
			if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}
			file.transferTo(uploadFile);//把文件transfer到零时文件夹
			qiniuUploadService.upload(uploadFile,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  modelAndView;
	}


	/**
	 * 七牛云文件上传测试
	 * @return
	 */
	@RequestMapping("qiniuFileTest1")
	/************测试字节码上传到七牛云服务器********************/
	public ModelAndView qiniuFileTest1(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		try {
			if(file != null && !file.isEmpty() ){
				String fileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				qiniuUploadService.upload(bytes,fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  modelAndView;
	}

	/**
	 * 跳转生成表情包测试页面
	 * @return
     */
	@RequestMapping("image")
	public String image (){
		return "image";
	}



}

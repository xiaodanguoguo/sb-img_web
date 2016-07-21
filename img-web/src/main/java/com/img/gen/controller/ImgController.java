package com.img.gen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.Page;
import com.img.gen.controller.dto.ImgResourceDTO;
import com.img.gen.dao.model.ImgResource;
import com.img.gen.service.ImgResourceService;

@Controller
@RequestMapping("img/")
public class ImgController {
	@Autowired
	private ImgResourceService imgResourceService; 
	@RequestMapping("get/page")
	public Page<ImgResourceDTO> getImgByPage(Integer pageNo, Integer pageSize, String keyWords, ImgResourceDTO imgResourceDTO) {
		Page<ImgResourceDTO> imgPage = new Page<>(pageNo, pageSize);
		try {
			ImgResource imgResource = new ImgResource();
			if (BeanUtils.isNotNull(imgResourceDTO)) 
				BeanUtils.copyProperties(imgResourceDTO, imgResource);
			
			imgResource.setImgId("1");
			List<ImgResource> imgResources = imgResourceService.getImgByPage(imgPage.getStartRow(), pageSize, imgResource);
			BeanUtils.copyPropertieses(imgResources, new ArrayList<ImgResourceDTO>(), ImgResourceDTO.class);
			Long total = imgResourceService.getImgCount(imgResource);
			imgPage.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgPage;
	}
	
}

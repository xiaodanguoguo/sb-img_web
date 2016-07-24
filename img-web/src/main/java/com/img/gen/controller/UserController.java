package com.img.gen.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.img.gen.conmon.BeanUtils;
import com.img.gen.conmon.JsonResult;
import com.img.gen.conmon.MD5CncryptHelper;
import com.img.gen.conmon.enumeration.StatusEnum;
import com.img.gen.conmon.thread.AssertContext;
import com.img.gen.conmon.thread.User;
import com.img.gen.controller.dto.UserInfoDTO;
import com.img.gen.dao.model.UserInfo;
import com.img.gen.service.UserInfoService;

@RequestMapping("user/")
@Controller
public class UserController {
	private UserInfoService userInfoService;
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public JsonResult<UserInfoDTO> login(String userName, String password) {
		JsonResult<UserInfoDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserInfo userInfo = userInfoService.login(userName, password);
			if (BeanUtils.isNotNull(userInfo)) {
				UserInfoDTO userInfoDTO = new UserInfoDTO();
				User user = new User();
				user.setUserId(userInfo.getUserId());
				user.setUserName(userInfo.getUserName());
				AssertContext.init(user);
				BeanUtils.copyProperties(userInfo, userInfoDTO);
				result.setResults(userInfoDTO);
			} 
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		} 
		return result;
	}
	
	/**
	 * 用户注册
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("reg")
	@ResponseBody
	public JsonResult<String> regUser(UserInfoDTO userInfoDTO) {
		JsonResult<String> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setCreateTime(new Date());
			userInfo.setStatus(StatusEnum.ENABLED.getCode());
			userInfo.setPassword(MD5CncryptHelper.cncryptMD5(userInfoDTO.getPassword()));
			BeanUtils.copyProperties(userInfoDTO, userInfo);
			userInfoService.createUserInfo(userInfo);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		} 
		return result;
	}
	
	/**
	 * 滤重(用户名)
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("filter/{userName}")
	@ResponseBody
	public JsonResult<String> filterRode(@PathVariable("userName") String userName) {
		JsonResult<String> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			List<UserInfo> userInfos = userInfoService.findUserInfos(userInfo);
			if (!BeanUtils.isNotNull(userInfos)) 
				result.setStatus(JsonResult.ERROR);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		} 
		return result;
	}
	
	/**
	 * 修改用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("modify")
	@ResponseBody
	public JsonResult<UserInfoDTO> modifyUser(UserInfoDTO userInfoDTO) {
		JsonResult<UserInfoDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserInfo userInfo = new UserInfo();
			userInfoDTO.setUserId(AssertContext.getUserId());
			userInfoDTO.setModDate(new Date());
			BeanUtils.copyProperties(userInfoDTO, userInfo);
			result.setStatus(
					userInfoService.updateUserInfoByPrimaryKey(userInfo) == 1 ? JsonResult.SUCCESS : JsonResult.ERROR);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		} 
		return result;
	}
	
	/**
	 * 通过用户ID获取用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public JsonResult<UserInfoDTO> getUser() {
		JsonResult<UserInfoDTO> result = new JsonResult<>(JsonResult.SUCCESS);
		try {
			UserInfo userInfo = userInfoService.getUserInfoByPrimaryKey(AssertContext.getUserId());
			if (!BeanUtils.isNotNull(userInfo)) {
				UserInfoDTO userInfoDTO = new UserInfoDTO();
				BeanUtils.copyProperties(userInfo, userInfoDTO);
				result.setResults(userInfoDTO);
			} else 
				result.setStatus(JsonResult.ERROR);
		} catch (Exception e) {
			result.setStatus(JsonResult.ERROR);
		} 
		return result;
	}
}

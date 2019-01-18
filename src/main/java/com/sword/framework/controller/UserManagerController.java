package com.sword.framework.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sword.framework.constant.Constants;
import com.sword.framework.dto.ReturnInfo;
import com.sword.framework.service.UserManagerService;

@RestController
public class UserManagerController {

	private UserManagerService userService;

	@RequestMapping(value = "/user/addAdminUser", method = RequestMethod.POST)
	public @ResponseBody ReturnInfo addAdminUser(String username, String password, String phone)
			throws AuthenticationException {
		return this.addUser(username, password, phone, new String[] { Constants.ROLE_ADMIN });
	}

	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	public @ResponseBody ReturnInfo addUser(String username, String password, String phone)
			throws AuthenticationException {
		return this.addUser(username, password, phone, new String[] { Constants.ROLE_DEFAULT });
	}

	private ReturnInfo addUser(String username, String password, String phone, String[] roles) {
		ReturnInfo ret = new ReturnInfo();
		userService.addUserInfo(username, password, phone, "", roles);
		ret.setSuccess(Constants.SUCCESS);
		ret.setMessage("操作成功");
		return ret;
	}
}

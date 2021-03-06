package com.sword.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sword.framework.constant.Constants;
import com.sword.framework.dto.ReturnInfo;
import com.sword.framework.service.RoleManageService;

@RestController
public class RoleManageController {

	@Autowired
	public RoleManageService roleManageService;

	@RequestMapping(value = "/role/addRole", method = RequestMethod.POST)
	public @ResponseBody ReturnInfo addMenu(String roleName, String roleDesc) {
		String roleId = roleManageService.addRole(roleName, roleDesc);
		ReturnInfo ret = new ReturnInfo();
		ret.setSuccess(Constants.SUCCESS);
		ret.setMessage("操作成功");
		ret.setData("id", roleId);
		return ret;
	}

	@RequestMapping(value = "/role/saveRoleMenus", method = RequestMethod.POST)
	public @ResponseBody ReturnInfo saveRoleMenus(String roleId, String menus) {
		roleManageService.saveRoleMenus(roleId, menus.split(","));
		ReturnInfo ret = new ReturnInfo();
		ret.setSuccess(Constants.SUCCESS);
		ret.setMessage("操作成功");
		return ret;
	}

}

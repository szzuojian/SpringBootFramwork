package com.sword.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sword.framework.constant.Constants;
import com.sword.framework.dto.ReturnInfo;
import com.sword.framework.service.MenuManageService;

import net.sf.json.JSONArray;

@RestController
public class MenuManageController {

	@Autowired
	private MenuManageService menuService;

	@RequestMapping(value = "/menu/addMenu", method = RequestMethod.POST)
	public @ResponseBody ReturnInfo addMenu(String jsonMenu) throws AuthenticationException {
		JSONArray menuArray = JSONArray.fromObject(jsonMenu);
		List menuList = (List) JSONArray.toCollection(menuArray);
		menuService.addMenu(menuList);
		ReturnInfo ret = new ReturnInfo();
		ret.setSuccess(Constants.SUCCESS);
		ret.setMessage("操作成功");
		return ret;
	}

}

package com.sword.framework.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
public interface MenuManageService {

	/**
	 * 增加菜单
	 * 
	 * @param menu
	 */
	public void addMenu(List<Map> menu);

}

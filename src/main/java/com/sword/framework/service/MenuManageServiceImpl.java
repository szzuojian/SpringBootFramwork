package com.sword.framework.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sword.framework.dao.SysMenuRepository;
import com.sword.framework.entity.SysMenu;
import com.sword.framework.util.BeanHelper;

@Service
public class MenuManageServiceImpl implements MenuManageService {

	@Autowired
	SysMenuRepository menuRepository;

	@Override
	public void addMenu(List<Map> menus) {

		List<SysMenu> menuList = new ArrayList<SysMenu>();

		for (Iterator iterator = menus.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			SysMenu menu = new SysMenu();
			BeanHelper.copyProperties(map, menu);

			menuList.add(menu);
		}

		menuRepository.save(menuList);
	}

}

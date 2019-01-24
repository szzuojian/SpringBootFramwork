package com.sword.framework.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sword.framework.dao.SysMenuRepository;
import com.sword.framework.dao.SysRoleRepositroy;
import com.sword.framework.entity.SysMenu;
import com.sword.framework.entity.SysRole;

@Service
public class RoleManageServiceImpl implements RoleManageService {

	@Autowired
	SysRoleRepositroy roleRepository;

	@Autowired
	SysMenuRepository menuRepository;

	@Override
	public String addRole(String roleName, String roleDesc) {
		SysRole role = new SysRole();
		role.setDescription(roleDesc);
		role.setName(roleName);
		role = roleRepository.save(role);
		return role.getId();
	}

	@Override
	public void saveRoleMenus(String roleId, String[] menuIds) {
		Iterable<String> ids = Arrays.asList(menuIds);
		List<SysMenu> menus = menuRepository.findAll(ids);

		SysRole role = roleRepository.findOne(roleId);

		role.setMenus(menus);

		roleRepository.save(role);
	}

}

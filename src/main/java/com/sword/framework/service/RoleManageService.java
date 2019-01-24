package com.sword.framework.service;

import javax.transaction.Transactional;

@Transactional
public interface RoleManageService {

	/**
	 * 增加角色
	 * 
	 * @param roleName
	 * @param roleDesc
	 * @return
	 */
	public String addRole(String roleName, String roleDesc);

	/**
	 * 设置角色和菜单的关系
	 * 
	 * @return
	 */
	public void saveRoleMenus(String roleId, String[] menuIds);

}

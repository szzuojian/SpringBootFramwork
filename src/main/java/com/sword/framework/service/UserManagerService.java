package com.sword.framework.service;

import javax.transaction.Transactional;

@Transactional
public interface UserManagerService {

	/**
	 * 添加用户
	 * 
	 * @param userName
	 * @param password
	 * @param phone
	 * @param addr
	 * @param roleIds
	 */
	public void addUserInfo(String userName, String password, String phone, String addr, String[] roleIds);

}

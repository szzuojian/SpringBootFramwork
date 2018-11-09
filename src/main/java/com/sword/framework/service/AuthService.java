package com.sword.framework.service;

public interface AuthService {

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	String login(String username, String password);

	/**
	 * 刷新
	 * 
	 * @param oldToken
	 * @return
	 */
	String refresh(String oldToken);
}

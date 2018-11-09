package com.sword.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sword.framework.dao.SysUserRepository;
import com.sword.framework.entity.SysUser;

public class CustomUserService implements UserDetailsService {
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SysUser user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		System.out.println("s:" + s);
		System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
		System.out.println("size:" + user.getRoles().size());
		// System.out.println("role:" + user.getRoles().get(0).getName());
		return user;
	}
}

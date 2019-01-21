package com.sword.framework.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sword.framework.dao.SysRoleRepositroy;
import com.sword.framework.dao.SysUserRepository;
import com.sword.framework.entity.SysRole;
import com.sword.framework.entity.SysUser;

@Service
public class UserManagerServiceImpl implements UserManagerService {

	@Autowired
	SysUserRepository userRepository;

	@Autowired
	SysRoleRepositroy roleRepository;

	@Override
	public void addUserInfo(String userName, String password, String phone, String addr, String[] roleIds) {
		SysUser user = new SysUser();
		user.setAddr(addr);
		user.setCreatetime(new Date());
		user.setPassword(password);
		user.setPhone(phone);
		user.setUsername(userName);

		Iterable<String> ids = Arrays.asList(roleIds);
		List<SysRole> roles = roleRepository.findAll(ids);

		user.setRoles(roles);

		userRepository.save(user);
	}

}

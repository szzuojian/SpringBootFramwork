package com.sword.framework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sword.framework.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, String> {
	SysUser findByUsername(String username);
}

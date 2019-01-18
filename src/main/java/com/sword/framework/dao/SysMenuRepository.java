package com.sword.framework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sword.framework.entity.SysMenu;

public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

}

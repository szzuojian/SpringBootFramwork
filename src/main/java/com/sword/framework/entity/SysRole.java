package com.sword.framework.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SysRole {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<SysMenu> menus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public boolean hasMenu(String url) {
		List<SysMenu> menus = getMenus();
		for (SysMenu sysMenu : menus) {
			if (sysMenu.getUrl().equals(url)) {
				return true;
			}
		}
		return false;
	}

}
